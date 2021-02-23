import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ScoreRepository a = new ScoreRepository();
        ArrayList<EmployeeScore> b = a.getTop3Quotes();
        System.out.println(b.get(0).getScore());
        System.out.println(b.get(1).getScore());
        System.out.println(b.get(2).getScore());
    }
}