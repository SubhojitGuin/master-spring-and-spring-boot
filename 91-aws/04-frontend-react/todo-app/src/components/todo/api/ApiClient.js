import axios from "axios";

export const apiClient = axios.create(
  {
    // baseURL: 'http://localhost:5000'
    baseURL: 'http://03-rest-api-full-stack-h2-env.eba-h3pwry8z.ap-south-1.elasticbeanstalk.com/'
  }
);
