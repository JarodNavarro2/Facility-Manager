package FacilityManager.model.maintenance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

///Class made to handle all maintenance requests and regards
public class MaintenanceImpl implements Maintenance
{
	public static ArrayList<Maintenance> maintain = new ArrayList<Maintenance>(); ///Storing the approved MaintenanceImpl
	public static ArrayList<Maintenance> maintain_request = new ArrayList<Maintenance>(); ///Storing the MaintenanceImpl requests
	public int schedule_time; ///The time for maintenance
	public boolean request_approval;///Boolean factor regarding if a request is approved or not
	public boolean problem_approval;///Boolean factor regarding if the problem rate is approved or not
	public boolean money_approval;///Boolean factor regarding if the budget is approved or not
	public int total_budget;///The total budget of the maintenance
	public int total_cost;///The total cost of the maintenance
	public String problems;///What the problem is 
	public String maintenance_name;///What the maintenance will be called
	public enum status { initiated, in_progress, complete }; //status of a maintenance order options
	public status request_status; ///Requesting the current status of the maintenance
	public LocalDateTime request_initiation_time; ///The start timer of a request
	public LocalDateTime request_completion_time;///The end timer of a request approval
	public MaintenanceImpl maintenanceFunction;


	public void setMaintenanceFunction(MaintenanceImpl main){
		this.maintenanceFunction = main;

	}
	public MaintenanceImpl getMaintenanceImplFunction(){
		return this.maintenanceFunction;

	}

	public Maintenance getMaintenanceRequest(String name) ///Getting the specific maintenance request
	{
		Maintenance main = null;
		name = name.toLowerCase();
		int check = 0;
		for(int i = 0; i < this.maintain_request.size(); i ++){
            if (this.maintain_request.get(i).getMaintenance_name().toLowerCase().matches(name)){
                main = this.maintain_request.get(i);
                check++;
                break;
            }
        }
        if (check == 0){
            System.out.println("OOPS! Unable to find the maintenance in question.");
        }
       return main;
	}
	public int getSchedule_time() ///Getting schedule time
	{
		return schedule_time;
	}
	public void setSchedule_time(int time)///Setting schedule time
	{
		this.schedule_time=time;
	}
	public int getTotal_cost()///Getting total cost
	{
		return total_cost;
	}
	public void setTotal_cost(int cost)///Setting total cost
	{
		this.total_cost=cost;
	}
	public int getTotal_budget()///Getting total budget
	{
		return total_budget;
	}
	public void setTotal_budget(int budget)///Setting total budget
	{
		this.total_budget=budget;
	}
	public void setRequest_approval(boolean req)///Setting the request approval
	{
		this.request_approval=req;
	}
	public boolean getRequest_approval()///Getting the request approval
	{
		return request_approval;
	}
	public void setProblem_approval(boolean prob) ///Setting the problem approval
	{
		this.problem_approval=prob;
	}
	public boolean getProblem_approval()///Getting the problem approval
	{
		return problem_approval;
	}
	public void setProblem(String problem)///Setting the problem of the maintenance
	{
		this.problems = problem;
	}
	public String getProblem()///Getting the problem of the maintenance 
	{
		return problems;
	}
	public void setMoney_approval(boolean mon)///Setting if the budget is approved or not
	{
		this.money_approval = mon;
	}
	public boolean getMoney_approval()///Getting the boolean factor of the budget
	{
		return money_approval;
	}
	public void setStatus(status input)///Setting the status 
	{ 
		this.request_status = input; 
	}
	public status getStatus()///Getting the status
	{ 
		return this.request_status; 
	}

	public void setMainenanceName(String s)///Setting the maintenance name
	{
		this.maintenance_name=s;
	}
	public String getMaintenance_name()///Getting the maintenance name
	{
		return maintenance_name;
	}

	public void setMaintenanceRequestBeginTime()///Setting the start time of the REQUEST
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dtf.format(now);
		this.request_initiation_time = now;
	}

	public LocalDateTime getMaintenanceRequestBeginTime()///Getting the start time
	{
		return this.request_initiation_time;
	}

	public void setMaintenanceRequestCompletionTime()///Setting the end time of the APPROVAL
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dtf.format(now);
		this.request_completion_time = now;
	}

	public LocalDateTime getMaintenanceRequestCompletionTime()///Getting the end time.
	{
		return this.request_completion_time;
	}
	
	//@IMPLEMENTATIONS@//
	
	public void makeFacilityRequest(ArrayList<Maintenance> main)///Creating a facility request, and adding it to the request array
    {
        maintain_request.addAll(main);
    }
    public void addMaintenance(ArrayList<Maintenance> main)///Adding maintenance ONLY if the request is approved
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
    public void calcMaintenanceCostForFacility(Maintenance main)///Calculating the cost. Passing or failing based on result
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
    public int calcProblemRateForFacility(int current_capacity, int max_capacity, Maintenance main)///Calculating the problem rate. Passing or failing based on result
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
    public void calcDownTimeForFacility()///Calculating the down time between maintenance REQUEST and maintenance APPROVAL
    {
        int hour_dif = request_completion_time.getHour() - request_initiation_time.getHour();
        int min_dif = request_completion_time.getMinute() - request_initiation_time.getMinute();
        int sec_dif = request_completion_time.getSecond() - request_initiation_time.getSecond();
        System.out.println("Downtime: "+hour_dif+":"+min_dif+":"+sec_dif);

    }
    public void listMaintRequests(Maintenance main)///Listing all requests
    {
       
            System.out.println("Request Information");
            System.out.println("MaintenanceImpl name: " + main.getMaintenance_name());
            System.out.println("MaintenanceImpl time: "+ main.getSchedule_time() + " Hours");
            System.out.println("MaintenanceImpl Budget: "+ main.getTotal_budget() + ". MaintenanceImpl Cost: "+ main.getTotal_cost());
            System.out.println("MaintenanceImpl Reason: "+ main.getProblem());
        
    }
    public void listMaintenance(Maintenance main)///Listing all MaintenanceImpl
    {

            System.out.println("MaintenanceImpl name: " + main.getMaintenance_name());
            System.out.println("MaintenanceImpl time: "+ main.getSchedule_time() + " Hours");
            System.out.println("MaintenanceImpl Budget: "+ main.getTotal_budget() + ". MaintenanceImpl Cost: "+ main.getTotal_cost());
            System.out.println("MaintenanceImpl Reason: " + main.getProblem());

    }
    public void listFacilityProblems(Maintenance main)///Listing all problems
    {
       
            System.out.println("MaintenanceImpl name: " + main.getMaintenance_name());
            System.out.println("Problem: "+ main.getProblem());
        
    }

}

