// Ported 1:1 from the original validation.js logic.
export function validateInputs({ operation, value1, value2, unit1, unit2, targetUnit }) {
  if (value1 === "" || value1 === null || value1 === undefined) {
    return "Please enter first value.";
  }
  if (Number(value1) <= 0) {
    return "First value must be greater than zero.";
  }

  if (operation !== "CONVERT") {
    if (value2 === "" || value2 === null || value2 === undefined) {
      return "Please enter second value.";
    }
    if (Number(value2) <= 0) {
      return "Second value must be greater than zero.";
    }
  }

  if (!unit1) return "Please select first unit.";
  if (operation !== "CONVERT" && !unit2) return "Please select second unit.";

  if (["CONVERT", "ADD", "SUBTRACT"].includes(operation) && !targetUnit) {
    return "Please select target unit.";
  }

  return null; // no error
}