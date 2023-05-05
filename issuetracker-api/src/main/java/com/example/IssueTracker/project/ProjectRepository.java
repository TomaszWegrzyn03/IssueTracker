package com.example.IssueTracker.project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByProjectUsers_Username(String username);

    @Transactional
    @Modifying
    @Query(value="DELETE  from project_users  WHERE project_id IN (SELECT project_id from projects where project_id = :project_id); " +
            "DELETE from projects WHERE project_id = :project_id ", nativeQuery = true)
    void deleteProject(@Param("project_id")Long projectId);

    @Transactional
    @Modifying
    @Query(value="DELETE from projects; ALTER TABLE projects ALTER COLUMN project_id RESTART WITH 1 ", nativeQuery = true)
    void deleteAllTestProjects();
}
