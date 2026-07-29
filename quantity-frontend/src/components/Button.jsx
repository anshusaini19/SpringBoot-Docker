export default function Button({
  children, onClick, active = false, variant = "primary",
  type = "button", disabled = false, className = "",
}) {
  const base = variant === "segment" ? "segment-btn" : `btn btn-${variant}`;
  const classes = [base, active ? "is-active" : "", className].filter(Boolean).join(" ");

  return (
    <button type={type} className={classes} onClick={onClick} disabled={disabled}>
      {children}
    </button>
  );
}