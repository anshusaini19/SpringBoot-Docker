import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { useAuth } from "../hooks/useAuth.js";
import Loading from "../components/Loading.jsx";

// Landing page for the redirect coming from the backend's index.html stub
// after a successful Google OAuth2 login (?token=...).
export default function OAuthCallbackPage() {
  const [params] = useSearchParams();
  const { login } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    const token = params.get("token");
    if (token) {
      login(token);
      navigate("/", { replace: true });
    } else {
      navigate("/login", { replace: true });
    }
  }, [params, login, navigate]);

  return <Loading text="Signing you in..." />;
}