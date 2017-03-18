package FacilityManager.model.inspection;


import java.time.LocalDate;
import java.time.LocalTime;

public interface Inspection {
    void setInspector_name(String name);
    void setInspection_date(LocalDate date);
    void setInspection_request_date(LocalDate date);
    void setInspection_time(LocalTime time);
    void setInspection_request_time(LocalTime time);
    void setIssue(String issue);
    void setInspection_passed(Boolean passed);

    String getInspection_inspector_name();

    LocalDate getInspection_date();

    LocalDate getInspection_request_date();
    LocalTime getInspection_time();

    LocalTime getInspection_request_time();

    String getInspection_issue();

    Boolean getInspection_passed();

    boolean isInUseDuringInterval();
    void getInspectionInformation();
}
