package passenger;

import common.EventInfo;
import elevator.Elevator;
import listner.ElevatorEvent;
import listner.ElevatorLister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I076057 on 8/12/2017.
 */
public class ElevatorController implements ElevatorLister {

    private final Elevator elevator;
    private int weightLimitation;

    private List<Passenger> passengerManger = new ArrayList<>();
    private List<Passenger> leavedPassenger = new ArrayList<>();


    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
        elevator.register(this);
        elevator.setPassengerManager(passengerManger);
        this.weightLimitation = 150;
    }

    public void setFloor(int floor) {
        elevator.setFloor(floor);
    }

    public void setWeightLimitation(int weightLimitation) {
        this.weightLimitation = weightLimitation;
    }

    public void addRequestPassenger(Passenger passenger) {

        if (!passengerManger.contains(passenger)) {
            passengerManger.add(passenger);
        }
    }

    public void start() {
        this.elevator.run();
    }

    @Override
    public void notify(ElevatorEvent event, int floor) {

        this.enterElevator(floor);
        this.LeaveElevator(floor);
    }

    private void enterElevator(int floor) {

        this.passengerManger.stream()
                .filter(passenger -> passenger.isOutElevator())
                .filter(passenger -> passenger.getFrom() == floor)
                .forEach(passenger -> {

                    int currentWeight = getWeightOnPassengerInFloor(passengerManger);
                    if (currentWeight + passenger.getWeight() < this.weightLimitation) {
                        passenger.enterElevator();
                        elevator.requestTo(passenger.getTo());
                    }
                });
    }

    private void LeaveElevator(int floor) {

        List<Passenger> offElevatorPassenger = new ArrayList<>();
        this.passengerManger.stream().filter(passenger -> passenger.isInElevator())
                .filter(passenger -> passenger.getTo() == floor)
                .forEach(passenger -> {
                    passenger.leaveElevator();
                    this.leavedPassenger.add(passenger);
                    offElevatorPassenger.add(passenger);
                });

        offElevatorPassenger.forEach(passenger -> this.passengerManger.remove(passenger));
    }

    private int getWeightOnPassengerInFloor(List<Passenger> passengerManger) {
        return this.passengerManger.stream()
                .filter(passenger -> passenger.isInElevator())
                .map(passenger -> passenger.getWeight())
                .reduce(0, (a, b) -> a + b);

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
