import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/ScoreServlet")
public class ScoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ScoreRepository scoreRepository;
		
	public ScoreServlet() {
		super();
		scoreRepository = new ScoreRepository();
	}   	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = "w";
		if(request.getParameter("command") != null) {
			command = request.getParameter("command");
		}
		switch (command) {
			default:
			EmployeeScore employeeScore = scoreRepository.getTop3Quotes().get(0);
			EmployeeScore employeeScore2 = scoreRepository.getTop3Quotes().get(1);
			EmployeeScore employeeScore3 = scoreRepository.getTop3Quotes().get(2);
			String quoteJSON = this.toJSON(employeeScore);
			String quote2JSON = this.toJSON(employeeScore2);
			String quote3JSON = this.toJSON(employeeScore3);
			String allJson = "[" + quoteJSON + "," + quote2JSON + "," + quote3JSON + "]";
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			response.getWriter().write(allJson);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quote = (String)request.getParameter("quote");
		System.out.println(quote);
		scoreRepository.addQuote(quote);
	}

	private String toJSON (EmployeeScore employeeScore) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(employeeScore);
	}
		
}
