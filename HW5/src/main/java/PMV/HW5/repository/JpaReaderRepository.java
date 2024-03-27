package PMV.HW5.repository;

import PMV.HW5.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReaderRepository extends JpaRepository<Reader, Long> {
}
