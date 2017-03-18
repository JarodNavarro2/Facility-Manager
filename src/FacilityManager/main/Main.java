package FacilityManager.main;
import FacilityManager.model.facility.Facility;
import FacilityManager.model.facility.Room;
import FacilityManager.model.facility.FacilityImpl;
import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.maintenance.Maintenance;
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
		facility1.getFacilityRoomsInfo();
		System.out.println();
        facility1.getFacilityInformation();



		System.out.println("\nRemoving room #2, Kitchen.");
		facility1.bulldozeARoom(1);
		System.out.println("Printing Room Info");
		facility1.getFacilityRoomsInfo();
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

		ArrayList<Maintenance> arL = facility1.getMaintenanceLog();
		for(Maintenance main: arL){
			System.out.println(main.getMaintenance_name());
		}






	}

}