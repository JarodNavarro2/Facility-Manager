package FacilityManager.model.facility;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.usage.Usage;
import FacilityManager.model.usage.useFunction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FacilityImpl implements Facility {
    public String name;
    public int maximum_capacity;
    public int current_capacity;
    public String status;
    public boolean occupancy;

    public ArrayList<Maintenance> maintenance_log = new ArrayList<Maintenance>();
    public ArrayList<Room> rooms = new ArrayList<Room>();
    public ArrayList<Inspection> inspection_log = new ArrayList<Inspection>();

    public useFunction facilityUsage = new useFunction();


    public Usage getFacilityUsage(){
        return this.facilityUsage;
    }

    public Room getRoom(int room_number){
        Room r = null;
        int check = 0;
        for (int i = 0; i < rooms.size(); i++){
            if (rooms.get(i).getRoomNumber() == room_number){
                r = rooms.get(i);
                check++;
                break;
            }
        }
        if (check == 0){
            System.out.println("OOPS! Unable to find the room in question.");
        }
        return r;
    }

    public String getFacilityInformation(){
        return "Name: " + this.name + "\nMaximum Capacity: "+this.maximum_capacity + "\nCurrent Capacity: " + this.current_capacity + "\nStatus: " +
                this.status + "\nOccupancy: "+ getOccupancy();
    }

    public String getFacilityName(){
        return this.name;
    }

    public void setFacilityName(String newName){
        this.name = newName;
    }

    /* DARYA: I deleted setMaxCapacity, but implemented Carl's calculations here, since the only way
    * to set max capacity is by setting the max capacity for all the rooms */
    public int getMaxCapacity(){
        this.maximum_capacity = 0; //resting max capacity
        for(Room room: this.rooms){
            this.maximum_capacity = room.getRoomMaxCapacity() + maximum_capacity;
        }
        return this.maximum_capacity;
    }

    /* DARYA: I deleted setCurrentCapacity, but implemented Carl's calculations here, since the only way
        * to set current capacity is by setting the current capacity for all the rooms */
    public int getCurrentCapacity(){
        this.current_capacity = 0;
        for(Room rooms : this.rooms){
            //Need to add a function that will getRoomCapacity and not use the variable from rooms
            this.current_capacity = rooms.room_current_capacity + this.current_capacity;
        }
        return this.current_capacity;
    }


    public boolean createARoom(String name, int capacity, int max_capacity, int number) { //boolean return T if successful (only way to fail if room # check fails
        if (!roomNumberCheck(number)) {
            Room temp_room = new Room(name, capacity, max_capacity, number); //initializing
            rooms.add(temp_room);
            return true;
        } else {
            return false;
        }
    }

    public boolean roomNumberCheck(int number){
        //checking to make sure the room_number is not already given in the facility
        boolean token = false;
        for (Room room_object : rooms) {
            if (room_object.getRoomNumber() == number) {
                token = true;
            }
        }
        return token;
        //returns true when a room number already exists for that facility
    }

    public boolean bulldozeARoom(int number){ //need to know room number in order to delete that specific room
        //checking first based on room number
        boolean token = false;
       for(int i = 0; i < this.rooms.size(); i ++){
           if (this.rooms.get(i).getRoomNumber() == number){
               this.rooms.remove(i);
               token = true;
               break;
           }
       }
        return token;
    }


    public void setStatus(String input_status) {
        this.status = input_status;
    }

    public boolean getOccupancy() {
        if (this.current_capacity == 0) {
            this.occupancy = false;
        }
        else {
            this.occupancy = true;
        }
        return occupancy;
    }

    public String getStatus(){
        return this.status;
    }


    public void getFacilityInspections(){
        this.facilityUsage.listInspections(inspection_log);
    }

    public void createInspection(){
        //TODO - setters/getters implementation in main
        Inspection newInspection = new Inspection();
        inspection_log.add(newInspection);
    }
}
