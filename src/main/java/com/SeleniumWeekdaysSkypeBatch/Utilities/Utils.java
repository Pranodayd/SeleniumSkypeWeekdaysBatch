package com.SeleniumWeekdaysSkypeBatch.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Utils 
{

	
	public static HashMap <String,String>EnvVars=new HashMap<String,String>();
	public static void Initialise_Env_Vars()
	{
		/*
		 * 1.Open EnvVars.csv in read mode.
		 * 2.Read file LINE BY LINE till end.
		 * 3.Split line using ","
		 * 4.Use 1st part after splitting as KEY in HashMap
		 * 5.Use 2ns part after splitting as VALUE in HashMap
		 */
		String FilePath=System.getenv("SeleniumConfigFiles");
		BufferedReader Br=null;
		FileReader Fr=null;
		try 
		{
			Fr=new FileReader(new File(FilePath));
			Br=new BufferedReader(Fr);
			
			
			String Line=Br.readLine();
			while(Line!=null)
			{	
				String[]Pieces=Line.split(",");
				EnvVars.put(Pieces[0], Pieces[1]);
				Line=Br.readLine();
			}
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Please check EnvVars file is present at "+FilePath);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Please check EnvVars file at "+FilePath+" should not be locked");
		}
		
		
		
	}

	public static String[][] ReadTestData(String FilePath)
	{
		
		String[][]DataRow = null;
		
		try {
			System.out.println(Utils.EnvVars.get("DataFilesPath")+"\\"+FilePath+".csv");
			FileReader FR=new FileReader(Utils.EnvVars.get("DataFilesPath")+"\\"+FilePath+".csv");
			BufferedReader BR=new BufferedReader(FR);
			String Line=BR.readLine();
			DataRow=new String[5][2];
			int i=0;
			while(Line!=null)
			{
				
				String[]parts=Line.split(",");
				DataRow[i][0]=parts[0];
				DataRow[i][1]=parts[1];
				i++;
				Line=BR.readLine();
			}	
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Environment variables file is not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problem while file reading");
		}
		
		return DataRow;
		
		
	}

}
