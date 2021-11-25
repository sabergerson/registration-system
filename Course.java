package registration.SYSTEM;

//Import necessary packages
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements java.lang.Comparable<Course>, Serializable {
	
	//Data fields
	String name;
	String course_ID;
	String section;
	String instructor;
	String location;
	int max_students;
	int enrolled_students;
	ArrayList<Student> students;
	
	//Default constructor
	public Course() {	
	}
	
	//Constructor
	public Course(String name, String course_ID, String section, String instructor, 
			String location, int max_students, int enrolled_students, ArrayList<Student> students) {
		this.name = name;
		this.course_ID = course_ID;
		this.section = section;
		this.instructor = instructor;
		this.location = location;
		this.max_students = max_students;
		this.enrolled_students = enrolled_students;
		this.students = students;
	}
	
	//Method: Compares to and returns courses ordered by enrolled students
	//Input: Course objects
	//Output: int, difference in enrolled students
	@Override
	public int compareTo(Course o) {
		int compare_enrolled_students = (int)(o).getEnrolled_students();
		return compare_enrolled_students - this.getEnrolled_students();
	} 

	//Accessors and mutators 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse_ID() {
		return course_ID;
	}

	public void setCourse_ID(String course_ID) {
		this.course_ID = course_ID;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMax_students() {
		return max_students;
	}

	public void setMax_students(int max_students) {
		this.max_students = max_students;
	}

	public int getEnrolled_students() {
		return enrolled_students;
	}

	public void setEnrolled_students(int enrolled_students) {
		this.enrolled_students = enrolled_students;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}	
}