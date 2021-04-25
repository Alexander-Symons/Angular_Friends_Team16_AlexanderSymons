package controller;

import domain.Person;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

@WebServlet("/Team2Ajax2")
public class Team2Ajax2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Team2Ajax2() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        Person p = (Person) request.getSession().getAttribute("user");
        response.setContentType("text/plain");
        response.getWriter().write("" + p.getRole() + "");
    }

}
