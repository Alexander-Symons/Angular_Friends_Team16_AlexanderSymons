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
	private QuoteRepository quoteRepository;
		
	public ManageQuoteServlet() {
		super();
		quoteRepository = new QuoteRepository();
	}   	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Quote quote = quoteRepository.getTop3Quotes().get(0);
		Quote quote2 = quoteRepository.getTop3Quotes().get(1);
		Quote quote3 = quoteRepository.getTop3Quotes().get(2);
		String quoteJSON = this.toJSON(quote);
		String quote2JSON = this.toJSON(quote2);
		String quote3JSON = this.toJSON(quote3);
		String allJson = "["+ quoteJSON + "," + quote2JSON +"," + quote3JSON +"]";
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().write(allJson);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quote = (String)request.getParameter("quote");
		System.out.println(quote);
		quoteRepository.addQuote(quote);
	}

	private String toJSON (Quote quote) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(quote);
	}
		
}
