package FacilityManager.model.inspection;


import java.time.LocalDate;
import java.time.LocalTime;

public interface Inspection {
    public void setInspector_name(String name);
    public void setInspection_date(LocalDate date);
    public void setInspection_request_date(LocalDate date);
    public void setInspection_time(LocalTime time);
    public void setInspection_request_time(LocalTime time);
    public void setIssue(String issue);
    public void setInspection_passed(Boolean passed);

    public String getInspection_inspector_name();

    public LocalDate getInspection_date();

    public LocalDate getInspection_request_date();
    public LocalTime getInspection_time();

    public LocalTime getInspection_request_time();

    public String getInspection_issue();

    public Boolean getInspection_passed();

    public boolean isInUseDuringInterval();
    public void getInspectionInformation();
}
