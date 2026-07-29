import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import MeasurementTypeSelector from "../components/MeasurementTypeSelector.jsx";
import OperationSelector from "../components/OperationSelector.jsx";
import QuantityInputForm from "../components/QuantityInputForm.jsx";
import ResultCard from "../components/ResultCard.jsx";
import AlertMessage from "../components/AlertMessage.jsx";
import Loading from "../components/Loading.jsx";
import { UNITS_BY_TYPE } from "../utils/units.js";
import { validateInputs } from "../utils/validation.js";
import { useApi } from "../hooks/useApi.js";
import quantityService from "../services/quantityService.js";

const OP_FROM_ROUTE = { compare: "COMPARE", convert: "CONVERT", add: "ADD", subtract: "SUBTRACT", divide: "DIVIDE" };

export default function CalculatorPage() {
  const { op } = useParams();
  const [measurementType, setMeasurementType] = useState("Length");
  const [operation, setOperation] = useState(OP_FROM_ROUTE[op] || "COMPARE");
  const [value1, setValue1] = useState("");
  const [value2, setValue2] = useState("");
  const [unit1, setUnit1] = useState(UNITS_BY_TYPE["Length"][0]);
  const [unit2, setUnit2] = useState(UNITS_BY_TYPE["Length"][0]);
  const [targetUnit, setTargetUnit] = useState(UNITS_BY_TYPE["Length"][0]);
  const [validationError, setValidationError] = useState(null);

  const { data: result, loading, error, execute } = useApi();

  useEffect(() => {
    const units = UNITS_BY_TYPE[measurementType];
    setUnit1(units[0]);
    setUnit2(units[0]);
    setTargetUnit(units[0]);
  }, [measurementType]);

  useEffect(() => {
    if (OP_FROM_ROUTE[op]) setOperation(OP_FROM_ROUTE[op]);
  }, [op]);

  function runOperation() {
    const first = { value: Number(value1), unit: unit1, measurementType };
    switch (operation) {
      case "COMPARE": return quantityService.compare(first, value2, unit2, measurementType);
      case "CONVERT": return quantityService.convert(first, targetUnit);
      case "ADD": return quantityService.add(first, value2, unit2, measurementType, targetUnit);
      case "SUBTRACT": return quantityService.subtract(first, value2, unit2, measurementType, targetUnit);
      case "DIVIDE": return quantityService.divide(first, value2, unit2, measurementType);
      default: return Promise.reject(new Error("Unknown operation"));
    }
  }

  function handleExecute() {
    const validationMsg = validateInputs({ operation, value1, value2, unit1, unit2, targetUnit });
    setValidationError(validationMsg);
    if (validationMsg) return;
    execute(runOperation).catch(() => {});
  }

  return (
    <div className="page fade-in">
      <header className="page-header">
        <h1>Quantity Measurement</h1>
        <p>Measure, compare, convert and calculate across units</p>
      </header>

      <section className="card">
        <h2 className="card-title">Measurement Type</h2>
        <MeasurementTypeSelector selected={measurementType} onSelect={setMeasurementType} />
      </section>

      <section className="card">
        <h2 className="card-title">Operation</h2>
        <OperationSelector selected={operation} onSelect={setOperation} />
      </section>

      <section className="card">
        <h2 className="card-title">Values</h2>
        <QuantityInputForm
          operation={operation}
          measurementType={measurementType}
          value1={value1} setValue1={setValue1}
          value2={value2} setValue2={setValue2}
          unit1={unit1} setUnit1={setUnit1}
          unit2={unit2} setUnit2={setUnit2}
          targetUnit={targetUnit} setTargetUnit={setTargetUnit}
        />
      </section>

      <AlertMessage message={validationError || error} />

      <section className="execute-section">
        <button className="btn btn-primary btn-large" onClick={handleExecute} disabled={loading}>
          {loading ? "Calculating..." : "Execute"}
        </button>
      </section>

      {loading ? <Loading text="Calculating..." /> : <ResultCard result={result} />}
    </div>
  );
}