package PMV.HW7.repository;

import PMV.HW7.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIssueRepository extends JpaRepository<Issue, Long> {
}
