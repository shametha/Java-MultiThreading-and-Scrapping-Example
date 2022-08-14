import java.util.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.lang.String;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.FileWriter;


public class JsonFileHandler implements MyFileHandler {
    MyCollection myCollection=MyCollection.initialise();
    public void readFromFile() throws Exception
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("/Users/sreeramkt/Downloads/drive-download-20220713T130556Z-001/employee.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;

            for(Object jsonObject: employeeList){
                JSONObject employee =(JSONObject) jsonObject;
                String FirstName = (String) employee.get("firstName");
                String LastName = (String) employee.get("lastName");
                String DateOfBirth = (String) employee.get("dateOfBirth");
                Long Experience = (Long) employee.get("experience");
                //System.out.println(FirstName+" "+LastName+" "+DateOfBirth+" "+Experience);
                Employee e = new Employee();
                Date date=new SimpleDateFormat("dd/MM/yy").parse(DateOfBirth);
                e.setDateOfBirth(date);
                e.setExperience(Experience);
                e.setFirstName(FirstName);
                e.setLastName(LastName);
                myCollection.add(e);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile() throws Exception
    {
        JSONArray employeeList = new JSONArray();
        for(int i=0;i<100;i++) {
            Employee employees=myCollection.get();
            JSONObject employee = new JSONObject();
            employee.put("firstName", employees.getFirstName());
            employee.put("lastName", employees.getLastName());
            employee.put("DateOfBirth", employees.getDateOfBirth());
            employee.put("Experience", employees.getExperience());

            employeeList.add(employee);
        }
        try (FileWriter file = new FileWriter("/Users/sreeramkt/Downloads/jsonWriteOutput.json")) {
            file.write(employeeList.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

