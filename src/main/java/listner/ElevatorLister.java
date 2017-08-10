package listner;

import common.EventInfo;

import java.util.List;

/**
 * Created by I076057 on 8/10/2017.
 */
public interface ElevatorLister {
    public void notify(ElevatorEvent event, int elevator);
    List<EventInfo> getEventHistory();
}
