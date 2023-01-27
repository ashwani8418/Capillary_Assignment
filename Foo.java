public class Foo {
    CircuitBreaker circuitBreaker;
    Service service;
    private State state;
   
    public Foo(Service service, CircuitBreaker circuitBreaker) {
        this.circuitBreaker = new CircuitBreaker();
        this.service = service;
    }

    public void serviceRequest() {
        if (circuitBreaker.allowRequest()) {
            // call the service
            Service service = new Service(circuitBreaker);
            service.handleRequest();
            circuitBreaker.recordSuccess();
        } else {
            System.out.println("Service is currently unavailable.");
            circuitBreaker.recordFailure();
        }
    }

    public void setState(State state) {
        this.state = State.OPEN;
    }

    public State getState() {
        return state;
    }
}
