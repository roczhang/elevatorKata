package elevator;

import listner.ElevatorEvent;
import listner.ElevatorLister;

/**
 * Created by I076057 on 8/10/2017.
 */
public class SimpleElevator {

    private final ElevatorLister listner;
    private int currentFloor;
    private int fromFloor;
    private int toFloor;


    public SimpleElevator(ElevatorLister listner) {
        this.listner = listner;
    }

    public void setFloor(int floor) {

        this.currentFloor = floor;
    }

    public void requestFloor(int from, int to) {

        this.fromFloor = from;
        this.toFloor = to;

    }

    public void run() {

        gotoFloor(this.currentFloor, this.fromFloor);
        this.currentFloor = this.fromFloor;


        gotoFloor(this.currentFloor, this.toFloor);
        this.currentFloor = this.toFloor;


    }

    public void gotoFloor(int currentFloor, int floor) {
        this.fireEvent(ElevatorEvent.ARRIVED, this);

        if (currentFloor > floor) {

            currentFloor++;

        } else if (currentFloor < floor) {

            currentFloor--;
        } else {

            return;
        }

        this.gotoFloor(currentFloor, floor);

    }

    private void fireEvent(ElevatorEvent arrived, SimpleElevator elevator) {

        listner.notify(arrived, elevator);
    }


    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getFromFloor() {
        return fromFloor;
    }

    public int getToFloor() {
        return toFloor;
    }
}
