package FacilityManager.model.usage;

import FacilityManager.model.facility.Facility;

public interface Use
{
    public void setUse(String useage);
    public String getUse();
    public void vacateFacility(Facility facility);
    public void listActualUsage(Facility temp);
    public double calcUsageRate(Facility facility);
}