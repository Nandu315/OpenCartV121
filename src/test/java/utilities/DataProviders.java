package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider-1
	
	 @DataProvider(name="LoginData")
		public String[][] getData() throws IOException
		{
			String path = ".\\testdata\\logindata.xlsx"; //taking xl file from testdata
			ExcelUtility xlutils=new ExcelUtility(path); //creating an object for XLutility
			
			int totalrows=xlutils.getRowCount("sheet1");
			int totalcols=xlutils.getCellCount("sheet1", 1);
			
			String logindata[][]=new String[totalrows][totalcols]; //created for two dimension array which can store
			for(int i=1;i<=totalrows;i++)//1  // read the data from xl storing into two Dimensional array
			{
				for(int j=0;j<totalcols;j++)//0 i is rows j is col
				{
					logindata[i-1][j]=xlutils.getCellData("sheet1",i,j); //1, 0
				}
			}
			return logindata; //returning 2d array
				
			}
		 //in future if need another dataprovider we can add here
		//Data Provider 2
		//Data Provider 2

		//Data Provider 2

		

}
