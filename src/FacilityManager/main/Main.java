package FacilityManager.main;
import FacilityManager.model.facility.Facility;
import FacilityManager.model.facility.Room;
import FacilityManager.model.facility.FacilityImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/app-context.xml");
		System.out.println("***************** Application Context instantiated! ******************");

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









		//TODO - changeRoomInfo based on Room setters/getters


	}

}