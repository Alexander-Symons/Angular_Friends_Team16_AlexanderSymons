import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/ManageQuoteServlet")
public class ManageQuoteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ConsumptieRepository quoteRepository;

    public ManageQuoteServlet() {
        super();
        quoteRepository = new ConsumptieRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Consumptie> consumpties = quoteRepository.getAllConsumpties();
        String quoteJSON = this.toJSON(consumpties);
        response.setContentType("application/json");
        response.getWriter().write(quoteJSON);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quote = (String)request.getParameter("quote");
        System.out.println(quote);
        quoteRepository.addConsumptie(quote);
    }

    private String toJSON (Object quote) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(quote);
    }
    protected void searchbutton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test");
    }

}