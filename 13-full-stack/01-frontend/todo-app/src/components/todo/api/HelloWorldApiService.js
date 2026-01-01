import axios from "axios";

// export function retrieveHelloWorldBean() {
//   return axios.get("http://localhost:8080/hello-world-bean");
// }

const apiClient = axios.create(
  {
    baseURL: 'http://localhost:8080'
  }
);

// export const retrieveHelloWorld = () => apiClient.get("http://localhost:8080/hello-world");

// export const retrieveHelloWorldBean = () => apiClient.get("http://localhost:8080/hello-world-bean");

export const retrieveHelloWorldPathVariable
  = (username) => apiClient.get(`/hello-world/path-variable/${username}`, {
    headers: {
      Authorization: 'Basic U3ViaG86ZHVtbXk='
    }
  });

export const executeBasicAuthenticationService
  = (token) => apiClient.get(`/basicauth`, {
    headers: {
      Authorization: token
    }
  });
