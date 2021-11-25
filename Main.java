package registration.SYSTEM;

//Import necessary packages
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//MAIN CLASS
public class Main {

	//Method: MAIN
	public static void main(String[] args) throws NumberFormatException, IOException, ClassNotFoundException {
		//Initialize all_data object
		All_data all_data = null;
		//try serializing data from file of last use
  
			Serialize serial = new Serialize();
			all_data = serial.deserialize();
			System.out.println("Retrieving data...");
			//System.out.println(all_data.all_courses.get(0).getName());
			//System.out.println(all_data.all_students.get(0).getFirst_name());
		     
		//catch file not found exception
		//read from other data file (if first time running)
	if (all_data == null) {
			ArrayList<Course> course_list = Data_loading.load_course_data();
			ArrayList<Student> student_list = Data_loading.load_student_data(course_list);
			all_data = new All_data(course_list, student_list);
	}
	else {
	}
		//General login
		User user = Login.general_login(all_data.all_students);
		//Verify login credentials 
		while (user.getUsername().equals("x")) {
			System.out.println();
			System.out.println("Returning to login...");
			user = Login.general_login(all_data.all_students);
		}
		//Display admin menu
		if (user.getUsername().equals("Admin")){
			((Admin) user).display_menu(all_data);
		}
		//Display student menu
		else {
			((Student) user).display_menu(all_data);
		}
	}
}