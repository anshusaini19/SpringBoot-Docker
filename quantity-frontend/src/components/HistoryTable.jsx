import Button from "./Button.jsx";

export default function HistoryTable({ records, onDelete }) {
  if (!records || records.length === 0) {
    return (
      <div className="empty-state">
        <p>No history records found.</p>
        <span>Run an operation from the calculator to see it appear here.</span>
      </div>
    );
  }

  return (
    <div className="table-wrapper">
      <table className="data-table">
        <thead>
          <tr>
            <th>Operation</th><th>Type</th><th>This</th><th>That</th><th>Result</th><th>Status</th><th></th>
          </tr>
        </thead>
        <tbody>
          {records.map((r) => (
            <tr key={r.id ?? `${r.operation}-${r.thisValue}-${r.thatValue}-${Math.random()}`}>
              <td>{r.operation}</td>
              <td>{r.thisMeasurementType}</td>
              <td>{r.thisValue} {r.thisUnit}</td>
              <td>{r.thatValue != null ? `${r.thatValue} ${r.thatUnit}` : "—"}</td>
              <td>{r.resultString ?? r.resultValue ?? "—"}</td>
              <td><span className={`badge ${r.error ? "badge-danger" : "badge-success"}`}>{r.error ? "Error" : "OK"}</span></td>
              <td className="table-actions">
                {r.id != null && onDelete && (
                  <Button variant="danger" className="btn-small" onClick={() => onDelete(r.id)}>Delete</Button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}