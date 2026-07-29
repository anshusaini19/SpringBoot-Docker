import { useState, useCallback, useRef } from "react";
import { extractErrorMessage } from "../services/apiClient.js";

/**
 * Generic { data, loading, error, execute } hook.
 * IMPORTANT: pass the async function to `execute` at call time, e.g.
 *   execute(() => historyService.getAllHistory())
 * NOT at construction time. This keeps `execute`'s identity 100% stable
 * (empty dependency array) so it is always safe to use inside useEffect
 * dependency arrays without risking re-render loops.
 * Also guards against race conditions: if a newer call starts before an
 * older one resolves, the older one's result is discarded.
 */
export function useApi() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const requestIdRef = useRef(0);

  const execute = useCallback(async (apiFn) => {
    const requestId = ++requestIdRef.current;
    setLoading(true);
    setError(null);
    try {
      const result = await apiFn();
      if (requestId === requestIdRef.current) setData(result);
      return result;
    } catch (err) {
      const message = extractErrorMessage(err);
      if (requestId === requestIdRef.current) setError(message);
      throw err;
    } finally {
      if (requestId === requestIdRef.current) setLoading(false);
    }
  }, []);

  return { data, loading, error, execute, setData, setError };
}