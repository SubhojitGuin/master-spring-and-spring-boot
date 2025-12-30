import { useEffect, useState } from "react";
import { retrieveAllTodosForUsernameApi, deleteTodoApi } from "./api/TodoApiService";

export function ListTodosComponent() {

  const [todos, setTodos] = useState([]);

  const [message, setMessage] = useState(null);

  useEffect(() => refreshTodos(), []);  // Executed as soon as the component is loaded

  function refreshTodos() {
    retrieveAllTodosForUsernameApi("Subho")
    .then(response => setTodos(response.data))
    .catch(error => console.log(error));
  }

  function deleteTodo(id) {
    console.log("clicked" + id);
    deleteTodoApi("Subho", id)
    .then(() => {
      setMessage(`Todo ${id} has been deleted successfully`)
      refreshTodos()
    }).catch(error => console.log(error));
  }

  return (
    <div className='container'>
      <h1>Things you want to do!</h1>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className='table'>
          <thead>
            <tr>
              <th>Description</th>
              <th>Is Done?</th>
              <th>Target Date</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {todos.map(todo => (
              <tr key={todo.id}>
                <td>{todo.description}</td>
                <td>{todo.done.toString()}</td>
                <td>{todo.targetDate.toString()}</td>
                <td><button className="btn btn-danger" onClick={() => deleteTodo(todo.id)}>Delete</button></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
