import { useTheme } from "../hooks/useTheme.js";

export default function ThemeToggle() {
  const { theme, toggleTheme } = useTheme();
  const isDark = theme === "dark";

  return (
    <button className="theme-toggle" onClick={toggleTheme} aria-label="Toggle color theme" aria-pressed={isDark}>
      <span className={`theme-toggle-option ${!isDark ? "is-active" : ""}`}>Light</span>
      <span className={`theme-toggle-option ${isDark ? "is-active" : ""}`}>Dark</span>
      <span className={`theme-toggle-thumb ${isDark ? "is-dark" : ""}`} />
    </button>
  );
}