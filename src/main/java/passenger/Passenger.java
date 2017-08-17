package passenger;


import elevator.ElevatorController;

/**
 * Created by I076057 on 8/9/2017.
 */
public class Passenger {


    private final int weight;
    private String name;
    private int from;
    private int to;
    private boolean inElevator = false;

    public Passenger(int from, int to) {

        this("", 1, 1);
    }

    public Passenger(String name, int from, int to) {

        this(name, from, to, 1);
    }

    public Passenger(String name, int from, int to, int weight) {

        this.name = name;
        this.from = from;
        this.to = to;
        this.weight = weight;

    }


    public void setGoal(int from, int to) {

        this.from = from;
        this.to = to;
    }

    public void requestElevator(ElevatorController elevatorManager) {

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


    public void leaveElevator() {
        this.inElevator = false;
    }

    public boolean isInElevator() {
        return inElevator;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isOutElevator() {
        return !this.isInElevator();
    }
}


