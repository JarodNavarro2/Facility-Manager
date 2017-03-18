package FacilityManager.model.facility;

import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.usage.Usage;
import FacilityManager.model.usage.useFunction;

public interface Facility {
    public Usage getFacilityUsage();
    public Room getRoom(int room_number);
    public String getFacilityInformation();
    public String getFacilityName();
    public void setFacilityName(String newName);
    public int getMaxCapacity();
    public int getCurrentCapacity();
    public boolean createARoom(String name, int capacity, int max_capacity, int number);
    public boolean roomNumberCheck(int number);
    public boolean bulldozeARoom(int number);
    public void setStatus(String input_status);
    public boolean getOccupancy();
    public String getStatus();
    public void getFacilityInspections();
    public void createInspection();
}
