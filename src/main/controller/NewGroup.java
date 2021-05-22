package controller;

import domain.Group;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class NewGroup extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession();
        String groupnamestring = request.getParameter("groupname");
        String member1string = request.getParameter("member1");
        String member2string = request.getParameter("member2");
        String member3string = request.getParameter("member3");
        String member4string = request.getParameter("member4");
        Person person = (Person) request.getSession().getAttribute("user");


        Person member1 = getPersonService().findPerson(member1string);
        Person member2 = getPersonService().findPerson(member2string);
        Person member3 = getPersonService().findPerson(member3string);
        Person member4 = getPersonService().findPerson(member4string);

        ArrayList<String> errors = new ArrayList<>();

        if(groupnamestring.trim().isEmpty()){
            errors.add("groupname is empty");
        }
        Group newgroup = new Group(groupnamestring);


        if (member1 == null) { errors.add("member 1 doesn't exist."); }
        if (member2 == null) { errors.add("member 2 doesn't exist."); }
        if (member3 == null) { errors.add("member 3 doesn't exist."); }
        if (member4 == null) { errors.add("member 4 doesn't exist."); }
        if (errors.size() != 0) {
            request.setAttribute("errors", errors);
            return "index.jsp";
        }
        else {
            try {
                newgroup.addGroupmember(member1);
                newgroup.addGroupmember(member2);
                newgroup.addGroupmember(member3);
                newgroup.addGroupmember(member4);
                getGroupService().addGroup(newgroup);
            }
            catch(IllegalArgumentException e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "index.jsp";
            }

        }

//            System.out.println(group.getGroupmembers().size());
//            System.out.println(group.getGroupmembers().get(0).getFirstName());
        return "Controller?action=Overview";
    }
}

