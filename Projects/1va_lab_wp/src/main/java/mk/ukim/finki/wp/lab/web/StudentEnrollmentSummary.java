package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentSer;
    private final CourseService courseSer;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, StudentService studentSer, CourseService courseSer) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentSer = studentSer;
        this.courseSer = courseSer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext cont = new WebContext(req, resp, req.getServletContext());
        courseSer.addStudentInCourse(req.getSession().getAttribute("students").toString(), Long.parseLong(req.getSession().getAttribute("course").toString()));
        resp.setContentType("text/html; charset=UTF-8");

        String str = req.getSession().getAttribute("course").toString();
        Long nz = Long.parseLong(str);
        List<Student> students = courseSer.listStudentsByCourse(nz);

        cont.setVariable("studenti", students);
        springTemplateEngine.process("studentsInCourse.html", cont, resp.getWriter());
    }

}
