export default function ResultCard({ result }) {
  const hasResult = !!result;
  const isError = result?.error;
  const display = hasResult ? (result.resultString ?? result.resultValue ?? "Success") : "Waiting for operation...";

  return (
    <section className={`result-card ${hasResult ? "has-result" : ""} ${isError ? "is-error" : ""} fade-in`}>
      <span className="result-label">Result</span>
      <span className="result-value">{display}</span>
    </section>
  );
}