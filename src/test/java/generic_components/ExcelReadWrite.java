package generic_components;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


public class ExcelReadWrite 
 {
	FileInputStream fis;
	HSSFWorkbook wb;
	 public ExcelReadWrite(String xlpath) throws IOException
	 {
		 fis= new FileInputStream(xlpath);
		 wb=new HSSFWorkbook(fis);
	 }
	 public HSSFSheet setsheet(String sheetname)
	 {	
		 HSSFSheet sheet = wb.getSheet(sheetname);
		return sheet;
	 }
	 public int getrowcount(HSSFSheet sheet)
	 {	
		 int lastrowNum = sheet.getLastRowNum();
		return lastrowNum;
	 }
	 public int getcolcount(HSSFSheet sheet,int rowindex)
	 {	
         short lastcellNum = sheet.getRow(rowindex).getLastCellNum();
		 return lastcellNum;
  	 }
	 
	 public String readdata(HSSFSheet sheet,int rowindex,int celnum)
	 {	
		 HSSFCell cell = sheet.getRow(rowindex).getCell(celnum);
		 String celtext=null;
		 
		 if(cell==null)
		 celtext="";
		 
		 else if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		 celtext=cell.getStringCellValue();
		 
		 else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC)
		 celtext=String.valueOf(cell.getNumericCellValue());
		 
		 else if(cell.getCellType()==cell.CELL_TYPE_BLANK)
		 celtext="";
		return celtext;
		
	 }
	public String readdata(HSSFSheet sheet,int rowindex,String colname)
	{	
		int colindex=0;
		for(int i=0;i<getcolcount(sheet, 0);i++)
		{
			if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname))
				colindex=i;
		}
		return readdata(sheet, rowindex, colindex);
	}
	 
	public void writedata(HSSFSheet sheet,int rowindex,int celnum,String wdata)
	{
      HSSFCell wcell = sheet.getRow(rowindex).getCell(celnum);
      if(wcell==null)
      {
    	  wcell=sheet.getRow(rowindex).createCell(celnum);;
      }
     
      wcell.setCellValue(wdata);
	}
	public void writedata(HSSFSheet sheet,int rowindex,String colname,String wdata)
	{
		int colindex=0;
		for(int i=0;i<getcolcount(sheet, 0);i++)
		{
			if(sheet.getRow(0).getCell(i).getStringCellValue().trim().equalsIgnoreCase(colname))
				colindex=i;
		}
		writedata(sheet, rowindex, colindex,wdata);
	}
	
	public void saveexcel(String xlpath) throws IOException
	{
		fis.close();
		FileOutputStream fos= new FileOutputStream(xlpath);
		wb.write(fos);
		fos.close();
	}
			
	 
 }
