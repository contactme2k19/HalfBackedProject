package com.springBoot.Admin;


import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

public class readJSON {
	public static void main(String[] args) throws FileNotFoundException, JSONException
	{
	//JSON parser object to parse read file
		 String content = "";
		 try
		    { 
		        content = new String ( Files.readAllBytes( Paths.get("C:\\Users\\ndhandapani\\Documents\\JSONREADER\\102_GSI COMMUNITY 102_JSONAdd.json") ) );
		    JSONArray jArray = new JSONArray(content);
		    String 	firstName = jArray.getJSONObject(1).getString("FirstName");
		    System.out.println(firstName);
		   // System.out.println(courseName);
		 // JSONArray made with the sub array of courses in your JSON
            JSONArray jArrayCourses = jArray.getJSONObject(0).getJSONArray("programs");
		    for (int i = 0; i < jArray.length(); i++) {
                 String name1 = jArray.getJSONObject(i).getString("programs");
                System.out.println("name: "+name1);
                // Loop trough each sub array of courses.
                for (int j = 0; j < jArrayCourses.length(); j++) {
                    // Get courseName of each JSONObject inside your courses sub array
                    String courseName = jArray.getJSONObject(i).getJSONArray("programs").getJSONObject(j)
                            .getString("CourseName");
                    System.out.println("courseName: "+courseName);

                }

            }
		    }
		    catch (IOException e)
		    {
		        e.printStackTrace();
		    }
		  
    
}
}