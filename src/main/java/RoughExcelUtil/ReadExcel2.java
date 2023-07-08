package RoughExcelUtil;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;


import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ReadExcel2 {
    private static ThreadLocal<Workbook> xssfWorkbook = new ThreadLocal();
    private static DataFormatter dataFormatter = new DataFormatter();
    private static Logger logger = Logger.getLogger(ReadExcel.class.getName());
    private static ThreadLocal<Sheet> xssfSheet = new ThreadLocal();
    private static ThreadLocal<InputStream> fis = new ThreadLocal();

    private ReadExcel2() {}

    private static void setup(String fileName, String SheetName) throws IOException {
        InputStream inputStreamXls = ReadExcel.class.getResourceAsStream("/data/"+fileName+ ".xls");
        InputStream inputStreamXlsx = ReadExcel.class.getResourceAsStream("/data/"+fileName+ ".xlsx");
        if(inputStreamXls != null) {
            fis.set(inputStreamXls);
        }
        else{
            if(inputStreamXlsx == null){
                throw new IOException("Excel detail annotation may be missing or excel file/sheet doesnt Exista");
            }
            fis.set(inputStreamXlsx);
        }
        Workbook xssfWorkbook =  WorkbookFactory.create((InputStream)fis.get()); //new Workbook((InputStream)fis.get());
        xssfSheet.set(xssfWorkbook.getSheet(SheetName));
        xssfWorkbook.close();
    }
    public static Object[][] readData(String[] excelInfo){
        String excelName = excelInfo[0];
        String sheetName = excelInfo[1];
        List<Object[]> results = new ArrayList();

        try{
            setup(excelName, sheetName);
            int numRows = ((XSSFSheet)xssfSheet.get()).getLastRowNum();
            for(int i = 1;i<=numRows;++i){
                Map<String, String> inputValues = getHashMapDataFromRow((Sheet)xssfSheet, i);
                results.add(new Object[]{inputValues});
            }}
        catch(IOException var10){
            logger.warning(var10.getMessage());
        }
        finally{
            IOUtils.closeQuietly((Closeable)fis.get());
        }

        return (Object[][])(results.toArray(new Object[0][]));
    }

    public static List<HashMap<String, String>> readData(String excelName, String sheetName){
        System.out.println("Before read Data: " +Thread.currentThread().getId());
        List<HashMap<String, String>> excelData = new ArrayList();

        try{
            setup(excelName, sheetName);
            int numRows = ((XSSFSheet)xssfSheet.get()).getLastRowNum();
            for(int i = 1;i<=numRows;++i){
                HashMap<String,String> inputValues = getHashMapDataFromRow((Sheet)xssfSheet, i);
                excelData.add(inputValues);
            }}
        catch(IOException var9){
            logger.warning(var9.getMessage());
        }
        finally{
            IOUtils.closeQuietly((Closeable)fis.get());
        }
        return excelData;

    }

    private static HashMap<String, String> getHashMapDataFromRow(Sheet sheet,int rowIndex){
        HashMap<String, String> results = new HashMap();
        String[] columnHeaders = getDataFromRow(sheet,0);
        String[] valuesFromRow =getDataFromRow(sheet,rowIndex);
        for(int i=0;i<columnHeaders.length;++i){
            if(i>=valuesFromRow.length){
                results.put(columnHeaders[i],"");
            }
            else{
                results.put(columnHeaders[i],valuesFromRow[i]);
            }
        }
        return results;}



    private static String[] getDataFromRow(Sheet sheet,int rowIndex){
        FormulaEvaluator formulaEvaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        Row row = sheet.getRow(rowIndex);
        int numcells = row.getLastCellNum();
        String[] result = new String[numcells];
        for(int i=0;i<numcells;++i){
            result[i] = getValueAsString(row.getCell(i), formulaEvaluator);
        }
        return result;
    }

    private static String getValueAsString(Cell cell,FormulaEvaluator formulaEvaluator){
        if(cell!=null){
            CellType cellType = cell.getCellType();
            if(cellType.equals(CellType.BOOLEAN)){
                return String.valueOf(cell.getBooleanCellValue());
            }
            if(cellType.equals(CellType.NUMERIC)){
                return dataFormatter.formatCellValue(cell); }
            if(cellType.equals(CellType.STRING)){
                return cell.getRichStringCellValue().getString();
            }
            if(cellType.equals(CellType.FORMULA)){
                return formulaEvaluator.evaluate(cell).getStringValue();
            }
        }
        return "";
    }





}

