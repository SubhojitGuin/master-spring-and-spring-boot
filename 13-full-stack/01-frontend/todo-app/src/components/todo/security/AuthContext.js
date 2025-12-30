import { createContext, useContext, useState } from "react";

//1: Create Context
export const AuthContext = createContext();

export const useAuth = (() => useContext(AuthContext));

//2: Share the created contents with other components
export default function AuthProvider({ children }) {

  //3: Put some state in the context
  const [isAuthenticated, setAuthenticated] = useState(false);

  const [username, setUsername] = useState(null);

  function login(username, password) {
    if (username === "Subho" && password === "dummy") {
      setAuthenticated(true);
      setUsername(username);
      return true;
    } else {
      setAuthenticated(false);
      setUsername(null);
      return false;
    }
  }

  function logout() {
    setAuthenticated(false);
    setUsername(null);
  }

  return (
    <AuthContext.Provider value={ { isAuthenticated, username, login, logout } }>
      {children}
    </AuthContext.Provider>
  );
}