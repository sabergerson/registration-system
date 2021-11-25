package registration.SYSTEM;

//Import necessary packages
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements Student_actions, Serializable {
	//Data fields
	ArrayList<Course> courses;
	
	//default serial version ID
	private static final long serialVersionUID = 1L;

	//Default constructor
	public Student() {
	}

	//Constructor
	public Student(String username, String password, String first_name, String last_name) {
		super(username, password, first_name, last_name);
		this.courses = new ArrayList<Course>();
	}
	
	//GENERAL METHODS
	
	//Method: Display student menu
	//Input: all data 
	//Output: void
	@Override
	public void display_menu(All_data all_data) throws IOException {
		//Display options
		System.out.println("Please select from the following actions\n"
				+"by entering the letter in parentheses");
		System.out.println();
		System.out.println("COURSE MANAGEMENT");
		System.out.println("(A) View all courses");
		System.out.println("(O) View all open courses");
		System.out.println("(R) Register for a course");
		System.out.println("(W) Withdraw from a course");
		System.out.println("(V) View all registered courses");
		System.out.println();
		System.out.println("(X) Exit the program");
		System.out.println();
		//Obtain student input
		Scanner scan = new Scanner(System.in);  
		System.out.println("Action: ");
		String action = scan.nextLine();
		//verify input
		while (!action.equals("A") && !action.equals("O") && !action.equals("R") && !action.equals("W") &&
				!action.equals("V") && !action.equals("X")) {
			System.out.println("Invalid action. Please try again.");
			System.out.println("Please select from the following actions\n"
					+"by entering the letter in parentheses");
			System.out.println();
			System.out.println("COURSE MANAGEMENT");
			System.out.println("(A) View all courses");
			System.out.println("(O) View all open courses");
			System.out.println("(R) Register for a course");
			System.out.println("(W) Withdraw from a course");
			System.out.println("(V) View all registered courses");
			System.out.println();
			System.out.println("(X) Exit the program");
			System.out.println();
			System.out.println("Action: ");
			action = scan.nextLine();	
		}
		//Send choice to menu touchpoint
		menu_touchpoint(action, all_data);	
	}
	
	//Method: Send actions to menu touchpoint
	//Input: String action, all data 
	//Output: void
	@Override
	public void menu_touchpoint(String selection, All_data all_data) throws IOException {
		//match input and call corresponding action
		if (selection.equals("A")) {
			view_all(all_data);	
		}
		else if (selection.equals("O")) {
			view_all_open(all_data);	
		}
		else if (selection.equals("R")) {
			register(all_data);
		}
		else if (selection.equals("W")) {
			withdraw(all_data);
		}
		else if (selection.equals("V")) {
			my_courses(all_data);
		}
		else if (selection.equals("X")) {
			exit(all_data);
		}	
	}
	
	//Method: Identify course for student registration
	//Input: all data
	//Output: course reference 
	public Course identify_course_registration(All_data all_data) {
		//Obtain course information
		Scanner scan = new Scanner(System.in);  
		System.out.println("Enter course ID: ");
		String course_id = scan.nextLine();	
		boolean assigned = false;
		for (Course course: all_data.all_courses) {
			if (course.getCourse_ID().equals(course_id)) {
				assigned = true;
			}
		}
		ArrayList<Course> to_register = new ArrayList<Course>();
		ArrayList<Student> students = new ArrayList<Student>();
		Course course_to_register = new Course("x", "x", "x", "x", "x", 0, 0, students);
		if (assigned == true) {
			//find matching courses
			System.out.println("Sections of "+course_id+":");
			for (Course course: all_data.all_courses) {
				if (course.getCourse_ID().equals(course_id)) {
					System.out.println(course.getName()+" "+course.getCourse_ID()+" Section: "
						+course.getSection()+" Instructor: "+course.getInstructor()+"\nMax students: "
						+course.getMax_students()+" Enrolled students: "+course.getEnrolled_students()
						+" Location: "+course.getLocation());
					to_register.add(course);
				}	
			}
			//Obtain specific course information
			System.out.println("Enter section number: ");
			String choice = scan.nextLine();
			//Select specific course 
			for (Course course: to_register) {
				if (course.getSection().equals(choice)) {
					course_to_register = course; 
				}
			}
		}
		else {
			System.out.println("There is so course with this ID.");
		}
		//return course reference 
		return course_to_register;
	}
	
	
	//Method: Force quite
	//Inpout: none
	//Output: void
	public void force_quit() {
		System.exit(0);
	}
	
	//COURSE MANAGEMENT METHODS
	
	//Method: View all courses
	//Input: all data 
	//Output: void
	@Override
	public void view_all(All_data all_data) throws IOException {
		//Display all courses
		for (Course course: all_data.all_courses) {
			System.out.println(course.getName()+" "+course.getCourse_ID()+" Section: "
					+course.getSection()+" Instructor: "+course.getInstructor()+"\nMax students: "
					+course.getMax_students()+" Enrolled students: "+course.getEnrolled_students()
					+" Location: "+course.getLocation());
		}
		
		//Prompt user for next action
		System.out.println();
		Scanner scan = new Scanner(System.in); 
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
	}

	//Method: View all open courses
	//Input: all data 
	//Output: void
	@Override
	public void view_all_open(All_data all_data) throws IOException {
		//Disply all courses for which enrolled students is less than max
		for (Course course: all_data.all_courses) {
			if (course.getEnrolled_students() < course.getMax_students()) {
				System.out.println(course.getName()+" "+course.getCourse_ID()+" Section: "
						+course.getSection()+" Instructor: "+course.getInstructor()+"\nMax students: "
						+course.getMax_students()+" Enrolled students: "+course.getEnrolled_students()
						+" Location: "+course.getLocation());
			}
		}
		
		//Prompt user for next action
		System.out.println();
		Scanner scan = new Scanner(System.in); 
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
	}

	//Method: Register for a course
	//Input: all data 
	//Output: void
	@Override
	public void register(All_data all_data) throws IOException {
		//find desired course
		Course course = identify_course_registration(all_data);
		//find course in main list to directly edit
		Scanner scan = new Scanner(System.in);
		if (course.getName().equals("x")) {
			System.out.println("Cannot register.");
		}
		else {
			//tell student if course is full
			if (course.getEnrolled_students() == course.getMax_students()) {
				System.out.println("This course is full.");				
			}
			//otherwise, add the course to the student course list
			else {
				//Verify student identity
				System.out.println("Enter first name: ");
				String first_name = scan.nextLine();
				System.out.println("Enter last name: ");
				String last_name = scan.nextLine();
				while (!(this.getFirst_name() == first_name) || !(this.getLast_name() == last_name)) {
					System.out.println("Incorrect name. Try again.");
					System.out.println("Enter first name: ");
					first_name = scan.nextLine();
					System.out.println("Enter last name: ");
					last_name = scan.nextLine();
				}
				ArrayList<Course> courses = this.getCourses();
				courses.add(course);
				//add the student to the class list in the course object	
				for (Course course_section: all_data.all_courses) {
					if (course_section.getName().equals(course.getName()) && 
							course_section.getSection().equals(course.getSection())) {
						ArrayList<Student> full_class = course_section.getStudents();
						full_class.add(this);
						course_section.setStudents(full_class);
						course_section.setEnrolled_students(course_section.getEnrolled_students()+1);
					}
				}
					
			}
		}
			
		//Prompt user for next action
		System.out.println();
		System.out.println("(R) register for another course");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String choice = scan.nextLine();
		//Verify indicated action
		while (!choice.equals("R") &&!choice.equals("M") && !choice.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(R) register for another course");
			System.out.println("(M) return to main menu");
			System.out.println("(E) exit program");
			choice = scan.nextLine();
		}
		//execute on student choice
		if (choice.equals("R")) {
			register(all_data);
		}
		if (choice.equals("M")) {
			display_menu(all_data);
		}
		if (choice.equals("X")) {
			exit(all_data);
		}
	}

	//Method: Withdraw from a course
	//Input: all data 
	//Output: void
	@Override
	public void withdraw(All_data all_data) throws IOException {
		//Obtain course information
		Scanner scan = new Scanner(System.in);  
		my_courses(all_data);
		System.out.println("Enter course ID: ");
		String course_id = scan.nextLine();
		//remove course from student list
		boolean exists = false;
		for (Student student: all_data.getAll_students()) {
			if (student.equals(this)) {
				for (Course course: student.getCourses()) {
					if (course.getCourse_ID().equals(course_id)) {
						ArrayList<Course> updated_course = student.getCourses();
						updated_course.remove(course);
						student.setCourses(updated_course);
						exists = true;
					}
				}
			}
		}
		//remove student from course list
		for (Course course: all_data.all_courses) {
			if (course.getCourse_ID() == course_id && course.getStudents().contains(this)) {
				ArrayList<Student> updated_students = course.getStudents();
				updated_students.remove(this);
				course.setStudents(updated_students);
			}
		}
		//Prompt user for next action
		if (exists == false) {
			System.out.println("You are not in a course with this ID.");
		}
		System.out.println();
		System.out.println("(W) withdraw from another course");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("W") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(W) withdraw from another course");
			System.out.println("(M) return to main menu");
			System.out.println("(E) exit program");
			action = scan.nextLine();
		}
		//eecute on student choice
		if (action.equals("W")) {
			withdraw(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}
	}	

	//Method: View all courses student is registered for
	//Input: all data 
	//Output: void
	@Override
	public void my_courses(All_data all_data) {
		for (Course course: this.getCourses()) {
			System.out.println(course.getName()+" "+course.getCourse_ID()+" "+course.getInstructor()
			+" "+course.getMax_students()+" "+course.getEnrolled_students()+" "+course.getSection()
			+" "+course.getLocation());
		}
	}

	@Override
	public void exit(All_data all_data) throws IOException {
		//serialize data to file for next use
		Scanner scan = new Scanner(System.in);
		Serialize serial = new Serialize();
		boolean success;
		System.out.println("Exiting");
		success = serial.serialize(all_data);
		//exit the program if serialization successful
		if (success == true) {
			System.exit(0);
		}
		if (success == false) {
			System.out.println("Cannot save data.");
			System.out.println();
			System.out.println("(M) return to main menu");
			System.out.println("(F) force quit");
			String action = scan.nextLine();
			//Verify indicated action
			while (!action.equals("M") && !action.equals("F")) {
				System.out.println("Invalid input. Try again.");
				System.out.println();
				System.out.println("(M) return to main menu");
				System.out.println("(F) force quit");
				action = scan.nextLine();
			}
			//Execute indicated action
			if (action.equals("F")) {
				display_menu(all_data);
			}
			if (action.equals("F")) {
				force_quit();
			}
		}
	}
	
	//Accessors and mutators
	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}	
}