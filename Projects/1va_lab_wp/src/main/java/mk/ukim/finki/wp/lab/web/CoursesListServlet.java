package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import mk.ukim.finki.wp.lab.model.Course;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/listCourses")
public class CoursesListServlet extends HttpServlet {
    private final CourseService courseSer;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListServlet(CourseService courseSer, SpringTemplateEngine springTemplateEngine) {
        this.courseSer = courseSer;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Course> courses = courseSer.listall();
        context.setVariable("courses", courses);
        resp.setContentType("text/html; charset=UTF-8");
        springTemplateEngine.process("listCourses.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("course", req.getParameter("course"));


        resp.sendRedirect("/AddStudent");

    }
}
