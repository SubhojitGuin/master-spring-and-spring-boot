import { useState } from 'react';
import './TodoApp.css';
import { BrowserRouter, Routes, Route, useNavigate, useParams } from 'react-router-dom';

export default function TodoApp() {
  return (
    <div className="TodoApp">
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<LoginComponent/>}/>
          <Route path='/login' element={<LoginComponent/>}/>
          <Route path='/welcome/:username' element={<WelcomeComponent/>}/>
          <Route path='/todos' element={<ListTodosComponent/>}/>
          <Route path='*' element={<ErrorComponent/>}/>
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

function ListTodosComponent() {
  const todos = [
    {id: 1, description: "Learn AWS"},
    {id: 2, description: "Learn Azure"},
    {id: 3, description: "Learn DevOps"},
    {id: 4, description: "Learn GCP"}
  ]
  return (
    <div className='ListTodosComponent'>
      <h1>Things you want to do!</h1>
      <div>
        <table>
          <thead>
            <th>ID</th>
            <th>Description</th>
          </thead>
          <tbody>
              {todos.map(todo => (
                <tr>
                  <td>{todo.id}</td>
                  <td>{todo.description}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}