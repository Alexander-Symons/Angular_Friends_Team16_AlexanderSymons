import java.util.ArrayList;

public class ConsumptieRepositoryTeam1 {

    private ArrayList<ConsumptieTeam1> consumptieList;

    public ConsumptieRepositoryTeam1(){
        this.consumptieList = new ArrayList<>();
        ConsumptieTeam1 spaghetti = new ConsumptieTeam1("spaghetti", "Italiaanse pasta met bolognaise saus", "Pasta", 7.50);
        ConsumptieTeam1 pizza_special = new ConsumptieTeam1("Pizza Special", "Een goed belegde pizza", "Pizza", 10.75);
        ConsumptieTeam1 cola = new ConsumptieTeam1("cola", "Een zoete drank", "Soft drink", 2.00);
        ConsumptieTeam1 pizza_hawai = new ConsumptieTeam1("Pizza hawa&#239;", "Een pizza met ham en ananas", "Pizza", 9.50);
        consumptieList.add(spaghetti);
        consumptieList.add(pizza_special);
        consumptieList.add(cola);
        consumptieList.add(pizza_hawai);
    }

    public void addConsumptie(ConsumptieTeam1 consumptie){
        this.consumptieList.add(consumptie);
    }


    public ArrayList<ConsumptieTeam1> getAllConsumpties(){
        return consumptieList;
    }
}
