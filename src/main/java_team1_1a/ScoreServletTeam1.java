import java.io.IOException;
import java.util.ArrayList;
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
		scoreRepository = new ScoreRepositoryTeam1();
	}   	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request(request,response);
	}

	private void request(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String command = "w";
		if(request.getParameter("command") != null) {
			command = request.getParameter("command");
		}
		switch (command) {
			case "addEmployee":
				String naam = (String)request.getParameter("naam");
				scoreRepository.addEmployee(naam);
				break;
			case "showEmployee":
				String quoteJSON = this.toJSON(scoreRepository.getTop3Employees());
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json");
				response.getWriter().write(quoteJSON);
				break;


				/**@Author Robbe Jacobs**/
			case "zoekEmployee":
				String zoekwoord = request.getParameter("zoekwoord");
				ArrayList<EmployeeScoreTeam1> gevonden = scoreRepository.employees(zoekwoord);
				String quoteJSON1 = this.toJSON(gevonden);
				response.setContentType("application/json");
				response.getWriter().write(quoteJSON1);
				break;

			/**@Author Arno Piersoul**/
			case "employeesMinimum":
				String score = null;
				score = request.getParameter("score");
				try {
					int scoreInt = Integer.parseInt(score);
					ArrayList<EmployeeScoreTeam1> emps = scoreRepository.employeesMinimumScore(scoreInt);
					String minJson = this.toJSON(emps);
					response.setContentType("application/json");
					response.getWriter().write(minJson);
				}
				catch (Exception e){
					System.out.println("error");
				}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request(request,response);
	}

	private String toJSON (ArrayList<EmployeeScoreTeam1> employeeScore) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(employeeScore);
	}
		
}
