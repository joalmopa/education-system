package co.com.education.domain.usecase;


import co.com.education.domain.gateway.CourseService;


public class CourseUseCase {

    private CourseService courseService;

    public CourseUseCase(CourseService courseService) {
        this.courseService = courseService;
    }
}
