import com.fasterxml.jackson.annotation.JsonIgnore;

public class Quote implements Comparable<Quote> {
	
	private String author;
	private String text;
	private int score;
	@JsonIgnore
	private int year;
	
	public Quote(String author, String text, int year) {
		super();
		setAuthor(author);
		setText(text);
		setYear(year);
	}
	public Quote(String author, String text, int year, int score) {
		super();
		setAuthor(author);
		setText(text);
		setYear(year);
		setScore(score);
	}


	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Quote o) {
		if(this.getScore() <= o.getScore()){
			return 1;
		}
		else return -1;
	}
}
