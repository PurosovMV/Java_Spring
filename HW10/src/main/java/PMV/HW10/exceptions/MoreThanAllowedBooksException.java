package PMV.HW10.exceptions;

public class MoreThanAllowedBooksException extends IllegalStateException {
    public MoreThanAllowedBooksException(String message) {
        super(message);
    }
}
