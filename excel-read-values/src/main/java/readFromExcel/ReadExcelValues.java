package readFromExcel;

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelValues {
public ArrayList<Double> ReadExcelValue(String file){
	ArrayList<Double> excelValues=new ArrayList<>();
	FileInputStream inputStream = null;
	try {
	inputStream = new FileInputStream(new File(file));
    Workbook workbook = new XSSFWorkbook(inputStream);
    Sheet firstSheet = workbook.getSheetAt(0);
    java.util.Iterator<Row> iterator = firstSheet.iterator();
    int count=0;
    while (iterator.hasNext()) {
        Row nextRow = iterator.next();
        Iterator<Cell> cellIterator = nextRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if(count==0)
            {
            	count++;
            	continue;
            }
            excelValues.add(cell.getNumericCellValue());
            //to read String values, use cell.getStringCellValue() and
            //read into ArrayList<String> and change return type of the method to ArrayList<String>
        }
    }
	workbook.close();
	inputStream.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return excelValues;
}
public static void main(String[] args) {
	ReadExcelValues readIds = new ReadExcelValues();
	ArrayList<Double> excelValuess= new ArrayList<>();
	excelValuess=readIds.ReadExcelValue("Path To Your Excel File");
	System.out.println(excelValuess);
	System.out.println("There are "+excelValuess.size()+" values present in the Excel file");
	}
}