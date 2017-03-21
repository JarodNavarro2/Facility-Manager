package FacilityManager.main;
import FacilityManager.model.facility.Facility;
import FacilityManager.model.facility.Room;
import FacilityManager.model.facility.FacilityImpl;
import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.maintenance.maintenanceImplFunction;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cglib.core.Local;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		System.out.println("***************** Application Context instantiated! ******************");

		//Getting the facility BEAN
		Facility facility1 = (Facility) context.getBean("facility1");
		facility1.setFacilityName("House");
		facility1.setStatus("In Use");
		facility1.setOccupancy(true);

		System.out.println("\nListing room information for the facility " + facility1.getFacilityName());
		facility1.getFacilityRoomsInfo();
		System.out.println("\nListing facility information for " + facility1.getFacilityName());
        facility1.getFacilityInformation();

		System.out.println("\nRemoving room #2, " + facility1.getRooms().get(1).getRoomName());
		facility1.bulldozeARoom(2);
		System.out.println("\nListing room information for the facility " + facility1.getFacilityName());
		facility1.getFacilityRoomsInfo();
		System.out.println("\nListing facility information for " + facility1.getFacilityName());
        facility1.getFacilityInformation();
        System.out.println();

		//@TODO Create multiple inspections per facility
		Inspection inspection1 = (Inspection) context.getBean("inspections");
		inspection1.setInspection_date(LocalDate.of(2017,01,12));
		inspection1.setInspection_passed(false);
		inspection1.setInspection_request_date(LocalDate.now());
		inspection1.setInspection_time(LocalTime.now());
		inspection1.setInspector_name("Carl");
		inspection1.setIssue("What issue?");
		facility1.setInspections(inspection1);
		facility1.getInspection().getInspectionInformation();


		//A maintenance object has already been injected into facility because of SPRING
		Maintenance maintenance1 = facility1.getMaintenance();
		maintenance1.setMainenanceName("Test Maintenance");
		maintenance1.setProblem("This is an issue");

		//Added another injection to Maintence. We inject the maintenance functions into the implementation so we can use those functions
		//This function was injected into the maintenance class via Spring
		maintenanceImplFunction functions = maintenance1.getMaintenanceImplFunction();


		//We can then call functions on maintenance using this injection
		functions.calcMaintenanceCostForFacility(maintenance1);
		System.out.println("Maintenance Calc Problem Rate");
		functions.calcProblemRateForFacility(facility1.getCurrentCapacity(), facility1.getMaxCapacity(), maintenance1);
		functions.listMaintenance(maintenance1);


		System.out.println("Maintenance Log Below");
		ArrayList<Maintenance> arL = facility1.getMaintenanceLog();
		for(Maintenance main: arL){
			System.out.println(main.getMaintenance_name());
		}


	}

}