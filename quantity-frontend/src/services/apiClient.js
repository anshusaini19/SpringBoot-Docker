import axios from "axios";
import config from "../config/config.js";

const apiClient = axios.create({
  baseURL: config.apiBaseUrl,
  headers: { "Content-Type": "application/json" },
});

// Attach JWT to every request
apiClient.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");
  if (token) req.headers.Authorization = `Bearer ${token}`;
  return req;
});

// Normalize the backend's 3 different error response shapes into one string:
// 1) { field: message, ... }        (bean validation, 400)
// 2) "plain error string"           (business exceptions, 400/500)
// 3) { error: true, errorMessage }  (service-level failures, 200 OK)
export function extractErrorMessage(error) {
  if (error?.response) {
    const data = error.response.data;
    if (typeof data === "string") return data;
    if (data && typeof data === "object") {
      if (data.errorMessage) return data.errorMessage;
      const fieldErrors = Object.values(data).filter((v) => typeof v === "string");
      if (fieldErrors.length) return fieldErrors.join(", ");
    }
    return `Request failed (${error.response.status})`;
  }
  if (error?.request) return "Cannot reach the server. Is the backend running?";
  return error?.message || "Something went wrong.";
}

apiClient.interceptors.response.use(
  (res) => res,
  (error) => {
    if (error?.response?.status === 401) {
      localStorage.removeItem("token");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export default apiClient;