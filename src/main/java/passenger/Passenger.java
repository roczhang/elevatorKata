package passenger;


/**
 * Created by I076057 on 8/9/2017.
 */
public class Passenger {


    private int from;
    private int to;
    private boolean inElevator = false;

    public Passenger(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Passenger() {
        this(1, 1);
    }

    public void setGoal(int from, int to) {

        this.from = from;
        this.to = to;
    }


    public boolean inElevator() {
        return this.inElevator;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public void enterElevator() {
        this.inElevator = true;
    }

    public void goOutElevator() {
        this.inElevator = false;
    }

    public boolean isUpperStair() {

        if (this.from == this.to) return true;
        return from < to ? true : false;
    }


    public boolean isDownStair() {

        if (this.from == this.to) {
            return true;
        } else {
            return !isUpperStair();
        }
    }
}


