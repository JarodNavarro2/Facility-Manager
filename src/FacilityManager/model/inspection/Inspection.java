package FacilityManager.model.inspection;
import java.time.LocalDate;
import java.time.LocalTime;

public class Inspection {
    public String inspector_name;
    public LocalDate inspection_date;
    public LocalDate inspection_request_date;
    public LocalTime inspection_time;
    public LocalTime inspection_request_time;
    public String issue;
    public Boolean inspection_passed;

    public void setInspector_name(String name){this.inspector_name = name;}
    public void setInspection_date(LocalDate date){this.inspection_date = date;}
    public void setInspection_request_date(LocalDate date){this.inspection_request_date = date;}
    public void setInspection_time(LocalTime time){this.inspection_time = time;}
    public void setInspection_request_time(LocalTime time){this.inspection_request_time = time;}
    public void setIssue(String issue){this.issue = issue;}
    public void setInspection_passed(Boolean passed){this.inspection_passed = passed;}

    public String getInspection_inspector_name(){ return this.inspector_name; }

    public LocalDate getInspection_date(){ return this.inspection_date; }

    public LocalDate getInspection_request_date(){ return this.inspection_request_date; }

    public LocalTime getInspection_time(){ return this.inspection_time; }

    public LocalTime getInspection_request_time(){ return this.inspection_request_time; }

    public String getInspection_issue(){ return this.issue; }

    public Boolean getInspection_passed() { return this.inspection_passed; }




}
