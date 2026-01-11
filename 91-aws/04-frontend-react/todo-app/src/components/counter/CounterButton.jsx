export function CounterButton({ by = 1, incrementMethod, decrementMethod }) {

  return (
    <div className="Counter">
      <div>
        <button className="counterButton"
          onClick={() => incrementMethod(by)}
        >+{by}</button>
        <button className="counterButton"
          onClick={() => decrementMethod(by)}
        >-{by}</button>
      </div>
    </div>
  );
}
