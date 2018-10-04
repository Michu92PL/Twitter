package twitter.model;

public class AuthorRepositoryException extends RuntimeException {
    public AuthorRepositoryException(String message) {
        super(message);
    }

    public AuthorRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
