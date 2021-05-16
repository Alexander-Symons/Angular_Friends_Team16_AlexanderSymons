package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.AsyncRequesthandler;
import domain.Group;
import domain.Person;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class UpdateJSON extends AsyncRequesthandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(reader);
        List<Group> groups = super.getGroupService().getAll();

        for(int i = 0 ; i != rootNode.size(); i++){
            String groupname = rootNode.get(i).get("groupname").toPrettyString().replace("\"","");

            groups.get(i).setGroupname(groupname);
            System.out.println(groupname);

        }


        return "";
    }
}
