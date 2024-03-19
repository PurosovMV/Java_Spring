package PMV.HW3.exceptions;

public class MoreThanAllowedBooksException extends IllegalStateException {
    public MoreThanAllowedBooksException(String message) {
        super(message);
    }
}
