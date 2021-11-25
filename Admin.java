package registration.SYSTEM;

//Import necessary packages
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//ADMIN CLASS
public class Admin extends User implements Admin_actions {
	
	//Default serial version ID
	private static final long serialVersionUID = 1L;

	//Default constructor
	public Admin() {
	}

	//Constructor
	public Admin(String first_name, String last_name) {
		super("Admin", "Admin001", first_name, last_name);
	}
	
	//GENERAL METHODS
	
	//Method: Display admin menu
	//Input: all data
	//Output: void
	@Override
	public void display_menu(All_data all_data) throws IOException {
		//Display menu options
		System.out.println("Please select from the following actions\n"
				+"by entering the letter in parentheses");
		System.out.println();
		System.out.println("COURSE MANAGEMENT");
		System.out.println("(C) Create course");
		System.out.println("(D) Delete course");
		System.out.println("(E) Edit course");
		System.out.println("(I) Display information for course");
		System.out.println("(R) Register a student");
		System.out.println();
		System.out.println("REPORTS");
		System.out.println("(A) View all courses");
		System.out.println("(F) View all full courses");
		System.out.println("(W) Write list of full courses to a file");
		System.out.println("(S) View students registered in course");
		System.out.println("(V) View courses a student is registered in");
		System.out.println("(O) Sort courses by enrolled students");
		System.out.println();
		System.out.println("(X) Exit the program");
		System.out.println();
		//Obtain admin input
		Scanner scan = new Scanner(System.in);  
		System.out.println("Action: ");
		String action = scan.nextLine();
		//Verify input
		while (!action.equals("C") && !action.equals("D") && !action.equals("E") && !action.equals("I") &&
				!action.equals("R") && !action.equals("A") && !action.equals("F") && !action.equals("W") &&
				!action.equals("S") && !action.equals("V") && !action.equals("X") && !action.equals("O")) {
			System.out.println("Invalid option. Please try again.");
			System.out.println();
			System.out.println("Please select from the following actions\n"
					+"by entering the letter in parentheses");
			System.out.println();
			System.out.println("COURSE MANAGEMENT");
			System.out.println("(C) Create course");
			System.out.println("(D) Delete course");
			System.out.println("(E) Edit course");
			System.out.println("(I) Display information for course");
			System.out.println("(R) Register a student");
			System.out.println();
			System.out.println("REPORTS");
			System.out.println("(A) View all courses");
			System.out.println("(F) View all full courses");
			System.out.println("(W) Write list of full courses to a file");
			System.out.println("(S) View students registered in course");
			System.out.println("(V) View courses a student is registered in");
			System.out.println("(O) Sort courses by enrolled students");
			System.out.println();
			System.out.println("(X) Exit the program");
			System.out.println();
			System.out.println("Action: ");
			System.out.println();
			action = scan.nextLine();
			
		}
		//send input and data to menu touchpoint
		menu_touchpoint(action, all_data);	
	}
	
	//Method: Matches input to admin function and calls corresponding method
	//Input: String admin input, all data
	//Output: void
	@Override
	public void menu_touchpoint(String selection, All_data all_data) throws IOException {
		//match input to method, call method
		if (selection.equals("C")) {
			create_course(all_data);	
		}
		else if (selection.equals("D")) {
			delete_course(all_data);	
		}
		else if (selection.equals("E")) {
			edit_course(all_data);
		}
		else if (selection.equals("I")) {
			display_info(all_data);
		}
		else if (selection.equals("R")) {
			register_student(all_data);
		}
		else if (selection.equals("A")) {
			view_all(all_data);
		}
		else if (selection.equals("F")) {
			view_all_full(all_data);
		}
		else if (selection.equals("W")) {
			write_all_full(all_data);
		}
		else if (selection.equals("S")) {
			view_registered_students(all_data);	
		}
		else if (selection.equals("V")) {
			view_registered_courses(all_data);	
		}
		else if (selection.equals("O")) {
			sort(all_data);
		}
		else if (selection.equals("X")) {
			exit(all_data);	
		}		
	}
	
