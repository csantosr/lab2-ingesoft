package co.edu.unal.software_engineering.labs.repository;

import co.edu.unal.software_engineering.labs.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
    @Query("SELECT ass.course FROM Association ass WHERE ass.userRole.userRolePK.user.id = ?1")
    List<Course> findByUserId(int user_id);
}
