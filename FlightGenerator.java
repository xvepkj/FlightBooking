import java.util.*;
import java.io.*;
import java.lang.*;

class FlightGenerator{
	public static void main(String args[]) throws IOException{
		String[] cities={"Delhi","Mumbai","Chennai","Bengaluru", "Goa"};
		String[] airlines={"Vistara","Indigo","SpiceJet","GoAir","AirIndia"};
		BufferedWriter bf=new BufferedWriter(new FileWriter("flights.txt"));
		int flid=1;
		for(int i=0;i<10000;i++)
		{	
			int date=(int)(Math.random() * 31)+1;
			int month=11;
			int source=(int)(Math.random() * 5);
			int ser=(int)(Math.random() * 5);
			int destination=(int)(Math.random() * 5);
			if(source==destination)destination=4-source;
			bf.write(flid+" "+cities[source]+" "+cities[destination]+" "+date+"/"+month+"/2019"+" "+airlines[ser]);
			bf.newLine();
			bf.flush();
			flid++;
		}
	}
}