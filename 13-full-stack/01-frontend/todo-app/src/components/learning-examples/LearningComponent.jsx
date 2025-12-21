import FirstComponent from './FirstComponent'; // to import the default one, no {} are required. So, when imported without {}, it refers to the default thing
import SecondComponent from './SecondComponent';
import ThirdComponent from './ThirdComponent';
import FourthComponent from './FourthComponent';
import { FifthComponent } from './FirstComponent'; // to import a specific thing enclose within {}

export default function LearningComponent() {
  return (
    <div className="LearningComponent">
      <FirstComponent></FirstComponent>
      <SecondComponent></SecondComponent>
      <ThirdComponent></ThirdComponent>
      <FourthComponent></FourthComponent>
      <FifthComponent></FifthComponent>
    </div>
  );
}