	//Method: Identify course by name and section 
	//Input: all data
	//Output: Course reference
	public Course identify_course(All_data all_data) {
		//Obtain course name
		ArrayList<Student> empty = new ArrayList<Student>();
		Course specified_course = new Course("x", "x", "x", "x", "x", 0, 0, empty);
		Scanner scan = new Scanner(System.in);  
		System.out.println("Enter course name: ");
		String course_name = scan.nextLine();
		//Display matching courses 
		ArrayList<Course> selected = new ArrayList<Course>();
		boolean exists = false;
		System.out.println();
		for (Course course: all_data.all_courses) {
			if (course.getName().equals(course_name)) {
				exists = true;
				selected.add(course);
			}	
		}
		if (exists == true) {
			System.out.println("Sections of "+course_name+":");
			System.out.println();
			for (Course course: selected) {
				System.out.println(course.getName()+" "+course.getCourse_ID()+" Section: "
				+course.getSection()+" Instructor: "+course.getInstructor());
			}
			//Obtain section number
			System.out.println();
			System.out.println("Enter course section number: ");
			String section = scan.nextLine();	
			//Select specific course
			boolean section_exists = false;
			for(Course course: selected) {
				if (course.getSection().equals(section)) {
					section_exists = true;
					specified_course = course;
				}
				
			}
			while (section_exists == false) {
				System.out.println("Invalid section number. Try again.");
				System.out.println();
				System.out.println("Enter course section number: ");
				section = scan.nextLine();	
				//Select specific course
				for(Course course: selected) {
					if (course.getSection().equals(section)) {
						section_exists = true;
						specified_course = course;
					}	
				}
			}
			System.out.println(specified_course.getName()+" "+specified_course.getCourse_ID()+
					" Section: "+specified_course.getSection()+" Instructor: "
					+specified_course.getInstructor());
		}
		//Return course reference
		return specified_course;
	}


	//Method: Identify course by course ID, and display course info
	//Input: all data
	//Output: void 
	public void identify_course(All_data all_data, String id) throws IOException {
		boolean assigned = false;
		for (Course course: all_data.all_courses) {
			if (course.getCourse_ID().equals(id)) {
				assigned = true;
			}
		}
		if (assigned == true) {
			//Display matching courses
			System.out.println("Sections of "+id+":");
			System.out.println();
			for (Course course: all_data.all_courses) {
				if (course.getCourse_ID().equals(id)) {
				System.out.println(course.getName()+" "+course.getCourse_ID()+" Section: "
					+course.getSection()+" Instructor: "+course.getInstructor()+"\nMax students: "
					+course.getMax_students()+" Enrolled students: "+course.getEnrolled_students()
					+" Location: "+course.getLocation());
				//Display enrolled students
				if (course.getEnrolled_students() > 0) {
					for (Student student: course.getStudents()) {
						System.out.println(student.getFirst_name()+" "+student.getLast_name());
					}
				}	
			}
		}
		}
		else {
			System.out.println("There is no course with this ID");
		}
	}
	
	//Method: Identify course for student registration
	//Input: all data
	//Output: course reference 
	public Course identify_course_registration(All_data all_data) {
		//Obtain course ID
		Scanner scan = new Scanner(System.in);  
		System.out.println("Enter course ID: ");
		String course_id = scan.nextLine();	
		//Select matching courses
		ArrayList<Course> to_register = new ArrayList<Course>();
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
		//Obtain section number
		System.out.println("Enter section number:");
		String choice = scan.nextLine();
		//Select specific course
		Course course_to_register = new Course();
		for (Course course: to_register) {
			if (course.getSection().equals(choice)) {
				course_to_register = course; 
			}
		}	
		//return course reference
		return course_to_register;
	}
	
	//Method: Create new student
	//Input: none
	//Output: Student reference
	public Student create_student() {
		//Obtain new student information
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter first name: ");
		String first_name = scan.nextLine();
		System.out.println("Enter last name: ");
		String last_name = scan.nextLine();
		System.out.println("Enter username: ");
		String username = scan.nextLine();
		System.out.println("Enter password: ");
		String password = scan.nextLine();
		//Create student
		Student student = new Student(username, password, first_name, last_name);
		//return student
		return student;
	}
	
	//Method: Force quite
	//Inpout: none
	//Output: void
	public void force_quit() {
		System.exit(0);
	}

	//COURSE MANAGEMENT FUNCTIONS
	
