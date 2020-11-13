package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    static List<Student> studenti= new ArrayList<>();

    @PostConstruct
    public void init() {
        studenti.add(new Student("abv", "strongpw1", "Ace", "Nikolov"));
        studenti.add(new Student("abvg", "strongpw2", "Dejan", "Ne_znam"));
        studenti.add(new Student("abvd", "strongpw3", "Petko", "Sho_da"));
        studenti.add(new Student("abvgj", "strongpw4", "Marija", "Napisham"));
        studenti.add(new Student("abve", "strongpw5", "Stanko", "Laboratoriska"));
    }

    public List<Student> findAllStudents() {
        return studenti;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return (List<Student>) studenti.stream().filter(r -> r.getName().contains(text) || r.getSurname().contains(text));
    }

    public void save(Student st) {
        if (st != null)
            studenti.add(st);
    }

}
