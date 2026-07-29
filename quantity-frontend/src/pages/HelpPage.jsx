export default function HelpPage() {
  return (
    <div className="page fade-in">
      <header className="page-header">
        <h1>Help</h1>
        <p>Quick reference for using the calculator</p>
      </header>

      <section className="card">
        <h2 className="card-title">Supported Measurement Types</h2>
        <ul className="help-list">
          <li>Length</li><li>Weight</li><li>Temperature</li><li>Volume</li>
        </ul>
      </section>

      <section className="card">
        <h2 className="card-title">How it works</h2>
        <p className="card-subtitle">
          Select a measurement type and an operation, enter your values, then press Execute.
          The result appears automatically below.
        </p>
      </section>
    </div>
  );
}