package controller;


import opdracht1.EmployeeScore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class zSymonsDelete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String name = request.getParameter("name");
        int score = Integer.parseInt(request.getParameter("score"));

        EmployeeScore delete = new EmployeeScore(name, score);
        getScoreRepository().deleteEmployeeScore(delete);

        return "";
    }
}