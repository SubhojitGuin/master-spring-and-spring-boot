const person = {
  name: "Subho",
  address: {
    line1: "Baker Street",
    city: "London",
    country: 'UK'
  },
  profiles: ['twitter', 'linkedin', 'instagram'],
  printProfiles: () => {
    person.profiles.map(
      profile => console.log(profile)
    )
  }
}

export default function LearningJavaScript() {
  return (
    <>
      <div>{ person.name }</div>
      <div>{ person.address.line1 }</div>
      <div>{ person.profiles[0] }</div>
      <div>{ person.printProfiles() }</div>
    </>
  );
}