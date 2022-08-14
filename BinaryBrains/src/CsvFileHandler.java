import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvFileHandler implements MyFileHandler {
    MyCollection myCollection=MyCollection.initialise();
    public void readFromFile() throws Exception
    {
        try {
            FileReader fileReader = new FileReader("/Users/sreeramkt/Downloads/drive-download-20220713T130556Z-001/employee.csv");
            BufferedReader csvReader = new BufferedReader(fileReader);
            String line = csvReader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                line = csvReader.readLine();
                Employee e = new Employee();
                Date date = new SimpleDateFormat("dd/MM/yy").parse(data[2]);
                Long exp = Long.parseLong(data[3]);
                e.setDateOfBirth(date);
                e.setExperience(exp);
                e.setFirstName(data[0]);
                e.setLastName(data[1]);
                //System.out.println(data[0]+" "+data[1]+" "+date+" "+exp);
                myCollection.add(e);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void writeToFile() throws Exception
    {
        try {

            FileWriter csvWriter = new FileWriter("/Users/sreeramkt/Downloads/csvWriteOutput.csv");
            for(int i=0;i<100;i++)
            {
                Employee employee = myCollection.get();
                csvWriter.append(employee.getFirstName());
                csvWriter.append(",");
                csvWriter.append(employee.getLastName());
                csvWriter.append(",");
                String strDate = (new SimpleDateFormat("dd-mm-yy")).format(employee.getDateOfBirth().getTime());
                csvWriter.append(strDate);
                csvWriter.append(",");
                String exp=Double.toString(employee.getExperience());
                csvWriter.append(exp);
                csvWriter.append("\n");
            }
            csvWriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}