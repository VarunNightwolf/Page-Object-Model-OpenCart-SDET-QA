package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	// Data Provider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path = ".\\testdata\\Opencart_loginData.xlsx"; // taking xl file from test Data
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for Excel Utility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcolumns = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcolumns]; // created for two dimenion arra which can store data
		
		for (int i=1;i<totalrows;i++) // 1 [read data from xl storing in two dimensional array]
		{
			for (int j=0;j<totalcolumns;j++)  // 0 i is row and j is column
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
			}
		}
		
		return logindata; // returning two dimensional array
	}

}
