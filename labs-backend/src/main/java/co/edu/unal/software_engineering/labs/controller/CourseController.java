package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.model.Course;
import co.edu.unal.software_engineering.labs.model.User;
import co.edu.unal.software_engineering.labs.pojo.CoursePOJO;
import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CourseController{

    private final CourseService courseService;
    private UserService userService;

    public CourseController( CourseService courseService, UserService userService ){
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping( value = {"/profesor/cursos", "/profesor/crear-curso"} )
    public ResponseEntity<Void> createCourse( @RequestBody CoursePOJO coursePojo ){
        Course course = courseService.mapperCourseEntity( coursePojo );
        if( !courseService.isRightCourse( course ) ){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );
        }
        courseService.save( course );
        return new ResponseEntity<>( HttpStatus.CREATED );
    }

    @GetMapping( value = {"/estudiante/cursos"} )
    public List<Course> getCoursesStudent() {
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        User student = userService.findByUsername( username );
        return courseService.findByUserId(student.getId());
    }

    @GetMapping( value = {"/profesor/cursos"} )
    public List<Course> getTeacherCourses() {
        String username = SecurityContextHolder.getContext( ).getAuthentication( ).getName( );
        User teacher = userService.findByUsername( username );
        return courseService.findByUserId(teacher.getId());
    }

}