package PMV.HW10.exceptions;

public class BookHasBeenReturnedException extends RuntimeException {
    public BookHasBeenReturnedException(String s) {
        super(s);
    }
}
