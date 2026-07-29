import { MEASUREMENT_TYPES } from "../utils/units.js";

export default function MeasurementTypeSelector({ selected, onSelect }) {
  return (
    <div className="type-grid">
      {MEASUREMENT_TYPES.map((m) => (
        <button
          key={m.type}
          type="button"
          className={`type-card ${selected === m.type ? "is-active" : ""}`}
          onClick={() => onSelect(m.type)}
        >
          <span className="type-card-badge">{m.label.charAt(0)}</span>
          <span className="type-card-label">{m.label}</span>
        </button>
      ))}
    </div>
  );
}