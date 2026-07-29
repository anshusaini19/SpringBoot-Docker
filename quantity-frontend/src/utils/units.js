export const MEASUREMENT_TYPES = [
  { type: "Length", label: "Length" },
  { type: "Weight", label: "Weight" },
  { type: "Temperature", label: "Temperature" },
  { type: "Volume", label: "Volume" },
];

export const UNITS_BY_TYPE = {
  Length: ["FEET", "INCHES", "CENTIMETERS", "YARDS"],
  Weight: ["GRAM", "KILOGRAM", "TONNE"],
  Temperature: ["CELSIUS", "FAHRENHEIT", "KELVIN"],
  Volume: ["LITRE", "MILLILITRE", "GALLON"],
};

export const OPERATIONS = [
  { key: "COMPARE", label: "Compare" },
  { key: "CONVERT", label: "Convert" },
  { key: "ADD", label: "Add" },
  { key: "SUBTRACT", label: "Subtract" },
  { key: "DIVIDE", label: "Divide" },
];

export function getLabels(operation) {
  switch (operation) {
    case "COMPARE": return { label1: "From", label2: "To" };
    case "CONVERT": return { label1: "Value", label2: "Target Unit" };
    default: return { label1: "Value 1", label2: "Value 2" };
  }
}