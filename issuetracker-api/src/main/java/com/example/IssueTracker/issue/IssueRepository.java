package com.example.IssueTracker.issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IssueRepository  extends JpaRepository<Issue, Long> {

    List<Issue> findIssueByProjectId(Long projectId);

    @Transactional
    @Modifying
    @Query(value="DELETE  from issue_users  WHERE issue_id IN (SELECT issue_id from issues where project_id = :project_id); " +
            "DELETE  from issues WHERE project_id = :project_id ", nativeQuery = true)
    void deleteAllIssuesByProjectId(@Param("project_id")Long projectId);

    @Transactional
    @Modifying
    @Query(value="DELETE  from issue_users  WHERE issue_id = :issue_id ; " +
            "DELETE  from issues WHERE issue_id = :issue_id ", nativeQuery = true)
    void deleteIssue(@Param("issue_id")Long issueId);

    @Transactional
    @Modifying
    @Query(value="DELETE from issues; ALTER TABLE issues ALTER COLUMN issue_id RESTART WITH 1 ", nativeQuery = true)
    void deleteAllTestIssues();
}
