package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.Group;
import domain.Person;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/OverviewJSON")
public class OverviewJSON extends AsyncRequesthandler {
    public String handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        List<Group> groups = getGroupService().getAll();
        String personJSON = toJSON(groups);
        return personJSON;
    }

    private String toJSON (List<Group> person) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(person);
        return json;
    }

//    response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(personJSON);
}