package controller;
import domain.Group;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class zRobbeUsers extends AsyncRequesthandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String users = "[";
        for	(int i = 0; i!= getPersonService().getPersons().size(); i++){
            users += "{ \"id\": \""+getPersonService().getPersons().get(i).getUserId()+"\", \"firstname\": \""+ getPersonService().getPersons().get(i).getFirstName()+"\", \"lastname\": \""+ getPersonService().getPersons().get(i).getLastName() +"\", \"status\": \""+ getPersonService().getPersons().get(i).getStatus()+"\" }";
            if (i!=getPersonService().getPersons().size()-1){
                users += ", ";
            }
        }
        users += "]";
        //response.setContentType("application/json");
        //response.setHeader("Access-Control-Allow-Origin", "*");
        //response.getWriter().write(users);

        return users;
    }
}
