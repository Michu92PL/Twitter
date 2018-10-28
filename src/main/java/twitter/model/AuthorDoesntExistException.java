package twitter.model;

public class AuthorDoesntExistException extends RuntimeException {
    public AuthorDoesntExistException(String message) {
        super(message);
    }

    public AuthorDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
