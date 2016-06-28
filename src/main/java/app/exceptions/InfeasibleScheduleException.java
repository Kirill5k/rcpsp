package app.exceptions;

/**
 * Created by Kirill on 23/06/2016.
 */
public class InfeasibleScheduleException extends RuntimeException {
    public InfeasibleScheduleException(){}

    public InfeasibleScheduleException(String message) {
        super(message);
    }
}
