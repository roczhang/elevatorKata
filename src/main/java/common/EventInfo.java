package common;

import listner.ElevatorEvent;

/**
 * Created by I076057 on 8/10/2017.
 */
public class EventInfo {

    private final ElevatorEvent event;
    private int floor;

    public EventInfo(ElevatorEvent event, int floor) {
        this.event = event;
        this.floor = floor;
    }

    public void setInfo(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public ElevatorEvent getEvent() {
        return event;
    }
}
