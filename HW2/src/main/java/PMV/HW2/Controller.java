package PMV.HW2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    StudentRepository studentRepository;

    @Autowired
    public Controller(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")
    public List<Student> getAll() {
        return studentRepository.getAllStudents();
    }
    @GetMapping("/student/{id}")
    public Student getById(@PathVariable int id){
        return studentRepository.getByID(id);
    }
    @GetMapping("/group/{groupName}/student")
    public List<Student> getByGroupName(@PathVariable String groupName){
        return studentRepository.getByGroupName(groupName);
    }
    @GetMapping("/student/search")
    public List<Student> getByName(@RequestParam String name){
        return studentRepository.getByName(name);
    }




}
