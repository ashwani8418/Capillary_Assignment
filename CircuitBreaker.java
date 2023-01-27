import java.util.Timer;
import java.util.TimerTask;

public class CircuitBreaker {
    private final int threshold;
    private final int timeout;
    private final int retryAttempts;
    State state;
    private int failureCount;

    public CircuitBreaker() {
        threshold = 5;
        timeout = 10;
        retryAttempts = 3;
        state = State.CLOSED;
        failureCount = 0;
    }

    public boolean allowRequest() {
        switch (state) {
            case CLOSED:
                recordSuccess();
                return true;
            case OPEN:
                recordFailure();
                return false;
            case PARTIALLY_OPEN:
                if (failureCount >= retryAttempts) {
                    state = State.OPEN;
                    return false;
                } else {
                    failureCount++;
                    return true;
                }
            default:
                return false;
        }
    }

    public void recordSuccess() {
        failureCount = 0;
        state = State.CLOSED;
    }

    public void recordFailure() {
        failureCount++;
        if (failureCount >= threshold) {
            state = State.OPEN;
            // start a timer for timeout seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    state = State.PARTIALLY_OPEN;
                }
            }, timeout * 1000);
        }
    }
}