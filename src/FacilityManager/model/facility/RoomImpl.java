package FacilityManager.model.facility;
import java.util.Vector;

public class RoomImpl implements Room {
    public String name;
    public int room_max_capacity;
    public int room_current_capacity;
    public int room_number;
    public Vector<Integer> room_numbers = new Vector();

    public void setName(String name){
        this.name = name;
    }
    public String getRoomName() {
        return this.name;
    }

    public boolean setRoom_current_capacity(int current_capacity) {
        //checking to make sure capacity has not reached maximum capacity
        boolean success_token;
        if (current_capacity >= this.room_max_capacity || current_capacity < 0) {
            success_token = false;
        } else {
            this.room_current_capacity = current_capacity;
            success_token = true;
        }
        return success_token;
    }

    public int getRoom_current_capacity(){ return this.room_current_capacity;}


    public boolean setRoom_max_capacity(int max_capacity) {
        //checking to make sure new max capacity is not less than current capacity
        boolean success_token;
        if (max_capacity < this.room_current_capacity) {
            success_token = false;
        } else {
            this.room_max_capacity = max_capacity;
            success_token = true;
        }
        return success_token;
    }

    public int getRoomMaxCapacity(){
        return this.room_max_capacity;
    }

    public void setRoom_number(int room_number) {
        boolean token = true;
        int length = room_numbers.size();
        for (Integer num : room_numbers) {
            if (num == room_number) {
                System.out.println("Unable to set room #, because room #" + room_current_capacity + " already is taken.");
                System.out.println("System default setting to room # to (desired room # +1). Room # is " + (room_number + 1));
                token = false;
            }
        }
        if (token) {
            this.room_number = room_number;
            room_numbers.add(room_number);
        } else {
            setRoom_number(room_number + 1);
        }
    }

    public int getRoomNumber() { return this.room_number; }

}
