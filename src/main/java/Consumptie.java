public class Consumptie {

    private String naam;
    private String bescchrijving;
    private String type;
    private double prijs;

    public Consumptie(String naam, String bescchrijving, String type, double prijs){
        setNaam(naam);
        setBescchrijving(bescchrijving);
        setType(type);
        setPrijs(prijs);
    }
    public Consumptie(String naam){
        setNaam(naam);
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBescchrijving() {
        return bescchrijving;
    }

    public void setBescchrijving(String bescchrijving) {
        this.bescchrijving = bescchrijving;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }
}