package FacilityManager.model.facility;

public interface Room {
    public void setName(String name);
    public String getRoomName();
    public boolean setRoom_current_capacity(int current_capacity);
    public int getRoom_current_capacity();
    public boolean setRoom_max_capacity(int max_capacity);
    public int getRoomMaxCapacity();
    public void setRoom_number(int room_number);
    public int getRoomNumber();
}
