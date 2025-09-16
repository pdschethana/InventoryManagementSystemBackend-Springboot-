package backend.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("Could not find item with id " + id);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
