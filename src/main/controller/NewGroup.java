package controller;

import domain.Group;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewGroup extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession();
        String string = request.getParameter("groupname");
        Person person = (Person) request.getSession().getAttribute("user");
        Group newgroup = new Group(string);
        boolean createnewgroup = true;
        for(Group group: getGroupService().getAll()) {
            if (group.getGroupname().equalsIgnoreCase(string)) {
                group.addGroupmember(person);
                createnewgroup = false;
            }
        }
        if(createnewgroup) {
            newgroup.addGroupmember(person);
            getGroupService().addGroup(newgroup);
        }

//            System.out.println(group.getGroupmembers().size());
//            System.out.println(group.getGroupmembers().get(0).getFirstName());
        return "index.jsp";
    }

}
