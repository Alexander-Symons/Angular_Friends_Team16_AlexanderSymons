import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
        request(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request(request, response);
    }
    protected void request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = "w";
        if(request.getParameter("command") != null) {
            command = request.getParameter("command");
        }
        switch (command) {
            case "edit":
                System.out.println("number");
                System.out.println(request.getParameter("number"));
                int editnumber = Integer.parseInt(request.getParameter("number"));
                String text = request.getParameter("quote");
                System.out.println(text);
                quoteRepository.getAllConsumpties().get(editnumber).setNaam(text);
                break;
            case "editpage":
                System.out.println(request.getParameter("number"));
                Consumptie c =quoteRepository.getConsumptie(Integer.parseInt(request.getParameter("number")));
                String quoteJSON = this.toJSON(c);
                response.setContentType("application/json");
                response.getWriter().write(quoteJSON);
                break;
            case "post":
                String quote = (String)request.getParameter("quote");
                System.out.println(quote);
                quoteRepository.addConsumptie(quote);
                break;
            case "delete":
                ArrayList<Consumptie> deletelist = quoteRepository.getAllConsumpties();
                int i = Integer.parseInt(request.getParameter("number"));
                System.out.println(i);
                deletelist.remove(i);
                quoteRepository.setAllConsumpties(deletelist);
                break;

            default:
                ArrayList<Consumptie> consumpties = quoteRepository.getAllConsumpties();
                String quoteJSON2 = this.toJSON(consumpties);
                response.setContentType("application/json");
                response.getWriter().write(quoteJSON2);
        }

    }

    private String toJSON (Object quote) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(quote);
    }
    protected void searchbutton(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("test");
    }

}