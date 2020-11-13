package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.boot.web.servlet.server.Session;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/AddStudent")
public class ListStudentServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentSer;

    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentSer) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentSer = studentSer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebContext cont = new WebContext(req, resp, req.getServletContext());
        List<Student> studenti = studentSer.listAll();
        cont.setVariable("students", studenti);
        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("listStudents.html", cont, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        ses.setAttribute("student", req.getParameter("student"));

        resp.sendRedirect("/StudentEnrollmentSummary");
    }
}
