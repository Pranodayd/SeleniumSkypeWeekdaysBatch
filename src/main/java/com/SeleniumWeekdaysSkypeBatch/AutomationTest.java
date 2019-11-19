package com.SeleniumWeekdaysSkypeBatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.SeleniumWeekdaysSkypeBatch.Utilities.Utils;

public class AutomationTest 
{

	RemoteWebDriver D;
	
	public HashMap<String,WebElement>ObjectRepo=new HashMap<String,WebElement>();
	
	public void StartBrowser(String Browser)
	{
		
		switch(Browser)
		{
			case "chrome":
				System.setProperty("webdriver.chrome.driver", Utils.EnvVars.get("ChromeDriverPath"));
				D=new ChromeDriver();
				break;
		
		}
		
		
	}
	
	public void OpenURL()
	{
		D.get(Utils.EnvVars.get("AppURL"));
		
	}
	
	
	public void EnterText(WebElement E,String S)
	{
		E.clear();
		E.sendKeys(S);
		
		
	}
	
	
	public void EnterText(String Stratergy,String Locator,String S)
	{
		FindAndReturnElement(Stratergy, Locator).sendKeys(S);;
		
	}
	public void ClickElement(WebElement E)
	{
		E.click();
	}


	public void CreateObjectRepo(String FileName)
	{
		
		String FilePath=Utils.EnvVars.get("ObjectRepositoriesPath")+"\\"+FileName+".csv";
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
				WebElement E=FindAndReturnElement(Pieces[1],Pieces[2]);
				ObjectRepo.put(Pieces[0], E);
				Line=Br.readLine();
			}
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Please check ObjectRepository file is present at "+FilePath);
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Please check ObjectRepository file at "+FilePath+" should not be locked");
		}
		
	}
	
	 WebElement FindAndReturnElement(String Stratergy,String Locator)
	{
		WebElement FoundElement=null;
		switch(Stratergy)
		{
			case "BY_ID":
				FoundElement=D.findElementById(Locator);
				break;
			case "BY_NAME":
				FoundElement=D.findElementByName(Locator);
				break;
			case "BY_CSS":
				FoundElement=D.findElementByCssSelector(Locator);
				break;
			case "BY_XPATH":
				FoundElement=D.findElementByXPath(Locator);
				break;
			case "BY_TAGNAME":
				FoundElement=D.findElementByTagName(Locator);
				break;
		}
	
		return FoundElement;
	}


	public String GetTitle()
	{
		
		return D.getTitle();
	}

	public void OpenComboBoxAndSelectValue(WebElement E,String ElementSelectStratergy,String ElementValue)
	{
		
	}
}
