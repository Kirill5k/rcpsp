package app.exception;

/**
 * Created by Kirill on 23/06/2016.
 */
public class InfeasibleActivitySequence extends RuntimeException {
    public InfeasibleActivitySequence(String message) {
        super(message);
    }
}
