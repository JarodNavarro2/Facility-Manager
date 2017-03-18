package FacilityManager.model.usage;

import FacilityManager.model.*;


import java.util.ArrayList;

public class Usage {
    String usage;
    // Business / Residential / Commercial/ Evicted / Vacant /

    public boolean inUse = false; //for initialization purposes only

    public void setUse(String useage){this.usage = useage;}

    public String getUse(){return this.usage;
    }


}
