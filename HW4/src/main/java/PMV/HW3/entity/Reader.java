package PMV.HW3.entity;
import lombok.Data;

@Data
public class Reader {
    private static long GEN_ID;

    private final long id;
    private final String firstName;
    private final String lastName;

    public Reader(String firstName, String lastName) {
        this.id = GEN_ID++;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}