package registration.SYSTEM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {
	
	public static String path = "//Users//sage//eclipse-workspace//SageBergerson_Homework1//src//"
			+ "registration//SYSTEM";
	
	public static boolean serialize(All_data all_data) {
		//instantiate an Employee object
		boolean success = false;
				All_data serial_data = new All_data(all_data.all_courses, all_data.all_students);
				try {
					//FileOutput Stream writes data to a file
					FileOutputStream fos = new FileOutputStream(path+"//All_data.ser");
					
					//ObjectOutputStream writes objects to a stream (A sequence of data)
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					//Writes the specific object to the OOS
					oos.writeObject(serial_data);
					
					//Close both streams
					oos.close();
					fos.close();
					System.out.println("Serialization complete");
					success = true;
				} 
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
				return success;
	}
	
	public static All_data deserialize() {
			All_data de_data = null;
				//Now we will deserialize the same object
				
				 try{
					  //FileInputSystem recieves bytes from a file
				      FileInputStream fis = new FileInputStream(path+"//All_data.ser");
				      
				      //ObjectInputStream does the deserialization-- it reconstructs the data into an object
				      ObjectInputStream ois = new ObjectInputStream(fis);
				      
				      //Cast as Employee. readObject will take the object from ObjectInputStream
				      de_data = (All_data)ois.readObject();
				      ois.close();
				      fis.close();
				    }
				    catch(IOException ioe) {
				       ioe.printStackTrace();
				     
				    }
				 catch(ClassNotFoundException cnfe) {
				       cnfe.printStackTrace();
				    
				     }
				    
				    return de_data;

				 }
			}