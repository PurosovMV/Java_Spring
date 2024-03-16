package PMV.HW2;

import lombok.Data;

@Data
public class Student {
    private final int id;
    private final String name;

    private final String surname;
    private final String groupName;
    private static int ID_AUTOINCREMENT;

    public Student(String name, String surname, String groupName) {
        this.id = ++ID_AUTOINCREMENT;
        this.name = name;
        this.surname = surname;
        this.groupName = groupName;
    }
}
