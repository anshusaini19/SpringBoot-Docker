import { UNITS_BY_TYPE, getLabels } from "../utils/units.js";

export default function QuantityInputForm({
  operation, measurementType,
  value1, setValue1, value2, setValue2,
  unit1, setUnit1, unit2, setUnit2,
  targetUnit, setTargetUnit,
}) {
  const units = UNITS_BY_TYPE[measurementType] || [];
  const { label1, label2 } = getLabels(operation);
  const needsSecondValue = operation !== "CONVERT";
  const needsTargetUnit = ["CONVERT", "ADD", "SUBTRACT"].includes(operation);

  return (
    <div className="input-grid">
      <div className="input-field">
        <label>{label1}</label>
        <div className="input-row">
          <input type="number" placeholder="Enter value" value={value1} onChange={(e) => setValue1(e.target.value)} />
          <select value={unit1} onChange={(e) => setUnit1(e.target.value)}>
            {units.map((u) => <option key={u} value={u}>{u}</option>)}
          </select>
        </div>
      </div>

      {needsSecondValue && (
        <div className="input-field">
          <label>{label2}</label>
          <div className="input-row">
            <input type="number" placeholder="Enter value" value={value2} onChange={(e) => setValue2(e.target.value)} />
            <select value={unit2} onChange={(e) => setUnit2(e.target.value)}>
              {units.map((u) => <option key={u} value={u}>{u}</option>)}
            </select>
          </div>
        </div>
      )}

      {needsTargetUnit && (
        <div className="input-field">
          <label>Target Unit</label>
          <select className="select-full" value={targetUnit} onChange={(e) => setTargetUnit(e.target.value)}>
            {units.map((u) => <option key={u} value={u}>{u}</option>)}
          </select>
        </div>
      )}
    </div>
  );
}