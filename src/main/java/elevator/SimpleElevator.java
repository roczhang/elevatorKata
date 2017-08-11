package elevator;

import common.ElevatorDirection;
import listner.ElevatorEvent;
import listner.ElevatorLister;
import utility.FindNearestElement;

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
    private ElevatorDirection direction = ElevatorDirection.NONDIRCTION;


    public SimpleElevator(ElevatorLister listner) {
        this.listner = listner;
    }

    public void setFloor(int floor) {

        this.currentFloor = floor;
    }

    public void requestFloor(int from, int to) {

        this.addRequest(this.fromFloor, from);
        this.addRequest(this.toFloor, to);
    }

    private void addRequest(List<Integer> request, int newOne) {

        if (!request.stream().anyMatch(e -> e == newOne)) {
            request.add(newOne);
        }
    }


    public void run() {


        while (hasRequest(this.fromFloor, this.toFloor)) {

            if (this.allRequestToSameFloor()) {
                gotoFloor(this.currentFloor, this.toFloor.get(0));
                this.removeFloor(this.currentFloor);
                return;
            }

            int nextFloor = this.nextFloor(this.direction, this.currentFloor, this.fromFloor, this.toFloor);

            System.out.printf("from %d --> %d \n", currentFloor, nextFloor);

            this.direction = getDirection(this.currentFloor, nextFloor);

            gotoFloor(this.currentFloor, nextFloor);


            this.currentFloor = nextFloor;
            this.removeFloor(currentFloor);

            if (!hasRequest()) {
                break;
            }
            updateElevatorFunction();

            if (this.direction == ElevatorDirection.UPPER) {
                currentFloor++;
            } else if (this.direction == ElevatorDirection.DOWN) {
                currentFloor--;
            }
        }
    }

    private void updateElevatorFunction() {
        if (this.direction != ElevatorDirection.NONDIRCTION) {
            if (!this.hasDirectionRequest(direction, this.currentFloor)) {
                direction = this.changeDirection(direction);
            }
        }

        if (direction == ElevatorDirection.NONDIRCTION) {

            int next = this.nextFloor(direction, currentFloor, this.fromFloor, this.toFloor);
            direction = this.getDirection(currentFloor, next);
        }
    }

    private void removeFloor(int floor) {
        this.removeFromRequest(floor);
        this.removeToRequest(floor);

    }

    public int nextFloor(ElevatorDirection direction, int currentFloor, List<Integer> fromFloor, List<Integer> toFloor) {

        List<Integer> target = new ArrayList<>();

        target.addAll(fromFloor);
        target.addAll(toFloor);

        if (direction == ElevatorDirection.NONDIRCTION) {

            if( fromFloor.size() >0){
                return  FindNearestElement.find(fromFloor.stream().mapToInt(e->e).toArray(), currentFloor);
            }else{
                return  FindNearestElement.find( toFloor.stream().mapToInt(e->e).toArray(), currentFloor);
            }

        } else if (direction == ElevatorDirection.DOWN) {

            if (fromFloor.stream().anyMatch(e -> e <= currentFloor)) {

                return fromFloor.stream().distinct().filter(e -> e <= currentFloor).mapToInt(e -> e).max().getAsInt();
            } else {
                return toFloor.stream().distinct().filter(e -> e <= currentFloor).mapToInt(e -> e).max().getAsInt();
            }

        } else {

            if (fromFloor.stream().anyMatch(e -> e >= currentFloor)) {

                return fromFloor.stream().distinct().filter(e -> e >= currentFloor).mapToInt(e -> e).min().getAsInt();
            } else {
                return toFloor.stream().distinct()
                        .filter(e -> e >= currentFloor)
                        .mapToInt(e -> e).min().getAsInt();

            }

        }


    }

    private boolean allRequestToSameFloor() {

        return this.fromFloor.stream().distinct().count() == 1 &&
                this.toFloor.stream().distinct().count() == 1 &&
                this.toFloor.get(0) == this.fromFloor.get(0);
    }

    private ElevatorDirection changeDirection(ElevatorDirection direction) {
        if (direction == ElevatorDirection.UPPER) return ElevatorDirection.DOWN;
        else return ElevatorDirection.UPPER;
    }

    private boolean hasDirectionRequest(ElevatorDirection direction, int floor) {

        if (direction == ElevatorDirection.UPPER) {

            return this.fromFloor.stream().anyMatch(from -> from > floor) ||
                    this.toFloor.stream().anyMatch(to -> to > floor);

        } else {

            return this.fromFloor.stream().anyMatch(from -> from < floor) ||
                    this.toFloor.stream().anyMatch(to -> to < floor);
        }
    }

    private ElevatorDirection getDirection(int from, Integer to) {

        if (from < to) return ElevatorDirection.UPPER;
        else if (from > to) return ElevatorDirection.DOWN;
        return ElevatorDirection.NONDIRCTION;
    }

    private void removeToRequest(int floor) {

        this.toFloor.removeIf(to -> to == floor);
    }

    private void removeFromRequest(int floor) {

        this.fromFloor.removeIf(from -> from == floor);
    }

    public boolean hasRequest() {

        return this.hasRequest(this.fromFloor, this.toFloor);
    }

    public boolean hasRequest(List<Integer> fromFloor, List<Integer> toFloor) {

        return fromFloor.size() > 0 || toFloor.size() > 0;
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

    public void requestFromFloor(int from) {
        this.addRequest(this.fromFloor, from);
    }

    public void requestTo(int to) {
        this.addRequest(this.toFloor, to);
    }

    public List<Integer> getFromFloors() {
        return this.fromFloor;
    }

    public List<Integer> getToFloors() {
        return this.toFloor;
    }
}
