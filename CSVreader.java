package registration.FILES;

//Import necessary modules
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//CSV READER CLASS
public class CSVreader {

	//Method: MAIN
	public static void main(String[] args) throws IOException {
		//File path
		String path = "//Users//sage//eclipse-workspace//SageBergerson_Homework1//src//"
				+ "registration//FILES";
		//prepare readers
		File csvFile = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(csvFile+"//MyUniversityCourses.csv"));
	    BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile+"//Course_data.txt"));    
		String line = "";
		//read in data from csv file
		try {
			while ((line = br.readLine()) != null) {
				String[] count = line.split(",");
				for (String item: count) {
					bw.append(item+",");
				}
				bw.append("\n");
				System.out.println(count[0]+" "+count[1]+" "+count[2]+" "+count[3]
						+" "+count[4]+" "+count[5]+" "+count[6]+" "+count[7]);
			}
			 bw.close();  
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}