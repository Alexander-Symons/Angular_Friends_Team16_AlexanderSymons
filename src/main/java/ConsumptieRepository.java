
import java.util.ArrayList;
import java.util.List;

public class ConsumptieRepository {

    private ArrayList<Consumptie> consumptieList;

    public ConsumptieRepository(){
        this.consumptieList = new ArrayList<>();
        Consumptie spaghetti = new Consumptie("spaghetti", "Italiaanse pasta met bolognaise saus", "Pasta", 7.50);
        Consumptie pizza_special = new Consumptie("Pizza Special", "Een goed belegde pizza", "Pizza", 10.75);
        Consumptie cola = new Consumptie("cola", "Een zoete drank", "Soft drink", 2.00);
        Consumptie pizza_hawai = new Consumptie("Pizza hawa&#239;", "Een pizza met ham en ananas", "Pizza", 9.50);
        consumptieList.add(spaghetti);
        consumptieList.add(pizza_special);
        consumptieList.add(cola);
        consumptieList.add(pizza_hawai);
    }

    public void addConsumptie(Consumptie consumptie){
        this.consumptieList.add(consumptie);
    }

    public ArrayList<Consumptie> getAllConsumpties(){
        return consumptieList;
    }
    public Consumptie getRandomConsumptie(){
        int n = consumptieList.size();
        //Random rn = new Random();
        //int i = rn.nextInt() % n;
        return consumptieList.get((int)(Math.random() * n));
    }
}