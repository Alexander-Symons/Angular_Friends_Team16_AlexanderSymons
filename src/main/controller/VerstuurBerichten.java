package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import domain.Bericht;
import domain.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/VerstuurBerichten")
public class VerstuurBerichten extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person user = (Person) session.getAttribute("user");
        String ontvangerstring = request.getParameter("ontvanger");
        Person ontvanger = user.getVriendByName(ontvangerstring);
        StringBuffer json = new StringBuffer();
        if (user.getBerichten(ontvanger) != null) {

            String friendsJSON = toJSON(user.getBerichten(ontvanger));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(friendsJSON);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person user =(Person)session.getAttribute("user");

        String ontvangerstring = request.getParameter("ontvanger");
        Person ontvanger = user.getVriendByName(ontvangerstring);

        String bericht = request.getParameter("tekst");

        if(ontvanger != null){
            user.addMessage(user, ontvanger, bericht);
            ontvanger.addMessage(user, user, bericht);
        }
    }

    private String toJSON (List<Bericht> berichts) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(berichts);
        return json;
    }

}
