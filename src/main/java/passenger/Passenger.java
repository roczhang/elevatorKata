package passenger;


/**
 * Created by I076057 on 8/9/2017.
 */
public class Passenger {


    private String name;
    private int from;
    private int to;
    private boolean inElevator;

    public Passenger(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Passenger(String name) {

        this(1,1);
        this.name = name;
    }


    public void setGoal(int from, int to) {

        this.from = from;
        this.to = to;
    }

    public void requestElevator(ElevatorManger elevatorManager) {

        elevatorManager.addRequestPassenger(this);
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public void enterElevator() {
        this.inElevator = true;
    }

    public boolean isInElevator() {
        return inElevator;
    }
}


