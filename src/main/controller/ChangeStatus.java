package controller;
import domain.Person;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ChangeStatus")
public class ChangeStatus extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        if (person != null){
            response.setContentType("text/plain");
            response.getWriter().write(person.getStatus());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        String status = request.getParameter("status");
        person.setStatus(status);

        response.setContentType("text/plain");
        response.getWriter().write(person.getStatus());
    }
}