	//Method: Create new course
	//Input: all data
	//Output: void
	@Override
	public void create_course(All_data all_data) throws IOException {
		//Obtain new course information
		System.out.println("NEW COURSE INFORMATION");
		System.out.println("______________________");
		Scanner scan = new Scanner(System.in);  
		System.out.println("Enter course name: ");
		String course_name = scan.nextLine();
		System.out.println("Enter course ID: ");
		String course_id = scan.nextLine();
		System.out.println("Enter section: ");
		String section = scan.nextLine();
		System.out.println("Enter instructor: ");
		String instructor = scan.nextLine();
		System.out.println("Enter location: ");
		String location = scan.nextLine();
		System.out.println("Enter maximum students: ");
		String max_students = scan.nextLine();
		System.out.println();
		//Create course
		Course new_course = new Course(course_name, course_id, section, instructor, 
				location, Integer.valueOf(max_students), 0, new ArrayList<Student>());		
		//Add course to all data
		all_data.all_courses.add(new_course);
		System.out.println(new_course.getName()+" created");
		
		//Prompt user for next action
		System.out.println();
		System.out.println("(C) create another course");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("C") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(C) create another course");
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("C")) {
			create_course(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
		System.out.println();
	}

	//Method: Delete course
	//Input: all data
	//Output: void
	@Override
	public void delete_course(All_data all_data) throws IOException {
		//Obtain course information
		Scanner scan = new Scanner(System.in);
		Course course = identify_course(all_data);
		if (course.getName() == "x") {
			System.out.println("There is no course with this name.");
		}
		else {
			//Verify course
			System.out.println();
			System.out.println("Is this the course you would like to delete (Y/N)?");
			String choice = scan.nextLine();
			System.out.println();
			while (choice.equals("Y") == false && choice.equals("N") == false) {
				System.out.print("Invalid option, choose again (Y/N):");
				choice = scan.nextLine();
			}
			//if yes, remove course from course list in all data
			if (choice.equals("Y")) {
				if (course.getEnrolled_students() > 0) {
					System.out.println("Courses with enrolled student cannot be deleted.");
				}
				else {
					all_data.all_courses.remove(course);
					System.out.println(course.getName()+" deleted");
				}
			}
		}

		//Prompt user for next action
		System.out.println();
		System.out.println("(D) delete another course");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("D") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(D) delete another course");
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("D")) {
			delete_course(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
		System.out.println();
	}			
	
	//Method: Edit course information
	//Input: all data
	//Output: void 
	@Override
	public void edit_course(All_data all_data) throws IOException {
		//Obtain course information
		Scanner scan = new Scanner(System.in);
		Course course = identify_course(all_data);
		if (course.getName() == "x") {
			System.out.println("There is no course with this name.");
		}
		else {
			for (Course this_course: all_data.all_courses) {
				if (this_course.getName().equals(course.name) && this_course.getSection().equals(course.section)) {
					System.out.println("Max students: "+course.getMax_students()+" Enrolled students: "
							+course.getEnrolled_students()+" Location: "+course.getLocation());
				}
			}
			System.out.println();
			//Verify course selection
			System.out.println("Is this the course you would like to edit (Y/N)?");
			String choice = scan.nextLine();
			while (choice.equals("Y") == false && choice.equals("N") == false) {
				System.out.println("Invalid option, choose again (Y/N):");
				choice = scan.nextLine();
			}
			//if yes, select element to edit
			if (choice.equals("Y")) {
				System.out.print("Enter element to edit:\n"
					+ "(section/instructor/location/maximum students\n)");
				String element = scan.nextLine();
				System.out.println();
				//verify input		
				while (!element.equals("section") && !element.equals("instructor") && !element.equals("location") 
					&& !element.equals("maximum students"))  {
					System.out.println("Invalid entry. Try again.");
					System.out.print("Enter element to edit:\n"
						+ "(section/instructor/location/maximum students/enrolled students\n)");
					element = scan.nextLine();
					System.out.println();
				}
				//edit section info
				if (element.equals("section")) { 
					//Obtain section info
					System.out.println("Current section: "+course.getSection());
					System.out.println("Enter new section: ");
					String new_section = scan.nextLine();
					//update course in all data
					for (Course edited_course : all_data.all_courses) {
						if (edited_course.equals(course)) {
							edited_course.setSection(new_section);
						}
					}
					System.out.println("Section updated.");					
				}
				//edit instructor info
				else if (element.equals("instructor")) {
					//Obtain instructor information
					System.out.println("Current instructor: "+course.getInstructor());
					System.out.println("Enter new instructor: ");
					String new_instructor = scan.nextLine();
					//update course in all data
					for (Course edited_course : all_data.all_courses) {
						if (edited_course.equals(course)) {
							edited_course.setInstructor(new_instructor);
						}
					}
					System.out.println("Instructor updated.");				
				}
				//edit location information
				else if (element.equals("location")) {
					//Obtain location information
					System.out.println("Current location: "+course.getLocation());
					System.out.println("Enter new location: ");
					String new_location = scan.nextLine();
					//update course in all data
					for (Course edited_course : all_data.all_courses) {
						if (edited_course.equals(course)) {
						edited_course.setLocation(new_location);
						}
					}
					System.out.println("Location updated.");	
				}
				//edit maximum students information
				else if (element.equals("maximum students")) {
					//obtain maximum students information
					System.out.println("Current maximum student: "+course.getMax_students());
					System.out.println("Enter new maximum students: ");
					String new_max_students = scan.nextLine();
					//verify that maximum students is not less then enrolled
					while (Integer.valueOf(new_max_students) < course.enrolled_students) {
						System.out.println("New value cannot be less than currently enrolled students. Try again.");
						System.out.println("Enter new maximum students: ");
						new_max_students = scan.nextLine();
					}
					//update course in all data
					for (Course edited_course : all_data.all_courses) {
						if (edited_course.equals(course)) {
							course.setMax_students(Integer.valueOf(new_max_students));
						}
					}
					System.out.println("Maximum students updated.");				
				}
			}
		}
		//Prompt user for next action
		System.out.println();
		System.out.println("(E) edit another course");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("E") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(E) edit another course");
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("E")) {
			edit_course(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
		System.out.println();
	}			

	//Method: Display course info
	//Input: all data
	//Output: void
	@Override
	public void display_info(All_data all_data) throws IOException {
		//Identify course to display
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter course ID:");
		String id = scan.nextLine();
		//Display course information
		identify_course(all_data, id); 
		
		//Prompt user for next action
		System.out.println();
		System.out.println("(I) display info another course");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("I") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(I) display info another course");
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("I")) {
			display_info(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
		System.out.println();
	}
	
	//Method: Register student for a course
	//Input: all data
	//Output: void 
	@Override
	public void register_student(All_data all_data) throws IOException {
		//create student
		Scanner scan = new Scanner(System.in);
		Student new_student = new Student();
		new_student = create_student();
		//add student to all data student list
		all_data.all_students.add(new_student);		
		System.out.println(new_student.getFirst_name()+" has been added.");
		//Prompt user for next action
		System.out.println();
		System.out.println("(R) register another student");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("R") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(R) register another student");
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("R")) {
			register_student(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
		System.out.println();
	}

	//Method: View all course information
	//Input: all data
	//Output: void
	@Override
	public void view_all(All_data all_data) throws IOException {
		//display all course information
		for (Course course: all_data.all_courses) {
			System.out.println(course.getName()+" "+course.getCourse_ID()+" Section: "
					+course.getSection()+" Instructor: "+course.getInstructor()+"\nMax students: "
					+course.getMax_students()+" Enrolled students: "+course.getEnrolled_students()
					+" Location: "+course.getLocation());
		}
		
		System.out.println();
		Scanner scan = new Scanner(System.in);
		//Prompt user for next action
		System.out.println();
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
		System.out.println();
	}

	//Method: View all full courses
	//Input: all data
	//Output: array list of full courses
	@Override
	public void view_all_full(All_data all_data) throws IOException {
		//display courses where enrolled and max students are equal
		for (Course course: all_data.all_courses) {
			if (course.getEnrolled_students() == course.getMax_students()) {
				System.out.println(course.getName()+" "+course.getCourse_ID()+" "+course.getInstructor()
				+" "+course.getMax_students()+" "+course.getEnrolled_students()+" "+course.getSection()
				+" "+course.getLocation());
			}
		}
		System.out.println();
		Scanner scan = new Scanner(System.in);
		//Prompt user for next action
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
		System.out.println();
	}

	//Method: Write all full courses to a file 
	//Input: all data
	//Output: void
	@Override
	public void write_all_full(All_data all_data) throws IOException {
		//write all courses to a file for which max students equals enrolled students
		ArrayList<Course> full_courses = new ArrayList();
		for (Course course: all_data.all_courses) {
			if (course.getEnrolled_students() == course.getMax_students()) {
				full_courses.add(course);
			}
		}
		//file path to create new file
		String path = "//Users//sage//eclipse-workspace//SageBergerson_Homework1//src//"
				+ "registration//FILES";
		//write full courses to file
		File csvFile = new File(path);
	    BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile+"//Full_courses.txt"));
		try {
			for (Course course: full_courses) {
				//define course info
				String course_info = course.getName()+" "+course.getCourse_ID()+" "+course.getInstructor()
				+" "+course.getMax_students()+" "+course.getEnrolled_students()+" "+course.getSection()
				+" "+course.getLocation();
				//write to file
				bw.append(course_info+"\n");
			}
			bw.append("\n");
			bw.close();  
		}
		//catch exceptions
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//notify admin of completion
		System.out.println("Courses written to file.");
		System.out.println();
		Scanner scan = new Scanner(System.in);
		//Prompt user for next action
		System.out.println();
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
		System.out.println();
	}

	//Method: View students registered in a specific course
	//Input: all data
	//Output: void
	@Override
	public void view_registered_students(All_data all_data) throws IOException {
		//Identify course to view
		Course course = identify_course(all_data);
		//display students in specific course list
		if (course.getStudents().size() < 1) {
			System.out.println("There are no students in this course.");
		}
		else {
			ArrayList<Student> students = course.getStudents();
			for (Student student: students) {
				System.out.println(student.getFirst_name()+" "+student.getLast_name());
			}
		}
		System.out.println();
		Scanner scan = new Scanner(System.in);
		//Prompt user for next action
		System.out.println();
		System.out.println("(S) view registered students in another course");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("S") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(S) view registered students in another course");
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("S")) {
			view_registered_students(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}	
		System.out.println();
	}

	//Method: view courses a specific student is registered in
	//Input: all data
	//Output: void
	@Override
	public void view_registered_courses(All_data all_data) throws IOException {
		//Obtain student information
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter first name: ");
		String first_name = scan.nextLine();
		System.out.println("Enter last name: ");
		String last_name = scan.nextLine();
		
		Student new_student = new Student();
		//Identify student
		boolean found = false;
		for (Student student: all_data.all_students) {
			if (student.getFirst_name().equals(first_name) && student.getLast_name().equals(last_name)) {
				new_student = student;
				found = true;
			}
		}
		//display course list for student
		if (found == true) {
			if (new_student.getCourses().size() >= 1) {
				ArrayList<Course> student_courses = new_student.getCourses();
				for (Course course: student_courses) {
					System.out.print(course.getName()+" "+course.getCourse_ID()+" "+course.getInstructor()
					+" "+course.getMax_students()+" "+course.getEnrolled_students()+" "+course.getSection()
					+" "+course.getLocation());
				}
			}
			else {
				System.out.println("Student not registered in courses yet.");
			}
		}
		else {
			System.out.println("Student not found.");
		}
		System.out.println();
		//Prompt user for next action
		System.out.println();
		System.out.println("(V) view courses for another student");
		System.out.println("(M) return to main menu");
		System.out.println("(X) exit program");
		String action = scan.nextLine();
		//Verify indicated action
		while (!action.equals("V") && !action.equals("M") && !action.equals("X")) {
			System.out.println("Invalid input. Try again.");
			System.out.println();
			System.out.println("(V) view courses for another student");
			System.out.println("(M) return to main menu");
			System.out.println("(X) exit program");
			action = scan.nextLine();
		}
		//Execute indicated action
		if (action.equals("V")) {
			view_registered_courses(all_data);
		}
		if (action.equals("M")) {
			display_menu(all_data);
		}
		if (action.equals("X")) {
			exit(all_data);
		}
		System.out.println();
	}

	//Method: Sort data according to number of enrolled students
	//Input: all data
	//Output: void 
	//@Override
	public void sort(All_data all_data) throws IOException {
		//sort data by descending order of enrolled students
		Collections.sort(all_data.all_courses);
		
		System.out.println();
		Scanner scan = new Scanner(System.in);
		//Prompt user for next action
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
		System.out.println();
	}

	//Method: Serialize updated data to file for next use
	//Input: all data
	//Output: void
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
			if (action.equals("X")) {
				force_quit();
			}
			System.out.println();
		}
	}
}