import { createContext, useContext, useState } from "react";
import { executeBasicAuthenticationService, executeJwtAuthenticationService } from "../api/AuthenticationApiService";
import { apiClient } from "../api/ApiClient";

//1: Create Context
export const AuthContext = createContext();

export const useAuth = (() => useContext(AuthContext));

//2: Share the created contents with other components
export default function AuthProvider({ children }) {

  //3: Put some state in the context
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState(null);
  const [token, setToken] = useState(null);

  // function login(username, password) {
  //   if (username === "Subho" && password === "dummy") {
  //     setAuthenticated(true);
  //     setUsername(username);
  //     return true;
  //   } else {
  //     setAuthenticated(false);
  //     setUsername(null);
  //     return false;
  //   }
  // }

  // async function login(username, password) { // async means that a Promise is returned as a reponse
  //   const baToken = 'Basic ' + window.btoa(username + ":" + password);

  //   try {
  //     const response = await executeBasicAuthenticationService(baToken); // await pauses the execution until the Promise is resolved

  //     if (response.status == 200) {
  //       setAuthenticated(true);
  //       setUsername(username);
  //       setToken(baToken);

  //       apiClient.interceptors.request.use(
  //         (config) => {
  //           console.log('intercepting and adding a token');
  //           config.headers.Authorization=baToken;
  //           return config;
  //         }
  //       );

  //       return true;
  //     } else {
  //       logout();
  //       return false;
  //     }
  //   } catch(error) {
  //     logout();
  //     return false;
  //   }
  // }

  async function login(username, password) { // async means that a Promise is returned as a reponse
    try {
      const response = await executeJwtAuthenticationService(username, password); // await pauses the execution until the Promise is resolved

      if (response.status == 200) {

        const jwtToken = 'Bearer ' + response.data.token;

        setAuthenticated(true);
        setUsername(username);
        setToken(jwtToken);

        apiClient.interceptors.request.use(
          (config) => {
            console.log('intercepting and adding a token');
            config.headers.Authorization=jwtToken;
            return config;
          }
        );

        return true;
      } else {
        logout();
        return false;
      }
    } catch(error) {
      logout();
      return false;
    }
  }

  function logout() {
    setAuthenticated(false);
    setUsername(null);
    setToken(null);
  }

  return (
    <AuthContext.Provider value={ { isAuthenticated, username, token, login, logout } }>
      {children}
    </AuthContext.Provider>
  );
}