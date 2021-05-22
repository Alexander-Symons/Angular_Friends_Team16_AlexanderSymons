package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class zRobbeGroups extends AsyncRequesthandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String groups = "[";
        for	(int i = 0; i!= getGroupService().getAll().size(); i++){
            groups += "{ \"id\": \""+getGroupService().getAll().get(i).getGroupname()+"\"}";

            if (i!=getGroupService().getAll().size()-1){
                groups += ", ";
            }
        }
        groups += "]";
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return groups;
    }
}
