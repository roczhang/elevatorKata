package elevator;

import common.ElevatorDirection;
import listner.ElevatorEvent;
import listner.ElevatorLister;
import passenger.Passenger;
import utility.Convert;
import utility.FindNextStopFloor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by I076057 on 8/10/2017.
 */
public class SimpleElevator {


    private List<Integer> fromFloor = new ArrayList<>();
    private List<Integer> toFloor = new ArrayList<>();

    private int currentFloor;
    private ElevatorDirection direction = ElevatorDirection.NONDIRCTION;

    private List<Passenger> passengerManager;
    private List<ElevatorLister> listnerManager = new ArrayList<>();

    public SimpleElevator(ElevatorLister listner) {

        this.register(listner);
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

        while (this.hasRequest()) {

            this.fireEvent(ElevatorEvent.ARRIVED, currentFloor);

            List<Integer> waitingPassengerRequest = getRequestFromPassengerOutSideOfElevator();
            List<Integer> inSidePassengerRequest = getRequestFromPassengerInsideOfElevator();
            int nextFloor = this.nextFloor(this.direction, this.currentFloor, waitingPassengerRequest, inSidePassengerRequest);
            this.direction = getDirection(this.currentFloor, nextFloor);

            gotoFloor(this.currentFloor, nextFloor);
            this.currentFloor = nextFloor;

            this.removeFloor(currentFloor);

            if (!this.hasRequest()) {
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

    private List<Integer> getRequestFromPassengerOutSideOfElevator() {
        List<Integer> waitingPassenger = null;
        if (this.passengerManager != null) {

            waitingPassenger = this.passengerManager.stream()
                    .filter(passenger -> !passenger.isInElevator())
                    //.filter(passenge -> passenge.getFrom() == this.currentFloor)
                    .map(passenger -> passenger.getFrom())
                    .collect(Collectors.toList());

        }
        if (waitingPassenger == null) {
            waitingPassenger = new ArrayList<>();
        }

        waitingPassenger.addAll(fromFloor);
        return waitingPassenger;
    }

    public List<Integer> getRequestFromPassengerInsideOfElevator() {
        //return this.toFloor;

        List<Integer>  insideRequst = null;
        if (this.passengerManager != null) {

            insideRequst =  this.passengerManager.stream()
                    .filter(passenger -> passenger.isInElevator())
                    .map(passenger -> passenger.getTo())
                    .collect(Collectors.toList());
        }
        if (this.passengerManager == null) {
            insideRequst = new ArrayList<>();
        }

        insideRequst.addAll(toFloor);

        return insideRequst.stream().distinct().collect(Collectors.toList());
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

        return FindNextStopFloor.find(direction, currentFloor, Convert.fromListToArray(fromFloor), Convert.fromListToArray(toFloor));
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

        if (this.passengerManager != null) {
            return this.passengerManager.size() > 0;
        } else {
            return fromFloor.size() > 0 || toFloor.size() > 0;
        }
    }

    public void gotoFloor(int currentFloor, int floor) {

        if (currentFloor == floor)
            return;

        if (currentFloor > floor) {
            currentFloor--;
        } else if (currentFloor < floor) {
            currentFloor++;
        }

        this.fireEvent(ElevatorEvent.ARRIVED, currentFloor);


        this.gotoFloor(currentFloor, floor);

    }

    private void fireEvent(ElevatorEvent arrived, int floor) {
        this.listnerManager.forEach(listner -> listner.notify(arrived, floor));
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

    public void register(ElevatorLister listner) {
        this.listnerManager.add(listner);
    }

    public void setPassengerManager(List<Passenger> passengerManager) {
        this.passengerManager = passengerManager;
    }


}
