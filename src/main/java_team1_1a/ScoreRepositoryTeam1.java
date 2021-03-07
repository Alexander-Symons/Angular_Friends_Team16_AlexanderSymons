import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ScoreRepositoryTeam1 {

	private ArrayList<EmployeeScoreTeam1> employeeScores;

	public ScoreRepositoryTeam1() {
		this.employeeScores = new ArrayList<>();
		EmployeeScoreTeam1 obama = new EmployeeScoreTeam1( "Obama", 1);
		employeeScores.add(obama);
		EmployeeScoreTeam1 king = new EmployeeScoreTeam1("pieter geens", 3);
		employeeScores.add(king);
		EmployeeScoreTeam1 x = new EmployeeScoreTeam1( "rudiy swennen", 2);
		employeeScores.add(x);
	}

	public void addEmployee (String str) {
		String kept = str.substring( 0, str.indexOf(","));
		String kept2 = str.substring( str.indexOf(",")+1);

		int numb = Integer.parseInt(kept2);

		employeeScores.add(new EmployeeScoreTeam1( kept, numb));
	}


	/**@Author Robbe Jacobs**/
	public ArrayList<EmployeeScoreTeam1> employees(String zoekwoord){
		zoekwoord = zoekwoord.replace('"',' ').trim();
		ArrayList<EmployeeScoreTeam1> gevonden = new ArrayList<>();
		for (EmployeeScoreTeam1 c : employeeScores){
			if (c.getText().contains(zoekwoord)){
				gevonden.add(c);
			}
		}
		return gevonden;
	}

	public ArrayList<EmployeeScoreTeam1> getTop3Employees(){
		ArrayList<EmployeeScoreTeam1> b = new ArrayList<>(employeeScores);
		ArrayList<EmployeeScoreTeam1> top3 = new ArrayList<>();
		Collections.sort(b);
		top3.add(b.get(0));
		top3.add(b.get(1));
		top3.add(b.get(2));
		Collections.sort(top3);
		return top3;
	}
	/**@Author Arno Piersoul**/
	public ArrayList<EmployeeScoreTeam1> employeesMinimumScore(int score){
		ArrayList<EmployeeScoreTeam1> emps = new ArrayList<>();
		for (EmployeeScoreTeam1 c : employeeScores){
			if (c.getScore() >= score){
				emps.add(c);
			}
		}
		return emps;
	}
}
