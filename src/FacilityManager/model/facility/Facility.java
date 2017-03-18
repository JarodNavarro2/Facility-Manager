package FacilityManager.model.facility;

import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.usage.Usage;
import FacilityManager.model.usage.useFunction;

public interface Facility {


    public Usage getFacilityUsage(); //TODO:

    public void setRoom(Room room);

    public Room getRoom(int room_number); //obtains room object based on specific room_number

    public String getFacilityName(); //works -- DK 03.18
    public void setFacilityName(String newName); //works -- DK 03.18

    public boolean bulldozeARoom(int number); //works -- DK 03.18
    public void setStatus(String input_status); //works -- DK 03.18
    public String getStatus(); //works -- DK 03.18
    public boolean getOccupancy(); //works -- DK 03.18
    public void setOccupancy(boolean occupancy); //works -- DK 03.18

    public void getFacilityInspections(); //TODO
    public void createInspection(); //TODO

    public void getFacilityInformation(); //works -- DK 03.18
    public void getFacilityRoomsInfo(); //works -- DK 03.18
    public int getMaxCapacity(); //works -- DK 03.18
    public int getCurrentCapacity(); //works -- DK 03.18

}
