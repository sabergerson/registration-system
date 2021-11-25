package registration.SYSTEM;

//Import necessary packages
import java.io.BufferedReader;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//DATA_LOADING CLASS
public class Data_loading {
		
	//default constructor 
	public Data_loading() {
	}

	//Method: Load course data from txt file
	public static ArrayList<Course> load_course_data() throws NumberFormatException, IOException {
		//Create array to hold courses
		ArrayList<Course> courses = new ArrayList<Course>();
		//Assign file path
		String path = "//Users//sage//eclipse-workspace//SageBergerson_Homework1//src//"
				+ "registration//FILES";
		//read course data from file
		File csvFile = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(csvFile+"//Course_data.txt"));
		String line = "";
		try {
			//skip first line with column names
			br.readLine();
			while ((line = br.readLine()) != null) {
				//Split each row into its individual data points
				String[] count = line.split(",");
				ArrayList<Student> students = new ArrayList<Student>();
				//set student list to null if none registered
				if (count[4].equals("NULL")) {
				}	
				//add student data otherwise
				else {
					int index = 4;
					while (index < count.length-3) {
						String[] student_name = count[index].split(" ");
						students.add(new Student(student_name[0], student_name[1], student_name[2], 
							student_name[3]));
						index++;
					}
				}
				//add formatted data to array
				courses.add(new Course(count[0], count[1], count[count.length-2], count[count.length-3],
						count[count.length-1], Integer.valueOf(count[2]), Integer.valueOf(count[3]),
						students));
			}
		}
		//catch exceptions
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		br.close();
		//return course list
		return courses;	
	}
	
	//Method: Load student data
	//Input: ArrayList of courses
	//Output: void
	public static ArrayList<Student> load_student_data(ArrayList<Course> course_list) {
		ArrayList<Student> all_students = new ArrayList<Student>();
		//find student list for each course
		for (Course course: course_list) {
			ArrayList <Student> course_students = course.getStudents();
			if (!course_students.equals(null)) {
				for (Student student: course_students) {
					//add students to student list
					all_students.add(student);
				}
			}
			else {
			}
		}
		//return student list
		return all_students;
	}
	
	//Method: serialize data
	//Input: all data
	//Output: boolean
	public boolean serialize(All_data all_data) throws IOException {	
		//set file path
		String path = "//Users//sage//eclipse-workspace//SageBergerson_Homework1//src//"
				+ "registration//SYSTEM";
		boolean success;
		//use iterator to serialize all data object to file
		try {
			FileOutputStream fileOut = new FileOutputStream(path+"//Serial_data.txt");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(all_data);    
	        objectOut.close();
	        fileOut.close();
	        //update success to true
	        success = true;
	        System.out.println("Saving data...");
		}
		//catch exception if file not found
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
			//update success to false
			success = false;
		}
		//catch I/O exception
		catch (IOException ex) {
			ex.printStackTrace();
			//update success to false
			success = false;
		}       
		//return success
	    return success;
	}
	
	//Method: deserialize object from file
	//Input: none
	//output: all data
	public All_data deserialize() throws ClassNotFoundException, FileNotFoundException, IOException {
		//set file path
		String path = "//Users//sage//eclipse-workspace//SageBergerson_Homework1//src//"
				+ "registration//FILES";
		boolean success;
		All_data all_data = null;
		//deserialize object from file
		try {   
			FileInputStream fileIn = new FileInputStream(path+"//Serial_data.txt");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			all_data = (All_data)objectIn.readObject();         
	        objectIn.close();
	        fileIn.close();
	        //update success to true if successful
	        success = true;
		}     
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		//catch end of file exception
		catch (EOFException ex) {
			ex.printStackTrace();
			//update success to false
			success = false;
		}
		//catch class not found exception
	    catch(ClassNotFoundException | IOException ex) {
	        ex.printStackTrace();
	        //update success to false
	        success = false;  
	    }
		//return success
		return all_data;
	}	
}