package FacilityManager.model.facility;

import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.usage.Usage;

import java.util.ArrayList;
import java.util.List;

public interface Facility {


    public Usage getFacilityUsage(); //TODO:

    public void setRooms(List<Room> rooms);
    public List<Room> getRooms(); //obtains room object based on specific room_number

    public String getFacilityName(); //works -- DK 03.18
    public void setFacilityName(String newName); //works -- DK 03.18

    public boolean bulldozeARoom(int number); //works -- DK 03.18
    public void setStatus(String input_status); //works -- DK 03.18
    public String getStatus(); //works -- DK 03.18
    public boolean getOccupancy(); //works -- DK 03.18
    public void setOccupancy(boolean occupancy); //works -- DK 03.18

    public void setInspections(Inspection inspection);

    public Inspection getInspection();

    public void getFacilityInformation(); //works -- DK 03.18
    public void getFacilityRoomsInfo(); //works -- DK 03.18
    public int getMaxCapacity(); //works -- DK 03.18
    public int getCurrentCapacity(); //works -- DK 03.18

    public void setMaintenance(Maintenance main);

    public Maintenance getMaintenance();

    public ArrayList<Maintenance> getMaintenanceLog();


}
