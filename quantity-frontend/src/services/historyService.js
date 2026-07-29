import apiClient from "./apiClient.js";

// These call the endpoints described in section 1 above (you add them to the controller).
const historyService = {
  getAllHistory() {
    return apiClient.get("/history/all").then((r) => r.data);
  },
  getHistoryByOperation(operation) {
    return apiClient.get(`/history/operation/${operation}`).then((r) => r.data);
  },
  getHistoryByType(measurementType) {
    return apiClient.get(`/history/type/${measurementType}`).then((r) => r.data);
  },
  getOperationCount(operation) {
    return apiClient.get(`/history/count/${operation}`).then((r) => r.data);
  },
  getErrorHistory() {
    return apiClient.get("/history/errors").then((r) => r.data);
  },
  deleteHistory(id) {
    return apiClient.delete(`/history/${id}`).then((r) => r.data);
  },
};

export default historyService;