import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ConsumptieServlet")
public class ConsumptieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ConsumptieRepository consumptieRepository;
    // test

    public ConsumptieServlet(){
        consumptieRepository = new ConsumptieRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Consumptie> consumpties = consumptieRepository.getAllConsumpties();
        String consumptieJSON = this.toJSON(consumpties);
        response.setContentType("application/json");
        response.getWriter().write(consumptieJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("naam"));
        Consumptie consumptie = new Consumptie(request.getParameter("naam"), request.getParameter("beschrijving"), request.getParameter("type"), Double.parseDouble(request.getParameter("prijs")));
        consumptieRepository.addConsumptie(consumptie);
    }

    private String toJSON (ArrayList<Consumptie> consumpties) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(consumpties);
    }
}
