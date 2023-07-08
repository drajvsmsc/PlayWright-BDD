package StepDefinitions;

import java.util.ArrayList;
import java.util.Map;

import java.util.List;

import RoughExcelUtil.ReadExcel2;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class DefaultStepDefinition {
    private static ThreadLocal<String> testDataId = new ThreadLocal<>();
    private static ThreadLocal<String> scenarioName =  new ThreadLocal<>();
    private static ThreadLocal<String> excel = new ThreadLocal<>();
    private static ThreadLocal<String> sheet = new ThreadLocal<>();
    private static ThreadLocal<ArrayList<Map<String, String>>> excelData = ThreadLocal.withInitial(() -> new ArrayList<>());
    public static ThreadLocal<Map<String, String>> currentIterationMap = new ThreadLocal<Map<String, String>>();
    // private static ThreadLocal<ArrayList<Map<String, String>>> excelData = new ThreadLocal<ArrayList<Map<String, String>>>(){
    //     @Override
    //     protected ArrayList<Map<String, String>> intialValue(){
    //         return new ArrayList<>();
    //     }
    //     };
    @Before
    public void readScenarioName(Scenario scenario){
        testDataId.set(scenario.getName());
    }  
    

    @Given("A workbook named {string} and sheet named {string} is read")
    public void A_workbook_named_and_sheet_named_is_read(String excelName, String SheetName) {
        // Write code here that turns the phrase above into concrete actions
        if(scenarioName.get() == null||!SheetName.equals(sheet.get())||!excelName.equals(sheet.get())||!testDataId.get().equals(scenarioName.get())){
            excelData.get().addAll(ReadExcel2.readData(excelName, SheetName));
        }
        List<Map<String,String>> removeData = new ArrayList<>();
        for(Map<String,String>map:excelData.get()){
            if(map.get("Scenario").equals(testDataId.get())){
                currentIterationMap.set(map);
                removeData.add(map);
                break;
            }
        }
        if(!removeData.isEmpty()){
            excelData.get().remove(removeData.get(0));
        }
        scenarioName.set(testDataId.get());
        sheet.set(SheetName);
        excel.set(excelName);
        
        

        
    }
    
}
