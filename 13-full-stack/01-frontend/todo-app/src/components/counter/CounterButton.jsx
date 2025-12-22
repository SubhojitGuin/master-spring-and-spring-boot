export function CounterButton({ by = 1, incrementMethod, decrementMethod }) {

  function incrementCounterFunction() {
    incrementMethod(by);
  }

  function decrementCounterFunction() {
    decrementMethod(by);
  }

  return (
    <div className="Counter">
      <div>
        <button className="counterButton"
          onClick={incrementCounterFunction}
        >+{by}</button>
        <button className="counterButton"
          onClick={decrementCounterFunction}
        >-{by}</button>
      </div>
    </div>
  );
}
