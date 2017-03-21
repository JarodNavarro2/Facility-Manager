package FacilityManager.model.usage;

import FacilityManager.model.facility.Facility;
import FacilityManager.model.facility.Room;
import FacilityManager.model.inspection.InspectionImpl;

import java.util.ArrayList;
import java.util.List;

public class UseImpl implements Use
{
	String use;
    public void setUse(String useage){ this.use=useage;}
    public String getUse(){ return this.use;}

    public void vacateFacility(Facility facility) {
        if (facility.getCurrentCapacity() <= 0){
            System.out.println("Facility is already vacated.");
        }
        else {
            for (Room room : facility.getRooms()){
                if (room.getRoom_current_capacity() > 0){
                    System.out.println("Room \"" + room.getRoomName() + "\" has " + room.getRoom_current_capacity() + " people in there.");
                    System.out.println("FIRE ALARM! Getting everyone out.");
                    room.setRoom_current_capacity(0);
                    if (room.getRoom_current_capacity() != 0){
                        System.out.println("Oops. Something went wrong. Someone is stuck in room " + room.getRoomName());
                    }
                    else {
                        System.out.println("Room \"" + room.getRoomName() + "\" has been cleared.");
                    }
                }
            }
        }
        if (facility.getCurrentCapacity() == 0){
            System.out.println("Facility has been vacated.");
        }
    }


    public void listActualUsage(Facility temp) {

        for(Room roo: temp.getRooms()){
            System.out.println("Room Name: "  + roo.getRoomName());
            System.out.println("Room Max Capacity: " + roo.getRoomMaxCapacity());
            System.out.println("Room Current Capacity: " + roo.getRoom_current_capacity());
            System.out.println("Room Number: " + roo.getRoomNumber());
            System.out.println("Room Usage: " + (roo.getRoom_current_capacity()/(double)roo.getRoomMaxCapacity()*100));
        }

    }
    public double calcUsageRate(Facility facility) {
        //as of 02/09 agremeent, to return total current capacity divided by max capacity
        int current = facility.getCurrentCapacity();
        int max = facility.getMaxCapacity();
        int fraction = current / max;
        double percentage = ((double)current / max) * 100;
        System.out.println("The current usage rate for facility \"" + facility.getFacilityName()
                + "\" is " + current + "/" + max + " or " + fraction + " or " + percentage + ".");
        return percentage;
    }
}