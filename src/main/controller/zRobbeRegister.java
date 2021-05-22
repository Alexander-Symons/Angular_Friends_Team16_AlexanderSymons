package controller;

import domain.Person;
import domain.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class zRobbeRegister extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<String> errors = new ArrayList<String>();
        String answer = request.getParameter("answer");

        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            errors.add("No email given");
        }

        String password = request.getParameter("password");
        if (password == null || password.isEmpty()) {
            errors.add("No password given");
        }

        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            errors.add("No name given");
        }

        String lastname = request.getParameter("lastname");
        if (lastname == null || lastname.isEmpty()) {
            errors.add("No lastname given");
        }

        if (errors.size() == 0) {
            getPersonService().addPerson(new Person(email, password, name, lastname, Role.LID));
        }

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
        }

        return "";
    }
}
