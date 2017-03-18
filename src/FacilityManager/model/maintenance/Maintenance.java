package FacilityManager.model.maintenance;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Maintenance {

    public MaintenanceImpl getMaintenanceRequest(String name);
    public int getSchedule_time();
    public void setSchedule_time(int time);
    public int getTotal_cost();
    public void setTotal_cost(int cost);
    public int getTotal_budget();
    public void setTotal_budget(int budget);
    public void setRequest_approval(boolean req);
    public boolean getRequest_approval();
    public void setProblem_approval(boolean prob);
    public boolean getProblem_approval();
    public void setProblem(String problem);
    public String getProblem();
    public void setMoney_approval(boolean mon);
    public boolean getMoney_approval();
    public void setStatus(FacilityManager.model.maintenance.MaintenanceImpl.status input);
    public FacilityManager.model.maintenance.MaintenanceImpl.status getStatus();

    public void setMainenanceName(String s);
    public String getMaintenance_name();

    public void setMaintenanceRequestBeginTime();

    public LocalDateTime getMaintenanceRequestBeginTime();

    public void setMaintenanceRequestCompletionTime();

    public LocalDateTime getMaintenanceRequestCompletionTime();

}
