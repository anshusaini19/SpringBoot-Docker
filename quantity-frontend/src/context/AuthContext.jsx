import React, { createContext, useState, useCallback } from "react";
import authService from "../services/authService.js";

export const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [token, setToken] = useState(authService.getToken());

  const login = useCallback((newToken) => {
    authService.saveToken(newToken);
    setToken(newToken);
  }, []);

  const logout = useCallback(() => {
    authService.logout();
    setToken(null);
  }, []);

  const value = { token, isAuthenticated: !!token, login, logout };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}