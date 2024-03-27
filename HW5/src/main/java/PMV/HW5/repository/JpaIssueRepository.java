package PMV.HW5.repository;

import PMV.HW5.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIssueRepository extends JpaRepository<Issue, Long> {
}
