package elevator;

import listner.ElevatorEvent;
import listner.ElevatorLister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I076057 on 8/10/2017.
 */
public class SimpleElevator {

    private final ElevatorLister listner;
    private int currentFloor;
    private List<Integer> fromFloor = new ArrayList<>();
    private List<Integer> toFloor = new ArrayList<>();


    public SimpleElevator(ElevatorLister listner) {
        this.listner = listner;
    }

    public void setFloor(int floor) {

        this.currentFloor = floor;
    }

    public void requestFloor(int from, int to) {

        this.fromFloor.add(from);
        this.toFloor.add(to);

    }

    public void run() {


        if (this.fromFloor.get(0).intValue() == this.toFloor.get(0)) {
            gotoFloor(this.currentFloor, this.toFloor.get(0));
            return;
        }

        gotoFloor(this.currentFloor, this.fromFloor.get(0));
        this.currentFloor = this.fromFloor.get(0);

        boolean isUpper = fromFloor.get(0) < toFloor.get(0);
        if (isUpper) {
            currentFloor++;
        } else {
            currentFloor--;
        }


        gotoFloor(this.currentFloor, this.toFloor.get(0));
        this.currentFloor = this.toFloor.get(0);

    }

    public void gotoFloor(int currentFloor, int floor) {
        this.fireEvent(ElevatorEvent.ARRIVED, currentFloor);

        if (currentFloor > floor) {
            currentFloor--;
        } else if (currentFloor < floor) {
            currentFloor++;
        } else {

            return;
        }

        this.gotoFloor(currentFloor, floor);

    }

    private void fireEvent(ElevatorEvent arrived, int floor) {

        if (listner != null) {
            listner.notify(arrived, floor);
        }
    }


    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getFromFloor() {
        return fromFloor.get(0);
    }

    public int getToFloor() {
        return toFloor.get(0);
    }
}
