import { Routes, Route } from "react-router-dom";
import ProtectedRoute from "../components/ProtectedRoute.jsx";
import MainLayout from "../layouts/MainLayout.jsx";
import LoginPage from "../pages/LoginPage.jsx";
import OAuthCallbackPage from "../pages/OAuthCallbackPage.jsx";
import CalculatorPage from "../pages/CalculatorPage.jsx";
import HistoryPage from "../pages/HistoryPage.jsx";
import HelpPage from "../pages/HelpPage.jsx";
import NotFoundPage from "../pages/NotFoundPage.jsx";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/oauth-callback" element={<OAuthCallbackPage />} />

      <Route element={<ProtectedRoute />}>
        <Route element={<MainLayout />}>
          <Route path="/" element={<CalculatorPage />} />
          <Route path="/:op" element={<CalculatorPage />} />
          <Route path="/history" element={<HistoryPage />} />
          <Route path="/help" element={<HelpPage />} />
        </Route>
      </Route>

      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  );
}