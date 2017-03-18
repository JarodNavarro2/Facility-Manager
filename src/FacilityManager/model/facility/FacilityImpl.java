package FacilityManager.model.facility;
import FacilityManager.model.maintenance.Maintenance;
import FacilityManager.model.maintenance.Inspection;
import FacilityManager.model.usage.Usage;
import FacilityManager.model.usage.useFunction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FacilityImpl {
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

    public boolean changeRoomInfo(int number) { //need to know room number in order to delete that specific room
        boolean token = true;
        Room temp_room = getRoom(number);
        if (temp_room != null) { //if room # found
            System.out.println("We have found a room in the facility by the room # " + number);
            System.out.println("What information would you like to change: ");
            System.out.println("[1] Room # " + number + "'s current capacity");
            System.out.println("[2] Room # " + number + "'s maximum capacity");
            System.out.println("[3] Room # " + number + "'s name");
            System.out.println("[4] Room # " + number + "'s number");
            System.out.println("[0] To exit this option.");
            Scanner user_input = new Scanner(System.in);
            int input = user_input.nextInt();
            switch (input) {
                case 1:
                    System.out.println("Room # " + number + "'s new current capacity: ");
                    input = user_input.nextInt();
                    if (!temp_room.setRoom_current_capacity(input)){ //must pass the Room's check against max cap
                        System.out.println("Unable to set the new current capacity for room # " + number);
                        token = false;
                    }
                    break;
                case 2:
                    System.out.println("Room # " + number + "'s new maximum capacity: ");
                    input = user_input.nextInt();
                    if (!temp_room.setRoom_max_capacity(input)){ //must pass the Room's check to make sure that new max cap isn't less than current capacity
                        System.out.println("Unable to set the new maximum capacity for room # " + number);
                        token = false;
                    }
                    break;
                case 3:
                    System.out.println("Room # " + number + "'s new name: ");
                    String newName = user_input.next();
                    temp_room.setRoomName(newName);
                    break;
                case 4:
                    System.out.println("Room # " + number + "'s new room number: ");
                    input = user_input.nextInt();
                    if (!roomNumberCheck(input)){ //returns true when there already is a room with that number
                        temp_room.setRoom_number(input);
                    }
                    else {
                        System.out.println("Ooops! It appears the facility already has a room with that number.");
                        System.out.println("Please try again.");
                        token = false;
                    }
                    break;
                default:
                    System.out.println("Ooops! Something went wrong.");
                    token = false;
                    break;
                }
            }
        else { return false; }

        return token; //returns true when successful
    }

    public void setStatus(String input_status) { this.status = input_status; }

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
        Boolean inspection_passed = false;
        Scanner user_input = new Scanner(System.in);
        System.out.println("Creating a new inspection.");
        System.out.print("Inspector's name: ");
        String inspector_name = user_input.next();

        System.out.print("Inspection date in (yyyy-MM-dd) format): ");
        String inspection_di = user_input.next();

        //formatting input to LocalDate type for inspection date
        DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate inspection_date = LocalDate.parse(inspection_di, formatter_date);

        System.out.print("Inspection date in (HH:mm) format): ");
        String inspection_ti = user_input.next();

        //formatting input to LcalTime type for inspection time
        DateTimeFormatter formatter_time = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime inspection_time = LocalTime.parse(inspection_ti, formatter_time);

        System.out.print("Reason for inspection: ");
        String reason = user_input.next();
        System.out.print("Inspection passed (yes/no): ");
        String success = user_input.next();
        success = success.toLowerCase();
        if (success.startsWith("y")){
            inspection_passed = true;
        }
        else if (success.startsWith("n")){
            inspection_passed = false;
        }
        else {
            System.out.print("ERROR! Inspection pass/fail was incorrectly set. Auto-setting to FALSE.");
        }
        Inspection newInspection = new Inspection(inspector_name, inspection_date, inspection_time, reason, inspection_passed);
        inspection_log.add(newInspection);
    }
}
