package controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Group;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class zArtoisDelete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String groupname = request.getParameter("groupname");
        List<Group> groups = getGroupService().getAll();
//        for(int i = 0; i< groups.size(); i++){
            getGroupService().getGroupRepository().delete(groupname);
//        }

        System.out.println(groupname);


        return "";
    }
}
