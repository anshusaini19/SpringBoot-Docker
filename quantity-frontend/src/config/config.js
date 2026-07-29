// Central place for all environment/config values.
const config = {
  apiBaseUrl: import.meta.env.VITE_API_BASE_URL || "/api/quantity",
  backendOrigin: import.meta.env.VITE_BACKEND_ORIGIN || "http://localhost:8080",
};

export default config;