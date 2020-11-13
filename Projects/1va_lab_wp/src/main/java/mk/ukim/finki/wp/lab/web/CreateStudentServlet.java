package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.StudentService;
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

@WebServlet(urlPatterns = "/createStudent")
public class CreateStudentServlet extends HttpServlet {
    private final StudentService studentSer;
    private final SpringTemplateEngine springTemplateEngine;

    public CreateStudentServlet(StudentService studentSer, SpringTemplateEngine springTemplateEngine) {
        this.studentSer = studentSer;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext cont = new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("createStudent.html", cont, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        studentSer.save(req.getParameter("username"), req.getParameter("password"), req.getParameter("name"), req.getParameter("surname"));

        resp.sendRedirect("/AddStudent");
    }
}
