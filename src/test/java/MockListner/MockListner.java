package MockListner;

import common.EventInfo;
import listner.ElevatorEvent;
import listner.ElevatorLister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by I076057 on 8/10/2017.
 */
public class MockListner implements ElevatorLister {
    private List<EventInfo> history = new ArrayList<>();

    @Override
    public void notify(ElevatorEvent event, int floor) {

        history.add( new EventInfo(event, floor));
    }

    @Override
    public List<EventInfo> getEventHistory() {
        return this.history;
    }
}
