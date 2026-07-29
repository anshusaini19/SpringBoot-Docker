import config from "../config/config.js";

const authService = {
  // Full browser navigation to Spring Security's OAuth2 entry point.
  loginWithGoogle() {
    window.location.href = `${config.backendOrigin}/oauth2/authorization/google`;
  },
  saveToken(token) {
    localStorage.setItem("token", token);
  },
  getToken() {
    return localStorage.getItem("token");
  },
  logout() {
    localStorage.removeItem("token");
  },
  isAuthenticated() {
    return !!localStorage.getItem("token");
  },
};

export default authService;