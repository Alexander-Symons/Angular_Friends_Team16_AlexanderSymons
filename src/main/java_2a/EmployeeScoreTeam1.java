import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmployeeScoreTeam1 implements Comparable<EmployeeScoreTeam1> {

	private String text;
	private int score;
	@JsonIgnore
	private int nothing;

	public EmployeeScoreTeam1(String text, int score) {
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
	public int compareTo(EmployeeScoreTeam1 o) {
		if(this.getScore() <= o.getScore()){
			return 1;
		}
		else return -1;
	}
}
