package FacilityManager.model.facility;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.inspection.Inspection;
import FacilityManager.model.usage.Usage;
import FacilityManager.model.usage.useFunction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

public class FacilityImpl implements Facility {
    public String name;
    public int maximum_capacity;
    public int current_capacity;
    public String status;
    public boolean occupancy;
    public ArrayList<Maintenance> maintenance_log = new ArrayList<Maintenance>();
    public List<Room> rooms ;
    public ArrayList<Inspection> inspection_log = new ArrayList<Inspection>();


    // Specific methods for dependency injection
    public void setRooms(List<Room> rooms){
        this.rooms = rooms;
    }

    public List<Room> getRooms(){
       return this.rooms;
    }


    public useFunction facilityUsage = new useFunction();


    public Usage getFacilityUsage(){
        return this.facilityUsage;
    }



    public void getFacilityInformation(){
        System.out.println("Name: " + this.getFacilityName() + "\nMaximum Capacity: "+ this.getMaxCapacity() + "\nCurrent Capacity: " + this.getCurrentCapacity() + "\nStatus: " +
                this.getStatus() + "\nOccupancy: "+ this.getOccupancy());
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
        this.maximum_capacity = 0;
        for (Room room:rooms){
            maximum_capacity += room.getRoomMaxCapacity();
        }
        return maximum_capacity;
    }

    /* DARYA: I deleted setCurrentCapacity, but implemented Carl's calculations here, since the only way
        * to set current capacity is by setting the current capacity for all the rooms */
    public int getCurrentCapacity(){
        this.current_capacity = 0;
        for(Room rooms : rooms){
            //Need to add a function that will getRoomCapacity and not use the variable from rooms
            this.current_capacity = rooms.getRoom_current_capacity() + this.current_capacity;
        }
        return this.current_capacity;
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

    public void setOccupancy(boolean occupancy){
        this.occupancy = occupancy;
    }
    public void getFacilityInspections(){
       // this.facilityUsage.listInspections(inspection_log);
    }

    public void createInspection(){
        //TODO - setters/getters implementation in main
        Inspection newInspection = new Inspection();
        inspection_log.add(newInspection);
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void getFacilityRoomsInfo(){
        for (Room room:rooms){
            System.out.println(
                    "Room name: " + room.getRoomName() +
                            "\nRoom number: " + room.getRoomNumber() +
                            "\nRoom current capacity: " + room.getRoom_current_capacity() +
                            "\nRoom maximum capacity: " + room.getRoomMaxCapacity()
            );
        }
    }
}
