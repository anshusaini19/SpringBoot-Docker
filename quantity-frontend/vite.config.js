import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// Proxies /api/** to the Spring Boot backend during dev so there
// are ZERO CORS issues without touching backend SecurityConfig.
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
});