import './App.css';
import CounterButton from './components/counter/Counter';

function App() {
  return (
    <div className="App">
      {/* <PlayWithProperties property1="value1" property2="value2"></PlayWithProperties> */}
      <CounterButton by={1}/>
      <CounterButton by={2}/>
      <CounterButton by={5}/>
    </div>
  );
}

// {property1: 'value1', property2: 'value2'}
// function PlayWithProperties(properties) {
  
//   console.log(properties);
//   console.log(properties.property1);
//   console.log(properties.property2);

//   return (
//     <div>Play With Properties</div>
//   );
// }

// function PlayWithProperties({ property1, property2 }) {
  
//   console.log(property1);
//   console.log(property2);

//   return (
//     <div>Play With Properties</div>
//   );
// }

export default App;
