package controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Group;
import domain.Person;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class zArtoisCreate extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String groupname = request.getParameter("groupname");
        String member1string = request.getParameter("member1");
        String member2string = request.getParameter("member2");
        String member3string = request.getParameter("member3");
        String member4string = request.getParameter("member4");
        Person member1 = getPersonService().findPerson(member1string);
        Person member2 = getPersonService().findPerson(member2string);
        Person member3 = getPersonService().findPerson(member3string);
        Person member4 = getPersonService().findPerson(member4string);
        ArrayList<String> errors = new ArrayList<>();
        if (member1 == null) { errors.add("member 1 doesn't exist."); }
        if (member2 == null) { errors.add("member 2 doesn't exist."); }
        if (member3 == null) { errors.add("member 3 doesn't exist."); }
        if (member4 == null) { errors.add("member 4 doesn't exist."); }
        if(groupname.trim().isEmpty()){errors.add("groupname is empty.");}
        System.out.println("1");
        if(errors.size() == 0) {
            System.out.println("2");
            List<Group> groups = getGroupService().getAll();
            Group group = new Group(groupname);
            group.addGroupmember(member1);
            group.addGroupmember(member2);
            group.addGroupmember(member3);
            group.addGroupmember(member4);
            getGroupService().getGroupRepository().add(group);

            System.out.println(groupname);

            return "";
        }
        else{
            return "";
        }
    }
}
