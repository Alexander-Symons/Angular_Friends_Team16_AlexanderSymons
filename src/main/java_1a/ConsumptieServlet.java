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

@WebServlet("/ConsumptieServlet")
public class ConsumptieServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ConsumptieRepository quoteRepository;

    public ConsumptieServlet() {
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
            case "search":
                ArrayList<Consumptie> pizzas = quoteRepository.getAllPizzas();
                String quoteJSON3 = this.toJSON(pizzas);
                response.setContentType("application/json");
                response.getWriter().write(quoteJSON3);
                break;
            case "edit":
                String editfoodname = (String)request.getParameter("foodname");
                String editfooddescription = (String)request.getParameter("fooddescription");
                String editfoodtype = (String)request.getParameter("foodtype");
                Double editfoodprice = Double.parseDouble(request.getParameter("foodprice"));
                System.out.println("number");
                System.out.println(request.getParameter("number"));
                int editnumber = Integer.parseInt(request.getParameter("number"));
                String text = request.getParameter("quote");
                System.out.println(text);
                quoteRepository.getAllConsumpties().get(editnumber).setNaam(editfoodname);
                quoteRepository.getAllConsumpties().get(editnumber).setBeschrijving(editfooddescription);
                quoteRepository.getAllConsumpties().get(editnumber).setType(editfoodtype);
                quoteRepository.getAllConsumpties().get(editnumber).setPrijs(editfoodprice);
                break;
            case "editpage":
                System.out.println(request.getParameter("number"));
                Consumptie c =quoteRepository.getConsumptie(Integer.parseInt(request.getParameter("number")));
                String quoteJSON = this.toJSON(c);
                response.setContentType("application/json");
                response.getWriter().write(quoteJSON);
                break;
            case "post":
                String foodname = (String)request.getParameter("foodname");
                String fooddescription = (String)request.getParameter("fooddescription");
                String foodtype = (String)request.getParameter("foodtype");
                String foodprice = (String)request.getParameter("foodprice");
                Consumptie consumptie = new Consumptie(foodname,fooddescription,foodtype,Double. parseDouble(foodprice));
                quoteRepository.addConsumptie(consumptie);
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