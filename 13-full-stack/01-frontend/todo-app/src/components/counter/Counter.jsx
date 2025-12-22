import { useState } from 'react';
import { PropTypes } from 'prop-types';
import './Counter.css';

export default function CounterButton({ by }) {

  const [count, setCount] = useState(0); // returns 2 things - Current State, function to update the state

  function incrementCounterFunction() {
    setCount(count + by);
  }

  function decrementCounterFunction() {
    setCount(count - by);
  }

  return (
    <div className="Counter">
      <span className="count">{ count }</span>
      <div>
        <button className="counterButton" 
            onClick={ incrementCounterFunction }
        >+{ by }</button>
        <button className="counterButton"
            onClick={ decrementCounterFunction }
        >-{ by }</button>
      </div>
    </div>
  )
}

// Set the datatype of the properties
CounterButton.propTypes = {
  by: PropTypes.number
}

// Set default values of the properties if they are not specified while calling the element
CounterButton.defaultProps = {
  by: 1
}