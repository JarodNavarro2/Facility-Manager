package FacilityManager.model.usage;
import FacilityManager.model.facility.Facility;
import FacilityManager.model.facility.Room;
import FacilityManager.model.maintenance.Inspection;


import java.util.ArrayList;

/**
 * Created by carlreider on 3/18/17.
 */
public class useFunction extends Usage {

    public boolean vacateFacility(String facility_name, ArrayList<Facility> facility_list) {
        facility_name = facility_name.toLowerCase();
        int facility_capacity;
        boolean success_token = false;
        for (Facility object : facility_list) {
            if (object.getFacilityName().toLowerCase().matches(facility_name)) {
                for (Room temp_room : object.rooms) {
                    if (!temp_room.setRoom_current_capacity(0)) {
                        System.out.println("Unable to vacate. Someone stuck in room "
                                + temp_room.getRoomName() + ".");
                    }
                }
                facility_capacity = object.getCurrentCapacity(); //checks to confirm that all rooms in facility = 0
                if (facility_capacity == 0) {
                    success_token = true;
                    System.out.println("Everyone is evacuated");
                    this.usage = "Vacant";
                    break;
                } else {
                    success_token = false;
                }
            } else {
                System.out.println("Unable to vacate." + "\n" +
                        "No facility found with that name.");
                success_token = false;
            }
        }
        return success_token;
    }


    public boolean listInspections(ArrayList<Inspection> inspectionList) {
        int counter = 1;
        boolean success_token;
        if (inspectionList.size() > 0) {
            for (Inspection inspection : inspectionList) {
                System.out.println("Listing information for inspection #" + counter + " out of " + inspectionList.size() + ".");
                System.out.println("Inspector name: " + inspection.getInspection_inspector_name());
                System.out.println("Inspection created on: " + inspection.getInspection_request_date() +
                        " " + inspection.getInspection_request_time());
                System.out.println("Inspection scheduled for: " + inspection.getInspection_date() +
                        " " + inspection.getInspection_time());
                System.out.println("Inspection reason: " + inspection.getInspection_issue());
                System.out.println("Inspection passed (true/false): " + inspection.getInspection_passed().toString());
                System.out.println("Facility in use?: "+ inspection.isInUseDuringInterval());
            }
            success_token = true;
        } else {
            System.out.println("Ooops! It appears that the inspection list is empty.");
            success_token = false;
        }
        return success_token;
    }


    public void listActualUsage(Facility temp) {
        ArrayList<Room> rooms = temp.rooms;
        for(Room roo: rooms){
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
