package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class zRobbeSearch extends AsyncRequesthandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String searchName = request.getParameter("name");
        ArrayList<Person> foundUsers = getPersonService().findByName(searchName);

        String found = "[";
        for	(int i = 0; i!= foundUsers.size(); i++){
            found += "{ \"id\": \""+foundUsers.get(i).getUserId()+"\", \"firstname\": \""+ foundUsers.get(i).getFirstName()+"\", \"lastname\": \""+ foundUsers.get(i).getLastName() +"\", \"status\": \""+ foundUsers.get(i).getStatus()+"\" }";
            if (i!=foundUsers.size()-1){
                found += ", ";
            }
        }
        found += "]";

        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return found;
    }
}
