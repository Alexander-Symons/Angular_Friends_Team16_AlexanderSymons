package controller;

import domain.Person;
import domain.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddFriend extends RequestHandler {
    private PersonService personService;

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        personService = super.getPersonService();
        HttpSession session = request.getSession();
        String naam = request.getParameter("naam");
        Person loggedIn = (Person) session.getAttribute("user");

        if (loggedIn != null){
            Person zoekPerson = personService.findPerson(naam);
            if (zoekPerson != null){
                loggedIn.addFriend(zoekPerson);
                zoekPerson.addFriend(loggedIn);
            }

//            for (Person p : loggedIn.getFriendlist()){
//                System.out.println(p.getFirstName() + " ");
//            }
        }
        return "index_t1.jsp";
    }
}
