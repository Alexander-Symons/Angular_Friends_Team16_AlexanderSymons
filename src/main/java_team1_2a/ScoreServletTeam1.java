import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/ScoreServletTeam1")
public class ScoreServletTeam1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ScoreRepositoryTeam1 scoreRepository;
		
	public ScoreServletTeam1() {
		super();
		scoreRepository = new ScoreRepositoryTeam1();
	}   	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeeScoreTeam1 employeeScore = scoreRepository.getTop3Quotes().get(0);
		EmployeeScoreTeam1 employeeScore2 = scoreRepository.getTop3Quotes().get(1);
		EmployeeScoreTeam1 employeeScore3 = scoreRepository.getTop3Quotes().get(2);
		String quoteJSON = this.toJSON(employeeScore);
		String quote2JSON = this.toJSON(employeeScore2);
		String quote3JSON = this.toJSON(employeeScore3);
		String allJson = "["+ quoteJSON + "," + quote2JSON +"," + quote3JSON +"]";
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().write(allJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String naam = (String)request.getParameter("naam");
		//String score = (String)request.getParameter("score");
		System.out.println(naam);
		scoreRepository.addQuote(naam);
	}

	private String toJSON (EmployeeScoreTeam1 employeeScore) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(employeeScore);
	}
		
}
