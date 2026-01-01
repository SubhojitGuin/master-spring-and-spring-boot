import { apiClient } from "./ApiClient";

// export function retrieveHelloWorldBean() {
//   return axios.get("http://localhost:8080/hello-world-bean");
// }

// export const retrieveHelloWorld = () => apiClient.get("http://localhost:8080/hello-world");

// export const retrieveHelloWorldBean = () => apiClient.get("http://localhost:8080/hello-world-bean");

export const retrieveHelloWorldPathVariable
  = (username) => apiClient.get(`/hello-world/path-variable/${username}`);

export const executeBasicAuthenticationService
  = (token) => apiClient.get(`/basicauth`, {
    headers: {
      Authorization: token
    }
  });
