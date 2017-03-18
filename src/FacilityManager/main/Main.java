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
		Room room = facility1.getRoom();
		room.setRoom_number(2);
		room.setRoom_max_capacity(100);
		room.setName("Testing Room");
		room.setRoom_current_capacity(29);

		facility1.getFacilityInformation();









		//TODO - changeRoomInfo based on Room setters/getters


	}

}