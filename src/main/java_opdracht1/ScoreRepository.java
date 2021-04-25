package java_opdracht1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ScoreRepository {

	private List<EmployeeScore> employeeScores = new ArrayList<EmployeeScore>();

	public ScoreRepository() {
		super();
		EmployeeScore obama = new EmployeeScore( "Obama", 1);
		employeeScores.add(obama);
		EmployeeScore king = new EmployeeScore("pieter geens", 3);
		employeeScores.add(king);
		EmployeeScore x = new EmployeeScore( "rudiy swennen", 2);
		employeeScores.add(x);
	}

	public void addQuote (String str) {
		System.out.println(str);
		String kept = str.substring( 0, str.indexOf(","));
		String kept2 = str.substring( str.indexOf(",")+1);

		int numb = Integer.parseInt(kept2);

		employeeScores.add(new EmployeeScore( kept, numb));

	}
	public void addEmployeeScore(EmployeeScore employeeScore){
		employeeScores.add(employeeScore);
	}

	public EmployeeScore getRandomQuote() {
		Random random = new Random();
		int position = random.nextInt(employeeScores.size());
		EmployeeScore employeeScore = employeeScores.get(position);
		return employeeScore;
	}
	public ArrayList<EmployeeScore> getTop3Quotes(){
		ArrayList<EmployeeScore> b = new ArrayList<>(employeeScores);
		ArrayList<EmployeeScore> top3 = new ArrayList<>();
		Collections.sort(b);
		top3.add(b.get(0));
		top3.add(b.get(1));
		top3.add(b.get(2));
		Collections.sort(top3);
		return top3;
	}


}
