package app.exception;

/**
 * Created by Kirill on 22/06/2016.
 */
public class UnsupportedAlgorithmType extends RuntimeException {
    public UnsupportedAlgorithmType(String message) {
        super(message);
    }
}
