import { createContext, useContext, useState } from "react";

//1: Create Context
export const AuthContext = createContext();

export const useAuth = (() => useContext(AuthContext));

//2: Share the created contents with other components
export default function AuthProvider({ children }) {

  //3: Put some state in the context
  const [number, setNumber] = useState(0);
  const [isAuthenticated, setAuthenticated] = useState(false);

  // setInterval(() => setNumber(number + 1), 10000);
  // const valueToBeShared = { number, isAuthenticated, setAuthenticated };

  return (
    <AuthContext.Provider value={ { number, isAuthenticated, setAuthenticated } }>
      {children}
    </AuthContext.Provider>
  );
}