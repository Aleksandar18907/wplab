package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepo;
    private final StudentRepository studentRepo;

    public CourseServiceImpl(CourseRepository courseRepo, StudentRepository studentRepo) {
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

    public List<Course> listall() {
        return courseRepo.findAllCourses();
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepo.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Student target = new Student();
        for (Student s : studentRepo.findAllStudents()) {
            if (s.getUsername().equals(username)) {
                target = s;
                break;
            }
        }
        for (Course c : courseRepo.findAllCourses()) {
            if (c.getCourseId().equals(courseId))
                courseRepo.addStudentToCourse(target, c);
            return c;
        }
        return null;
    }
}
