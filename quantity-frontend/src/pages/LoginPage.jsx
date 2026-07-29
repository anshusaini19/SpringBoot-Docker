import authService from "../services/authService.js";
import ThemeToggle from "../components/ThemeToggle.jsx";

export default function LoginPage() {
  return (
    <div className="auth-shell">
      <div className="auth-theme-toggle"><ThemeToggle /></div>
      <div className="auth-card fade-in">
        <h1 className="auth-title">Quantity Measurement</h1>
        <p className="auth-subtitle">Sign in to compare, convert, and track your measurements</p>
        <button className="btn btn-primary btn-large btn-full" onClick={() => authService.loginWithGoogle()}>
          Continue with Google
        </button>
      </div>
    </div>
  );
}