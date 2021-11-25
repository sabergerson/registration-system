package registration.SYSTEM;

//Import necessary packages
import java.io.Serializable;
import java.util.ArrayList;

//ALL DATA CLASS
public class All_data implements Serializable {
	
	//Default serial version ID
	private static final long serialVersionUID = 1L;
	
	//Data fields
	public ArrayList<Course> all_courses;
	public ArrayList<Student> all_students;
	
	//Default constructor
	public All_data() {
	}

	//Constructor
	public All_data(ArrayList<Course> course_list, ArrayList<Student> student_list) {
		this.all_courses = course_list;
		this.all_students = student_list;
	}

	//Accessors and mutators
	public ArrayList<Course> getAll_courses() {
		return all_courses;
	}

	public void setAll_courses(ArrayList<Course> all_courses) {
		this.all_courses = all_courses;
	}

	public ArrayList<Student> getAll_students() {
		return all_students;
	}

	public void setAll_students(ArrayList<Student> all_students) {
		this.all_students = all_students;
	}
}