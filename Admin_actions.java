package registration.SYSTEM;

//Import necessary packages
import java.io.IOException;
import java.util.ArrayList;

//INTERFACE: ADMIN ACTIONS
public interface Admin_actions {
	
	//GENERAL FUNCTIONS
	
	//Method: Display admin menu
	//Input: all data 
	//Output: void
	public abstract void display_menu(All_data all_data) throws IOException;

	//Method: Process menu selection
	//Input: all data 
	//Output: void
	public abstract void menu_touchpoint(String selection, All_data all_data) throws IOException;
	
	//COURSE MANAGEMENT FUNCTIONS

	//Method: Create a course
	//Input: all data 
	//Output: void 
	public abstract void create_course(All_data all_data) throws IOException;
	
	//Method: Delete a course
	//Input: all data 
	//Output: void 
	public abstract void delete_course(All_data all_data) throws IOException;

	//Method: Edit a course
	//Input: all data 
	//Output: void 
	public abstract void edit_course(All_data all_data) throws IOException;

	//Method: Display course info
	//Input: all data 
	//Output: void 
	public abstract void display_info(All_data all_data) throws IOException;

	//Method: Register student in a course
	//Input: all data 
	//Output: void
	public abstract void register_student(All_data all_data) throws IOException;

	//REPORT FUNCTIONS
	
	//Method: View all courses
	//Input: all data 
	//Output: void
	public abstract void view_all(All_data all_data) throws IOException;

	//Method: View all full courses
	//Input: all data 
	//Output: void
	public abstract void view_all_full(All_data all_data) throws IOException;

	//Method: Write all full courses to a file
	//Input: all data 
	//Output: void
	public abstract void write_all_full(All_data all_data) throws IOException;

	//Method: View all students registered in a course
	//Input: all data 
	//Output: void
	public abstract void view_registered_students(All_data all_data) throws IOException;

	//Method: View all courses a given student is registered for
	//Input: all data 
	//Output: void
	public abstract void view_registered_courses(All_data all_data) throws IOException;

	//Method: Sort all courses based on number of enrolled students
	//Input: all data 
	//Output: void
	public abstract void sort(All_data all_data) throws IOException;

	//Method: Serialize updated data to a file and exit the program
	//Input: all data 
	//Output: void
	public abstract void exit(All_data all_data) throws IOException;
}