import { NavLink } from "react-router-dom";
import { useAuth } from "../hooks/useAuth.js";
import ThemeToggle from "./ThemeToggle.jsx";

export default function Navbar() {
  const { logout } = useAuth();
  const linkClass = ({ isActive }) => "nav-link" + (isActive ? " nav-link-active" : "");

  return (
    <nav className="navbar">
      <div className="navbar-brand">Quantity<span className="navbar-brand-accent">Measure</span></div>
      <div className="navbar-links">
        <NavLink to="/" end className={linkClass}>Calculator</NavLink>
        <NavLink to="/history" className={linkClass}>History</NavLink>
        <NavLink to="/help" className={linkClass}>Help</NavLink>
      </div>
      <div className="navbar-actions">
        <ThemeToggle />
        <button className="btn btn-ghost btn-small" onClick={logout}>Logout</button>
      </div>
    </nav>
  );
}