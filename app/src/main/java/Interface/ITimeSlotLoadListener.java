package Interface;

import java.util.List;

import Pojos.TimeSlot;

public interface ITimeSlotLoadListener {
    void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotMorningList);
    void onTimeSlotLoadFailure(String message);
    void onTimeSlotLoadEmpty();
}
