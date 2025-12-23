import { useState } from 'react';
import './TodoApp.css';

export default function TodoApp() {
  return (
    <div className="TodoApp">
      <LoginComponent/>
      {/* <WelcomeComponent/> */}
    </div>
  );
}

// Here username and password is a controlled component as their content is handled by the React State
function LoginComponent() {

  const [username, setUsername] = useState('Subho');
  const [password, setPassword] = useState('');
  const [showSuccessMessage, setShowSuccessMessage]= useState(false);
  const [showErrorMessage, setShowErrorMessage]= useState(false);

  function handleUsernameChange(event) {
    setUsername(event.target.value);
  }

  function handlePasswordChange(event) {
    setPassword(event.target.value);
  }

  function handleSubmit() {
    if (username === "Subho" && password === "dummy") {
      setShowSuccessMessage(true);
      setShowErrorMessage(false);
    } else {
      setShowSuccessMessage(false);
      setShowErrorMessage(true);
    }
  }

  function SuccessMessageComponent() {
    if (showSuccessMessage)
      return <div className="successMessage">Authenticated Sucessfully</div>;
    else   
      return null;
  }

  function ErrorMessageComponent() {
    if (showErrorMessage)
      return <div className="errorMessage">Authentication Failed!!! Please check your credentials</div>;
    else   
      return null;
  }

  return (
    <div className="Login">
      <SuccessMessageComponent/>
      <ErrorMessageComponent/>
      <div className="LoginForm">
        <div>
          <label>User Name:</label>
          <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
        </div>
        <div>
          <label>Password:</label>
          <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
        </div>
        <div>
          <button type="button" name="login" onClick={handleSubmit}>Login</button>
        </div>
      </div>
    </div>
  );
}

function WelcomeComponent() {
  return (
    <div className="Welcome">
      Welcome Component
    </div>
  );
}