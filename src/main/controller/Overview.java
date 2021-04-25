package controller;

import domain.Group;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Overview extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("user");
        List<Group> groups = getGroupService().getAllWithUser(person);
        request.setAttribute("groups", groups);
//        request.setAttribute("groups", getGroupService().getAll());
        return "overview.jsp";
    }
}
