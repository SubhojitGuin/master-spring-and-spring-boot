import { useState } from 'react';
import './TodoApp.css';
import { BrowserRouter, Routes, Route, useNavigate, useParams } from 'react-router-dom';

export default function TodoApp() {
  return (
    <div className="TodoApp">
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<LoginComponent/>}></Route>
          <Route path='/login' element={<LoginComponent/>}></Route>
          <Route path='/welcome/:username' element={<WelcomeComponent/>}></Route>
          <Route path='*' element={<ErrorComponent/>}></Route>
        </Routes>
      </BrowserRouter>      
    </div>
  );
}

// Here username and password is a controlled component as their content is handled by the React State
function LoginComponent() {

  const [username, setUsername] = useState('Subho');
  const [password, setPassword] = useState('');
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [showErrorMessage, setShowErrorMessage] = useState(false);
  const navigate = useNavigate();

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
      navigate(`/welcome/${username}`);
    } else {
      setShowSuccessMessage(false);
      setShowErrorMessage(true);
    }
  }

  return (
    <div className="Login">
      <h1>Time to Login!</h1>
      {showSuccessMessage && <div className="successMessage">Authenticated Successfully</div>}
      {showErrorMessage && <div className="errorMessage">Authentication Failed! Please check your credentials</div>}
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

  const { username } = useParams();

  return (
    <div className="WelcomeComponent">
      <h1>Welcome {username}</h1>
      <div>
        Welcome Component
      </div>
    </div>
  );
}

function ErrorComponent() {
  return (
    <div className="ErrorComponent">
      <h1>We are working really hard!</h1>
      <div>
        Apologies for the 404. Reach out to our team ABC-DEF-GHIJ.
      </div>
    </div>
  );
}