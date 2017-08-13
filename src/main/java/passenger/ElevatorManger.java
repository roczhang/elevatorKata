package passenger;

import common.EventInfo;
import elevator.SimpleElevator;
import listner.ElevatorEvent;
import listner.ElevatorLister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I076057 on 8/12/2017.
 */
public class ElevatorManger implements ElevatorLister {

    private final SimpleElevator elevator;

    private List<Passenger> passengerManger = new ArrayList<>();
    private List<Passenger> leavedPassenger = new ArrayList<>();

    public ElevatorManger(SimpleElevator elevator) {
        this.elevator = elevator;
        elevator.register(this);
    }

    public void addRequestPassenger(Passenger passenger) {

        if (!passengerManger.contains(passenger)) {
            passengerManger.add(passenger);
            elevator.requestFromFloor( passenger.getFrom());
        }
    }

    public void start() {
        this.elevator.run();
    }

    @Override
    public void notify(ElevatorEvent event, int floor) {

        this.enterElevator(floor);
        this.LeaveElevator(floor);
        // this.enterElevator(floor);
    }

    private void enterElevator(int floor) {

        this.passengerManger.stream().filter(passenger -> passenger.getFrom() == floor)

                .forEach(passenger -> {
                    passenger.enterElevator();
                    elevator.requestTo(passenger.getTo());
                });

    }


    private void LeaveElevator(int floor) {

        this.passengerManger.stream().filter(passenger -> passenger.isInElevator())
                .filter(passenger -> passenger.getTo() == floor)
                .forEach(passenger -> {
                    passenger.leaveElevator();
                    this.leavedPassenger.add(passenger);
                });

        this.passengerManger.removeIf(passenger -> passenger.getTo() == floor);
    }

    public void setFloor(int floor) {
        elevator.setFloor(floor);
    }


    public int size() {
        return passengerManger.size();
    }

    public List<Passenger> getLeavePerson() {
        return this.leavedPassenger;
    }




    @Override
    public List<EventInfo> getEventHistory() {
        return this.getEventHistory();
    }

}
