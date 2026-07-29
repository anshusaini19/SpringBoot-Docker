import { Link } from "react-router-dom";

export default function NotFoundPage() {
  return (
    <div className="auth-shell">
      <div className="auth-card fade-in" style={{ textAlign: "center" }}>
        <h1 className="auth-title">404</h1>
        <p className="auth-subtitle">This page doesn't exist.</p>
        <Link to="/" className="btn btn-primary btn-full">Back to Home</Link>
      </div>
    </div>
  );
}