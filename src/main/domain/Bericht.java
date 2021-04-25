package domain;

public class Bericht {
    private Person zender;
    private String message;

    public Bericht(Person zender, String message){
        setZender(zender);
        setMessage(message);
    }

    public Person getZender() {
        return zender;
    }

    public void setZender(Person zender) {
        this.zender = zender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
