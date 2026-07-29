import { OPERATIONS } from "../utils/units.js";
import Button from "./Button.jsx";

export default function OperationSelector({ selected, onSelect, compact = false }) {
  return (
    <div className={`segmented-control ${compact ? "segmented-control-compact" : ""}`}>
      {OPERATIONS.map((op) => (
        <Button key={op.key} variant="segment" active={selected === op.key} onClick={() => onSelect(op.key)}>
          {op.label}
        </Button>
      ))}
    </div>
  );
}