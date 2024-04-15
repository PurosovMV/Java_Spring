package PMV.HW10.exceptions;

public class AllreadyHaveBook extends RuntimeException{
    public AllreadyHaveBook(String s) {
        super(s);
    }
}
