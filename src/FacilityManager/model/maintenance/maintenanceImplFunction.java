package FacilityManager.model.maintenance;


import java.util.ArrayList;

public class maintenanceImplFunction extends MaintenanceImpl {
    public void makeFacilityRequest(ArrayList<MaintenanceImpl> main)///Creating a facility request, and adding it to the request array
    {
        maintain_request.addAll(main);
    }
    public void addMaintenance(ArrayList<MaintenanceImpl> main)///Adding maintenance ONLY if the request is approved
    {
        if (request_approval == true && problem_approval == true && money_approval == true)
        {
            maintain.addAll(main);
            System.out.println("MaintenanceImpl added");
        }
        else
        {
            System.out.println("MaintenanceImpl has not been added");
        }
    }
    public void calcMaintenanceCostForFacility(MaintenanceImpl main)///Calculating the cost. Passing or failing based on result
    {
        int cost = main.getTotal_cost();
        int budg = main.getTotal_budget();
        int result = budg - cost;
        if (result <= 0)
        {
            System.out.println("MaintenanceImpl will drive us bankrupt");
            main.setMoney_approval(false);
        }
        else
        {
            System.out.println("Total cost for MaintenanceImpl: " + result);
            main.setMoney_approval(true);
        }
    }
    public int calcProblemRateForFacility(int current_capacity, int max_capacity, MaintenanceImpl main)///Calculating the problem rate. Passing or failing based on result
    {
        double problem = 0;
        if (current_capacity == 0)
        {
            System.out.println("Facility vacated. One moment...");
            System.out.println("Request approved");
            main.setProblem_approval(true);
            return 1;
        }
        else
        {
            System.out.println("While the Facility is not vacated, time is being taken away from those individuals");
            problem = (current_capacity / (double) max_capacity) * 100;
            System.out.println("The problem rate is around "+problem+"% for the requested maintenance");
            System.out.println("One moment...");
            if (problem <=70)
            {
                System.out.println("Request approved");
                main.setProblem_approval(true);
                return 1;
            }
            else
            {
                System.out.println("The problem is too high to request maintenance. Please speak to an advisor or VACATE the facility.");
                System.out.println("Request denied");
                main.setProblem_approval(false);
                return 0;
            }
        }
    }
    public void calcDownTimeForFacility()///Calculating the downtime between maintenance REQUEST and maintenance APPROVAL
    {
        int hour_dif = request_completion_time.getHour() - request_initiation_time.getHour();
        int min_dif = request_completion_time.getMinute() - request_initiation_time.getMinute();
        int sec_dif = request_completion_time.getSecond() - request_initiation_time.getSecond();
        System.out.println("Downtime: "+hour_dif+":"+min_dif+":"+sec_dif);

    }
    public void listMaintRequests()///Listing all requests
    {
        for (MaintenanceImpl main : this.maintain_request)
        {
            System.out.println("Request Information");
            System.out.println("MaintenanceImpl name: " + main.maintenance_name);
            System.out.println("MaintenanceImpl time: "+main.schedule_time);
            System.out.println("MaintenanceImpl Budget: "+ main.total_budget + ". MaintenanceImpl Cost: "+ main.total_cost);
            System.out.println("MaintenanceImpl Reason: "+ main.problems);
        }
    }
    public void listMaintenance()///Listing all MaintenanceImpl
    {
        for (MaintenanceImpl main : this.maintain)
        {
            System.out.println("MaintenanceImpl name: " + main.maintenance_name);
            System.out.println("MaintenanceImpl time: "+main.schedule_time);
            System.out.println("MaintenanceImpl Budget: "+ main.total_budget + ". MaintenanceImpl Cost: "+ main.total_cost);
            System.out.println("MaintenanceImpl Reason: " + main.problems);
        }
    }
    public void listFacilityProblems()///Listing all problems
    {
        for (MaintenanceImpl main : this.maintain)
        {
            System.out.println("MaintenanceImpl name: " + main.maintenance_name);
            System.out.println("Problem: "+ main.problems);
        }
    }

}
