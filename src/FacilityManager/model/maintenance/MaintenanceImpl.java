package FacilityManager.model.maintenance;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

///Class made to handle all maintenance requests and regards
public class MaintenanceImpl implements Maintenance
{
	public static ArrayList<MaintenanceImpl> maintain = new ArrayList<MaintenanceImpl>(); ///Storing the approved MaintenanceImpl
	public static ArrayList<MaintenanceImpl> maintain_request = new ArrayList<MaintenanceImpl>(); ///Storing the MaintenanceImpl requests
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

	public MaintenanceImpl getMaintenanceRequest(String name) ///Getting the specific maintenance request
	{
		MaintenanceImpl main = null;
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

}

