package PMV.HW5.exceptions;

public class BookHasBeenReturnedException extends RuntimeException {
    public BookHasBeenReturnedException(String s) {
        super(s);
    }
}
