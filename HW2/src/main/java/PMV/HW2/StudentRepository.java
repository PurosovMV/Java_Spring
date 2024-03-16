package PMV.HW2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentRepository {
    private final List<Student> studentList;


    public StudentRepository() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Виктор", "Иванов", "Архитектор"));
        studentList.add(new Student("Никита", "Петров", "Архитектор"));
        studentList.add(new Student("Владимир", "Смирнов", "Архитектор"));
        studentList.add(new Student("Александр", "Соболев", "Архитектор"));
        studentList.add(new Student("Николай", "Архипов", "Архитектор"));

    }

    //Метод возвращает студента по его ID
    public Student getByID(int id) {
        return studentList.stream().filter(studentList -> studentList.getId() == id).findAny().orElse(null);
    }

    //Метод возвращает весь список студентов
    public List<Student> getAllStudents() {
        return studentList;
    }

    public List<Student> getByGroupName(String groupName) {
        return studentList.stream().filter(studentList -> studentList.getGroupName().equals(groupName)).toList();
    }

    public List<Student> getByName(String name) {
        return studentList.stream().filter(studentList -> studentList.getName().equals(name)).toList();
    }
}
