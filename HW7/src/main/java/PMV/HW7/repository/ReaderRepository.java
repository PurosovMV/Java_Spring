package PMV.HW7.repository;
import PMV.HW7.entity.Reader;
import lombok.Data;
import org.springframework.stereotype.Repository;



import java.util.ArrayList;
import java.util.List;

@Data
@Repository
public class ReaderRepository {
    private final List<Reader> readers = new ArrayList<>();

    {
        readers.add(new Reader("Виктор", "Иванов"));
        readers.add(new Reader("Иван", "Петров"));
        readers.add(new Reader("Константин", "Рожков"));
        readers.add(new Reader("Виталий", "Семенов"));

    }
    public Reader getById(long id){
        return readers.stream().filter(reader -> reader.getId() == id).findFirst().orElse(null);
    }
    public void addReader(Reader reader){
        readers.add(reader);
    }
    public List<Reader> getAllReaders(){
        return readers;
    }
    public boolean deleteReader(Reader reader){
        return readers.removeIf(b -> b.equals(reader));
    }
}

