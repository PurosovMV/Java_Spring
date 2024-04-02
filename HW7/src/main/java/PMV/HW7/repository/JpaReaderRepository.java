package PMV.HW7.repository;

import PMV.HW7.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReaderRepository extends JpaRepository<Reader, Long> {
}
