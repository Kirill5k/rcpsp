package app.exceptions;

/**
 * Created by Kirill on 20/06/2016.
 */
public class StartingActivityNotFoundException extends RuntimeException {
    public StartingActivityNotFoundException(String message) {
        super(message);
    }
}
