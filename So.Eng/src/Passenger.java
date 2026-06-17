public class Passenger {
    //change the fields into final
    private final String name;

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //override
    @Override
    public String toString() {
        return name;
    }
}
