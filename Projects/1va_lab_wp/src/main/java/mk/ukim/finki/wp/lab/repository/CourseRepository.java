package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private static List<Course> kursevi = new ArrayList<>();

    @PostConstruct
    public void init() {
        kursevi.add(new Course((long) 1, "Diskretna matematika 1", "tesko"));
        kursevi.add(new Course((long) 2, "Strukturno programiranje", "lesno"));
        kursevi.add(new Course((long) 3, "Objektno programiranje", "medium"));
        kursevi.add(new Course((long) 4, "Algoritmi i podatocni strukturi", "lesno"));
        kursevi.add(new Course((long) 5, "Algoritmi i slozenost", "tesko"));
    }

    public long getCourseID() {
        return kursevi.size();
    }

    public List<Course> findAllCourses() {

        return kursevi;
    }

    public Optional<Course> findById(Long courseId) {
        return kursevi.stream().filter(r -> r.getCourseId().equals(courseId)).findFirst();
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        for (int i = 0; i < kursevi.size(); i++) {
            if (kursevi.get(i).getCourseId() == courseId)
                return kursevi.get(i).getStudents();
        }
        return null;
    }

    public Course addStudentToCourse(Student student, Course course) {
        for (Course c : kursevi) {
            if (c == course) {
                c.getStudents().add(student);
                return c;
            }
        }
        return null;
    }
}
