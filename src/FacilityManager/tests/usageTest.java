import facilities.Facility;
import facilities.Usage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Created by carlreider on 2/20/17.
 */
public class usageTest {

    public Facility testFac;
    public ArrayList<Facility> facList;
    public Usage use;

    @BeforeEach
    void setUp() {
        testFac = new Facility();
        use = new Usage();
        testFac.setFacilityName("Testing");
        facList = new ArrayList<Facility>();
        facList.add(testFac);
        testFac.createARoom("Shower",2,500,1);
        testFac.createARoom("Bathroom",100,102,2);
    }

    @AfterEach
    void tearDown() {
        facList.clear();
        assert (facList.size() == 0);
        testFac = null;
    }

    @Test
    public void testListActualUsage(){
        use = testFac.getFacilityUsage();
        use.setUse("Vacant");
        assert (testFac.getFacilityUsage().getUse().equals("Vacant"));
       use.listActualUsage(testFac);

    }

    @Test
    void testCalcUsageRate(){
        //This is not the most ideal way
        double usageRate = testFac.getFacilityUsage().calcUsageRate(testFac);
        assert  (usageRate == 16.943521594684384);
    }

    @Test
    public void testVacate(){
       assert (use.vacateFacility("Testing",facList));
    }


}

