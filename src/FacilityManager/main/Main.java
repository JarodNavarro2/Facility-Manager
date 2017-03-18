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


		Room room1 = (Room) context.getBean("room");
		System.out.println("Creating new room: Living Room.");
		room1.setName("Living Room");
		room1.setRoom_max_capacity(6);
		room1.setRoom_current_capacity(2);
		room1.setRoom_number(1);
		facility1.setRoom(room1);

		Room room2 = (Room) context.getBean("room");
		System.out.println("Creating new room: Kitchen.");
		room2.setName("Kitchen");
		room2.setRoom_max_capacity(4);
		room2.setRoom_current_capacity(1);
		room2.setRoom_number(2);
		facility1.setRoom(room2);

		System.out.println("\nListing facility information.");
		facility1.getFacilityInformation();
		System.out.println("\nListing facility rooms information.");
		facility1.getFacilityRoomsInfo();

		System.out.println("\nRemoving room #2, Kitchen.");
		facility1.bulldozeARoom(2);
		facility1.getFacilityRoomsInfo();









		//TODO - changeRoomInfo based on Room setters/getters


	}

}