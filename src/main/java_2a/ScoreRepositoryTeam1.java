import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ScoreRepositoryTeam1 {

	private List<EmployeeScoreTeam1> employeeScores = new ArrayList<EmployeeScoreTeam1>();

	public ScoreRepositoryTeam1() {
		super();
		EmployeeScoreTeam1 obama = new EmployeeScoreTeam1( "Obama", 1);
		employeeScores.add(obama);
		EmployeeScoreTeam1 king = new EmployeeScoreTeam1("pieter geens", 3);
		employeeScores.add(king);
		EmployeeScoreTeam1 x = new EmployeeScoreTeam1( "rudiy swennen", 2);
		employeeScores.add(x);
	}

	public void addQuote (String str) {
		String kept = str.substring( 0, str.indexOf(","));
		String kept2 = str.substring( str.indexOf(",")+1);

		int numb = Integer.parseInt(kept2);

		employeeScores.add(new EmployeeScoreTeam1( kept, numb));

	}

	public EmployeeScoreTeam1 getRandomQuote() {
		Random random = new Random();
		int position = random.nextInt(employeeScores.size());
		EmployeeScoreTeam1 employeeScore = employeeScores.get(position);
		return employeeScore;
	}
	public ArrayList<EmployeeScoreTeam1> getTop3Quotes(){
		ArrayList<EmployeeScoreTeam1> b = new ArrayList<>(employeeScores);
		ArrayList<EmployeeScoreTeam1> top3 = new ArrayList<>();
		Collections.sort(b);
		top3.add(b.get(0));
		top3.add(b.get(1));
		top3.add(b.get(2));
		Collections.sort(top3);
		return top3;
	}


}
