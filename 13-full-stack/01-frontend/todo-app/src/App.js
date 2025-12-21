import FirstComponent from './components/learning-examples/FirstComponent'; // to import the default one, no {} are required. So, when imported without {}, it refers to the default thing
import SecondComponent from './components/learning-examples/SecondComponent';
import ThirdComponent from './components/learning-examples/ThirdComponent';
import FourthComponent from './components/learning-examples/FourthComponent';
import { FifthComponent } from './components/learning-examples/FirstComponent'; // to import a specific thing enclose within {}
import './App.css';

function App() {
  return (
    <div className="App">
      My Todo Application
      <FirstComponent></FirstComponent>
      <SecondComponent></SecondComponent>
      <ThirdComponent></ThirdComponent>
      <FourthComponent></FourthComponent>
      <FifthComponent></FifthComponent>
    </div>
  );
}

export default App;
