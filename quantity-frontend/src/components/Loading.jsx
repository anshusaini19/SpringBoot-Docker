export default function Loading({ text = "Loading..." }) {
  return (
    <div className="loading-state" role="status" aria-live="polite">
      <span className="spinner" />
      <span className="loading-text">{text}</span>
    </div>
  );
}