package FacilityManager.model.facility;

import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.usage.Usage;
import FacilityManager.model.usage.useFunction;

public interface Facility {


    public Usage getFacilityUsage();

    public void setRoom(Room room);

    public Room getRoom(int room_number); //obtains room object based on specific room_number

    public String getFacilityName();
    public void setFacilityName(String newName);

    public boolean bulldozeARoom(int number);
    public void setStatus(String input_status);
    public boolean getOccupancy();
    public String getStatus();
    public void getFacilityInspections();
    public void createInspection();

    public void getFacilityInformation();
    public void getFacilityRoomsInfo();
    public int getMaxCapacity();
    public int getCurrentCapacity();

}
