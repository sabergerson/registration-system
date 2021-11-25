package registration.SYSTEM;

//Import necessary modules
import java.io.IOException;

//INTERFACE: STUDENT ACTIONS
public interface Student_actions {
	
	//Method: Display student menu
	//Input: all data 
	//Output: void
	public abstract void display_menu(All_data all_data) throws IOException;
	
	//Method: Send actions to menu touchpoint
	//Input: String action, all data 
	//Output: void
	public abstract void menu_touchpoint(String selection, All_data all_data) throws IOException;
	
	//Method: View all courses
	//Input: all data 
	//Output: void
	public abstract void view_all(All_data all_data) throws IOException;
	
	//Method: View all open courses
	//Input: all data 
	//Output: void
	public abstract void view_all_open(All_data all_data) throws IOException;
	
	//Method: Register for a course
	//Input: all data 
	//Output: void
	public  abstract void register(All_data all_data) throws IOException;
	
	//Method: Withdraw from a course
	//Input: all data 
	//Output: void
	public abstract void withdraw(All_data all_data) throws IOException;
	
	//Method: View all courses student is registered for
	//Input: all data 
	//Output: void
	public abstract void my_courses(All_data all_data);
	
	//Method: Serialize updated data and exit program
	//Input: all data 
	//Output: void
	public abstract void exit(All_data all_data) throws IOException;	
}