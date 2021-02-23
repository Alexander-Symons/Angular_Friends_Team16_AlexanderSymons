import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ConsumptieRepository repository = new ConsumptieRepository();
        System.out.println(repository.getRandomConsumptie().getNaam());
    }
}