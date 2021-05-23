package controller;

import opdracht1.EmployeeScore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class zSymonsSearch extends AsyncRequesthandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int score = Integer.parseInt(request.getParameter("score"));
        ArrayList<EmployeeScore> foundScores = getScoreRepository().findbyScore(score);

        String found = "[";
        for	(int i = 0; i!= foundScores.size(); i++){
            found += "{ \"text\": \""+foundScores.get(i).getText()+"\", \"score\": \""+ foundScores.get(i).getScore()+"\" }";
            if (i!=foundScores.size()-1){
                found += ", ";
            }
        }
        found += "]";

        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return found;
    }
}