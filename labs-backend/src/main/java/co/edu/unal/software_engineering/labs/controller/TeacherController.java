package co.edu.unal.software_engineering.labs.controller;

import co.edu.unal.software_engineering.labs.service.CourseService;
import co.edu.unal.software_engineering.labs.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController{

    private UserService userService;

    private CourseService courseService;

    public TeacherController( UserService userService, CourseService courseService ){
        this.userService = userService;
        this.courseService = courseService;
    }



}
