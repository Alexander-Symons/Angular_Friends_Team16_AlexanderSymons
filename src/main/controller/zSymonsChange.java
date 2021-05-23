package controller;

import opdracht1.EmployeeScore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class zSymonsChange extends RequestHandler{



    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String name = request.getParameter("name");
        int oldscore = Integer.parseInt(request.getParameter("oldscore"));
        int score = Integer.parseInt(request.getParameter("score"));

        EmployeeScore edit = new EmployeeScore(name, oldscore);
        getScoreRepository().deleteEmployeeScore(edit);
        EmployeeScore newscore = new EmployeeScore(name, score);
        getScoreRepository().addEmployeeScore(newscore);

        return "";
    }

}
