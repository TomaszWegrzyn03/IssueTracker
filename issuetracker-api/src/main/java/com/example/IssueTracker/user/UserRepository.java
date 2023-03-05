package com.example.IssueTracker.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);

   @Query(value="SELECT * from users INNER JOIN project_users on users.user_id = project_users.user_id WHERE project_users.project_id = :project_id", nativeQuery = true)
    List<User> findUsersByProjectId(@Param("project_id") Long projectId);
}

  