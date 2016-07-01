package app.exceptions;

/**
 * Created by Kirill on 23/06/2016.
 */
public class InfeasibleActivitySequenceException extends RuntimeException {
    public InfeasibleActivitySequenceException(){}

    public InfeasibleActivitySequenceException(String message) {
        super(message);
    }
}
