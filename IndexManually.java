import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.net.*;
import java.io.*;

public class IndexManually {
	

public void mySolrQuery(String host, String coreName, String nodeID, String action) throws Exception {

    String myHost = host;
    String myCoreName = coreName;
    String myNodeID = nodeID;
    String myAction = action;
    
    switch (Integer.parseInt(action)) {
    case 1:
     myAction = "INDEX";
      break;
    case 2:
      myAction = "REINDEX";
      break;
    case 3:
      myAction = "FIX";
      break;
    case 4:
      myAction = "PURGE";
  }
    
    URL solr = new URL(myHost + "/solr/admin/cores?action=" + myAction +"&core=" + myCoreName + "&nodeid=" + myNodeID);
    URLConnection sc = solr.openConnection();
    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                            sc.getInputStream()));
    String inputLine;
    
   while ((inputLine = in.readLine()) != null) {
        System.out.println(inputLine);
   
       try {
	    BufferedWriter writer = new BufferedWriter(new FileWriter("c:\\tmp\\log.log", true));
	    writer.append('\n');
	    writer.append(inputLine);
	    writer.close();
	    } catch (IOException e) 
        {
	    	System.out.println("An IO error occurred.");
  	      e.printStackTrace();
        }
        
       }
    in.close();
    
}



  public static void main(String[] args) {
	  
	  
	  IndexManually iM = new IndexManually();
	  
	  System.out.println("Main started");

	  Scanner sc=new Scanner(System.in);  
	  
	//  System.out.println("Enter the path to the log file :"); 
	  System.out.println("What is the action required?" + "\n" + "1: INDEX" + "\n" + "2: REINDEX" + "\n" + "3: FIX" + "\n" + "4: PURGE"); 
	//reads string  
	 String action = sc.nextLine();
	//  
	  System.out.println("Enter the host name and port including http/https :");
	  String  host = sc.nextLine();
	//  
	  System.out.println("Enter the core name  :");
	  String  coreName = sc.nextLine();
	 // coreName.toLowerCase() not used as core name is case sensitive .. 
	  System.out.println("Enter the path to the file with the list of nodes :");
	  String  pathToListOfNodes = sc.nextLine();
	//
	  
    try {
  
     File myObj = new File(pathToListOfNodes);
      Scanner myReader = new Scanner(myObj);
      String solrResponse;
      
      while (myReader.hasNextLine())
        {
    	  String data = myReader.nextLine();
    	    try {
    	        iM.mySolrQuery( host, coreName, data, action);  
    	        
    	     
    	        try {
    	    	    BufferedWriter writer = new BufferedWriter(new FileWriter("c:\\tmp\\log.log", true));
    	    	    writer.append('\n');
    	          writer.append(data);
    	    	    writer.close();
    	    	    } catch (IOException e) 
    	            {
    	    	    	System.out.println("An IO error occurred.");
    	      	      e.printStackTrace();
    	            }
    	            
    	       
    	        } catch (Exception e) 
    	        {
    	     System.out.println("An error occurred in the while loop.");
    	      e.printStackTrace();
    	    }
    	
            

        }
      myReader.close();
        } catch (FileNotFoundException e) 
        {
      System.out.println("An error occurred .");
      e.printStackTrace();
        }
    
  
  }
}

