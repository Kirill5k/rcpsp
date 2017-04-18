package app.exception;

/**
 * Created by Kirill on 20/06/2016.
 */
public class StartingActivityNotFound extends RuntimeException {
    public StartingActivityNotFound(String message) {
        super(message);
    }
}
