package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class zRobbeChangeUser extends RequestHandler{



    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String nameC = request.getParameter("name");
        String lastnameC = request.getParameter("lastname");
        String emailOld = request.getParameter("oldEmail");
        String emailNew = request.getParameter("newEmail");

        Person edit = getPersonService().findByEmail(emailOld);
        getPersonService().deletePerson(emailOld);
        edit.setFirstName(nameC);
        edit.setLastName((lastnameC));
        edit.setUserId(emailNew);
        getPersonService().addPerson(edit);

        return "";
    }

}
