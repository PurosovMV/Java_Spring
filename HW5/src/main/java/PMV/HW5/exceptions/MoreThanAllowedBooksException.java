package PMV.HW5.exceptions;

public class MoreThanAllowedBooksException extends IllegalStateException {
    public MoreThanAllowedBooksException(String message) {
        super(message);
    }
}
