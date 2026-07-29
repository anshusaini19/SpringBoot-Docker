import apiClient from "./apiClient.js";

// All 5 endpoints that exist today in QuantityMeasurementController.
const quantityService = {
  compare(first, secondValue, secondUnit, secondMeasurementType) {
    return apiClient.post("/compare", first, {
      params: { secondValue, secondUnit, secondMeasurementType },
    }).then((r) => r.data);
  },

  convert(quantity, targetUnit) {
    return apiClient.post("/convert", quantity, {
      params: { targetUnit },
    }).then((r) => r.data);
  },

  add(first, secondValue, secondUnit, secondMeasurementType, targetUnit) {
    return apiClient.post("/add", first, {
      params: { secondValue, secondUnit, secondMeasurementType, targetUnit },
    }).then((r) => r.data);
  },

  subtract(first, secondValue, secondUnit, secondMeasurementType, targetUnit) {
    return apiClient.post("/subtract", first, {
      params: { secondValue, secondUnit, secondMeasurementType, targetUnit },
    }).then((r) => r.data);
  },

  divide(first, secondValue, secondUnit, secondMeasurementType) {
    return apiClient.post("/divide", first, {
      params: { secondValue, secondUnit, secondMeasurementType },
    }).then((r) => r.data);
  },
};

export default quantityService;