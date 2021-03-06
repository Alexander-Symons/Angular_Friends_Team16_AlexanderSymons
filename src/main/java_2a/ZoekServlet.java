import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ZoekServlet")
public class ZoekServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConsumptieRepository consumptieRepository;
    // test

    public ZoekServlet(){
        consumptieRepository = new ConsumptieRepository();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String toJSON (ArrayList<Consumptie> consumpties) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(consumpties);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        String result;
        try {
            JsonObject jobj = new Gson().fromJson(jb.toString(), JsonObject.class);
            result = jobj.get("zoekwoord").toString();
            System.out.println(result);
        } catch (Exception e) {
            // crash and burn
            throw new IOException(e.getMessage());
        }
        if (result != null){
            ArrayList<Consumptie> gevonden = consumptieRepository.zoekConsumpties(result);
        }
    }
}
