public class Driver {
    public static void main(String[] args) {
        CircuitBreaker circuitBreaker = new CircuitBreaker();
        Service service = new Service(circuitBreaker);
        Foo foo = new Foo(service, circuitBreaker);
        foo.setState(State.CLOSED);
        foo.serviceRequest();
        foo.setState(State.OPEN);
        foo.serviceRequest();
        foo.setState(State.PARTIALLY_OPEN);
        foo.serviceRequest();
        foo.serviceRequest();
    }
}
