package dataprovider_components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import generic_components.ExcelReadWrite;

public class DataProvider_TestData 
{
 @DataProvider(name="dp_invalidtest")
 public static Iterator<Object[]> getinvalidSearchdata() throws IOException
 {
   return commonmethod_for_testdata("Senario_Search","InValidSearch");
 }
 @DataProvider(name="dp_validtest")
 public static Iterator<Object[]> getvalidsearchdata() throws IOException
 {
	 return commonmethod_for_testdata("Senario_Search","ValidSearch");
 }
 @DataProvider(name="dp_AddCart")
 public static Iterator<Object[]> getAddCart() throws IOException
 {
	 return commonmethod_for_testdata("Senario_Cart","AddCart");
 }
 @DataProvider(name="dp_deletecart")
 public static Iterator<Object[]> getdeletecart() throws IOException
 {
	 return commonmethod_for_testdata("Senario_Cart","DeleteCart");
 }
 
 
public static Iterator<Object[]> commonmethod_for_testdata(String sheetname,String Scriptname) throws IOException
{
	ExcelReadWrite xl=new ExcelReadWrite("D:\\My_BB_Projecct_Data\\testdata\\TestData.xls");
	HSSFSheet sheet = xl.setsheet(sheetname);
	int getrowcount = xl.getrowcount(sheet);
	int getcolcount = xl.getcolcount(sheet, 0);
	
	List<Object[]> al=new ArrayList<Object[]>();
	for(int i=1;i<=getrowcount;i++)
	{
		String Exicute_flag = xl.readdata(sheet, i, "Exicute_flag");
		String Script_name = xl.readdata(sheet, i, "Script_name");
	 if((Exicute_flag.equalsIgnoreCase("Y")) && (Script_name.equalsIgnoreCase(Scriptname)))
	   {		
		Object[] obj=new Object[1];
		Map<String,String> hm=new HashMap<String,String>();
		for(int j=0;j<getcolcount;j++)
		{
		  	String skey = xl.readdata(sheet, 0, j);
		  	String value = xl.readdata(sheet, i, j);
		  	hm.put(skey, value);
		}
		
		obj[0]=hm;
		al.add(obj);
		
	  }
    }
	return al.iterator();
  }
 
}
	
	
