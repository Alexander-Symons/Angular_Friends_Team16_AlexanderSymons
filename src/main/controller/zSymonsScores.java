package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class zSymonsScores extends RequestHandler{



    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String scores = "[";
        for	(int i = 0; i!= getScoreRepository().getTop3Quotes().size(); i++){
            scores += "{ \"text\": \""+getScoreRepository().getTop3Quotes().get(i).getText()+"\"}";

            if (i!=getScoreRepository().getTop3Quotes().size()-1){
                scores += ", ";
            }
        }
        scores += "]";
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        return scores;
    }

}