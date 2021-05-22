package controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Group;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.*;




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
