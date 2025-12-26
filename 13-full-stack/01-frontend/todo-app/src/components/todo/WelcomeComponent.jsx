import { useParams, Link } from "react-router-dom";
import axios from "axios";

export function WelcomeComponent() {

  const { username } = useParams();

  function callHelloWorldRestApi() {
    console.log('called');

    axios.get('http://localhost:8080/hello-world') // axios returns a Promise
        .then((response) => successfulResponse(response)) // sets the next action during successful response
        .catch((error) => errorResponse(error)) // handles the error if the call is unsuccessful
        .finally(() => console.log('cleanup')); // provides the final steps that are executed at any circumstances
  }

  function successfulResponse(response) {
    console.log(response);
  }

  function errorResponse(error) {
    console.log(error);
  }
  return (
    <div className="WelcomeComponent">
      <h1>Welcome {username}</h1>
      <div>
        Manage Your todos - <Link to="/todos">Go here</Link>
      </div>
      <div>
        <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>Call Hello World</button>
      </div>
    </div>
  );
}