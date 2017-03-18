package FacilityManager.model.inspection;

import java.time.LocalDate;


public class inspectionFunction extends Inspection {
    public boolean isInUseDuringInterval() {
        if(this.getInspection_date() == LocalDate.now()){
            return true;
        }
        return false;
    }
}
