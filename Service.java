public class Service {
    public State state;
    CircuitBreaker circuitBreaker;

    public Service(CircuitBreaker circuitBreaker) {
        state = State.CLOSED;
    }

    public void handleRequest() {
        if (state == State.CLOSED) {
            // perform some logic for handling the request
            System.out.println("State : "+ state + " Service is healthy and all requests are served.");
        } else if (state == State.OPEN) {
            System.out.println("State : "+ state + " Service is unhealthy and no request is served" );
        } else if (state == State.PARTIALLY_OPEN) {
            if (Math.random() < 0.5) {
                // perform some logic for handling the request
                System.out.println("State : "+ state + " Service transitioned from unhealthy to healthy and few requests are served");
                
            } else {
                System.out.println("State : "+ state + " Service is unhealthy and no request is served");

            }
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
