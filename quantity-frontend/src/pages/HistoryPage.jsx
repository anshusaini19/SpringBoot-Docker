import { useState, useEffect, useCallback } from "react";
import OperationSelector from "../components/OperationSelector.jsx";
import HistoryTable from "../components/HistoryTable.jsx";
import AlertMessage from "../components/AlertMessage.jsx";
import Loading from "../components/Loading.jsx";
import Button from "../components/Button.jsx";
import { MEASUREMENT_TYPES } from "../utils/units.js";
import { useApi } from "../hooks/useApi.js";
import historyService from "../services/historyService.js";

const FILTERS = [
  { key: "ALL", label: "All" },
  { key: "BY_OPERATION", label: "By Operation" },
  { key: "BY_TYPE", label: "By Type" },
  { key: "ERRORS_ONLY", label: "Errors Only" },
];

export default function HistoryPage() {
  const [filter, setFilter] = useState("ALL");
  const [operation, setOperation] = useState("COMPARE");
  const [measurementType, setMeasurementType] = useState("Length");
  const [count, setCount] = useState(null);
  const [countLoading, setCountLoading] = useState(false);

  const { data: records, loading, error, execute, setData } = useApi();

  // Memoized with the CORRECT dependencies — only changes identity when
  // filter/operation/measurementType actually change, which is what
  // makes it safe to put in the effect's dependency array below.
  const fetchRecords = useCallback(() => {
    switch (filter) {
      case "BY_OPERATION":
        return historyService.getHistoryByOperation(operation);
      case "BY_TYPE":
        return historyService.getHistoryByType(measurementType);
      case "ERRORS_ONLY":
        return historyService.getErrorHistory();
      default:
        return historyService.getAllHistory();
    }
  }, [filter, operation, measurementType]);

  useEffect(() => {
    execute(fetchRecords).catch(() => {});
  }, [fetchRecords, execute]);

  async function handleGetCount() {
    setCountLoading(true);
    try {
      const c = await historyService.getOperationCount(operation);
      setCount(c);
    } catch {
      setCount(null);
    } finally {
      setCountLoading(false);
    }
  }

  async function handleDelete(id) {
    if (!window.confirm("Delete this record? This cannot be undone.")) return;
    await historyService.deleteHistory(id);
    setData((prev) => (prev || []).filter((r) => r.id !== id));
  }

  return (
    <div className="page fade-in">
      <header className="page-header">
        <h1>Operation History</h1>
        <p>View, filter, count, and manage past operations</p>
      </header>

      <section className="card">
        <h2 className="card-title">Filter</h2>
        <div className="segmented-control">
          {FILTERS.map((f) => (
            <Button key={f.key} variant="segment" active={filter === f.key} onClick={() => setFilter(f.key)}>
              {f.label}
            </Button>
          ))}
        </div>

        {filter === "BY_OPERATION" && (
          <div className="sub-filter">
            <OperationSelector selected={operation} onSelect={setOperation} compact />
          </div>
        )}

        {filter === "BY_TYPE" && (
          <div className="sub-filter segmented-control">
            {MEASUREMENT_TYPES.map((m) => (
              <Button
                key={m.type}
                variant="segment"
                active={measurementType === m.type}
                onClick={() => setMeasurementType(m.type)}
              >
                {m.label}
              </Button>
            ))}
          </div>
        )}
      </section>

      <section className="card count-card">
        <div>
          <h2 className="card-title">Record Count</h2>
          <p className="card-subtitle">Count successful operations for a given type</p>
        </div>
        <div className="count-actions">
          <select className="select-inline" value={operation} onChange={(e) => setOperation(e.target.value)}>
            {["COMPARE", "CONVERT", "ADD", "SUBTRACT", "DIVIDE"].map((o) => (
              <option key={o} value={o}>{o}</option>
            ))}
          </select>
          <Button variant="primary" onClick={handleGetCount} disabled={countLoading}>
            {countLoading ? "Counting..." : "Get Count"}
          </Button>
          {count !== null && <span className="count-badge">{count}</span>}
        </div>
      </section>

      <AlertMessage message={error} />

      <section className="card">
        <h2 className="card-title">Records</h2>
        {loading ? (
          <Loading text="Loading history..." />
        ) : (
          <HistoryTable records={records} onDelete={handleDelete} />
        )}
      </section>
    </div>
  );
}