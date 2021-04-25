package java_opdracht1;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeScore implements Comparable<EmployeeScore> {

	private String text;
	private int score;
	@JsonIgnore
	private int nothing;

	public EmployeeScore(String text, int score) {
		super();
		setText(text);
		setScore(score);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(EmployeeScore o) {
		if(this.getScore() <= o.getScore()){
			return 1;
		}
		else return -1;
	}
}
