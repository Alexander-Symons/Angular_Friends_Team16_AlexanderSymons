import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuoteRepository {

	private List<Quote> quotes = new ArrayList<Quote>();

	public QuoteRepository() {
		super();
		Quote obama = new Quote("Obama", "Obama", 2009, 1);
		quotes.add(obama);
		Quote king = new Quote("Martin Luther King", "pieter geens", 1950, 3);
		quotes.add(king);
		Quote x = new Quote("XXX", "rudiy swennen", 1945, 2);
		quotes.add(x);
	}

	public void addQuote (String str) {
		String kept = str.substring( 0, str.indexOf(","));
		String kept2 = str.substring( str.indexOf(",")+1);

		int numb = Integer.parseInt(kept2);

		quotes.add(new Quote("Elke Steegmans", kept, 2020, numb));

	}

	public Quote getRandomQuote() {
		Random random = new Random();
		int position = random.nextInt(quotes.size());
		Quote quote = quotes.get(position);
		return quote;
	}
	public ArrayList<Quote> getTop3Quotes(){
		ArrayList<Quote> b = new ArrayList<>(quotes);
		ArrayList<Quote> top3 = new ArrayList<>();
		Collections.sort(b);
		top3.add(b.get(0));
		top3.add(b.get(1));
		top3.add(b.get(2));
		Collections.sort(top3);
		return top3;
	}


}
