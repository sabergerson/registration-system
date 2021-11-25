package registration.SYSTEM;

//Import necessary modules
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//LOGIN CLASS
public class Login {
	
	//Method: General login
	public static User general_login(ArrayList<Student> students) {
		//Ask user if admin/student
		Scanner scan = new Scanner(System.in);  
		System.out.println("Enter account type (Admin / Student): ");
		String user_type = scan.nextLine();
		//verify valid role
		while (!user_type.equals("Admin") && !user_type.equals("admin") && !user_type.equals("Student") 
		&& !user_type.equals("student")) {
			System.out.println("Invalid account type. Try again");
			System.out.println("Enter account type (Admin / Student): ");
			user_type = scan.nextLine();
		}	
		//Send to admin login
		if (user_type.equals("Admin") || user_type.equals("admin")) {
			User user = new Admin();
			user = admin_login();
			return user;
		}
		//Send to student login
		else {
			User user = new Student();
			user = student_login(students);
			return user;
		}
	}
	
	//Method: Admin login
	//Input: none
	//Output: none
	public static Admin admin_login() {
		//Obtain admin information
	    Scanner scan = new Scanner(System.in); 
		System.out.println("Enter first name: ");
		String first_name = scan.nextLine();
		System.out.println("Enter last name: ");
		String last_name = scan.nextLine();
		//Create admin
		Admin admin = new Admin(first_name, last_name);
		//Process sign in
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		//Verify login information
		while (!username.equals(admin.getUsername()) || !password.equals(admin.getPassword())) {
			System.out.println("Incorrect username or password. Try again.");
			System.out.println("Enter username: ");
			username = scan.nextLine();
			System.out.println("Enter password: ");
			password = scan.nextLine();
		}
		//Greet admin
		System.out.println();
		System.out.println("Welcome "+admin.first_name);
		return admin;
	}
	
	//Method: Student login
	//Input: ArrayList of students
	//Output: none
	public static Student student_login(ArrayList<Student> students) {
		//Obtain student information
	    Scanner scan = new Scanner(System.in); 
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		//Create new student
		Student this_student = new Student("x", "x", "x", "x");
		boolean assigned = false;
		//Identify if student with credentials exists on student list
		for (Student student: students) {
			//If so, assign this identity to student
			if (student.getUsername().equals(username) && (student.getPassword().equals(password))) {
				this_student = student;
				assigned = true;
				System.out.println();
				System.out.println("Welcome "+student.first_name);
			}
		}
		//If not, notify student, prompt re-sign in
		if (assigned == false) {
			System.out.println("Incorrect username or password.\n"
					+ "If you would like to set up an account, please contact an administrator.");
		}
		return this_student;
	}
}