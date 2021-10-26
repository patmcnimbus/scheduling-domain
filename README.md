# Using the Specification Pattern in Domain Driven Design  
  
If you're looking for a more traditional readme file please go [here](https://github.com/patmcnimbus/scheduling-domain/blob/main/traditional_readme.md).  
  
Domain Driven Design is a [concept](https://en.wikipedia.org/wiki/Domain-driven_design) that has been around since 2003 when Eric Evans published his [book](https://www.amazon.com/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215) of the same title.  Although the concept isn't a new one, I've found that it isn't implemented very often in the contexts that I've worked in as a Java developer.  I first came across Domain Driven Design (DDD) when it was offered as a way to fight latency in micro-services implementations.  It is a very large topic and this post will not even come close to addressing all that it encompasses.  We will however hit on the key concepts that drive the code that you see in the example.  
  
The specification pattern is another concept developed by Evans and Martin Fowler is this [paper](https://www.martinfowler.com/apsupp/spec.pdf).  A vastly scaled down discussion and example can be found [here](https://java-design-patterns.com/patterns/specification/).  In this example the specification pattern is used to validate domain objects in the process of scheduling sorties for a U.S. Air Force Air Refueling Squadron.  
  
## The Domain  
U.S Air Force flying wings develop a flying schedule to integrate operations across multiple echelons of command and functional areas of responsibility.  All these different groups interact with the schedule to move it from an anemic shell to a fully fleshed-out final product.  The context chosen for this example is the flying squadron scheduling context.  This context was chosen primarily because the author could readily serve as the domain expert for this context.  
  
In this context the schedule starts out as nothing more than a list of aircraft sorties where each sortie has nothing but an air refueling request that is received through multiple echelons of higher headquarters.  At this point in the process the most important thing for the person developing the schedule to do is to review aircrew requirements in priority order.  When those requirements are determined, the scheduler can decide what "type" of sortie each one on the schedule will be and then start to match the appropriate aircrew members to the sortie.  
  
Once the aircrew is matched with the sortie, the fine details of the sortie can be finalized and re-adjusted if necessary.  These details include takeoff time, land time, fuel load and finally the actual aircraft that will be assigned to the sortie is added by personnel in maintenance control.  
  
Before the schedule is finalized there is a large list of things that need to be validated before the schedule can be considered ready for execution.  Those include:

1. The air refueling fits in the time window for the sortie.
2. The aircrew meets the minimum crew complement.
3. The fuel load is appropriate for the sortie.
4. The aircrew is appropriate for the sortie type.
5. Finally, that the aircrew are all current, qualified or supervised.
  
At the schedule level there is a further validation that ensures that aircraft are only on the schedule once in a given day or that they have appropriate time for maintenance personnel to prepare the aircraft for a second sortie or "turn" it as they say in the domain.  

Note:  If you are familiar with the domain, you'll notice these aren't all the things that need to be validated for a sortie.  But they come pretty close and the rest wouldn't be hard to implement.
  
## Highlights of Domain Driven Design  
According to Martin Fowler, "Domain-Driven Design is an approach to software development that centers the development on programming a domain model that has a rich understanding of the processes and rules of a domain."  
  
DDD uses terms like bounded context and ubiquitous language that have already been touched on in the section above for the domain and context at hand.  To implement a model in a given context DDD recommends that the domain objects be classified as either aggregates, entities or value objects.  Each of those concepts are defined like so:  
  
>Aggregate:  A cluster of associated objects that are treated as a unit for the purpose of data changes.  External references are restricted to one member of the Aggregate, designated as the root.  A set of consistency rules applies with the Aggregate's boundaries.  
  
>Entity:  An object fundamentally defined not by its attributes, but by a thread of continuity and identity.  
  
>Value Object:  An object that describes some characteristic or attribute but carries no concept of identity.  
  
In the case of this example, almost all of the domain objects are entities at the very least.  The air refueling has been represented as a value object and implemented with a lombok builder to emphasize that decision.  In reality, the air refueling has an identity in a different context but is a value object in this one.  At the top level of the domain, the aggregate root is the schedule object that holds several sortie aggregates.  
  
The specification pattern is chosen in this case to ensure that the domain logic is contained within the domain model and does not leak into the application service layer or clutter the domain objects with verbose validation code.  Given the complexity of some of the validations required, it works very well.  
## The Specification Pattern  
In Java, the specification pattern is best employed as an implementation of the Predicate interface.  Predicate provides a test method that returns a boolean value representing whether the specification is satisfied or not.  There are a couple different explanations referenced above in the introduction that give both a more detailed explanation and in another case some examples from a simpler domain.  At this point it's helpful to take a look at the code for the simplest example in this domain:  
```Java
public class MinimumCrewComplementSatisfiedSpecification extends AbstractSpecification<Sortie> {

  @Override
  public boolean test(Sortie sortie) {
    return thereAreAtLeastTwoPilots(sortie) && thereIsAtLeastOneBoomOperator(sortie);
  }

  private boolean thereAreAtLeastTwoPilots(Sortie sortie) {
    return sortie.getAirCrew().stream()
            .filter(crewMember -> crewMember.getPosition().equals(Position.P))
            .count()
        >= 2;
  }

  private boolean thereIsAtLeastOneBoomOperator(Sortie sortie) {
    return sortie.getAirCrew().stream()
            .filter(crewMember -> crewMember.getPosition().equals(Position.B))
            .count()
        >= 1;
  }
}
```  
I offer this example as the simplest because in my opinion it is the simplest to read and follow.  The reader is encouraged to review the remainder of the specifications and run the tests of the individual specifications as well as the application level unit tests.
  
