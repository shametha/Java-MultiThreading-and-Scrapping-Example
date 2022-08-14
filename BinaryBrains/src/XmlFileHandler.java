import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlFileHandler implements MyFileHandler {
    MyCollection myCollection=MyCollection.initialise();
    public void readFromFile() throws Exception
    {
        try {
            File file = new File("//Users/sreeramkt/Downloads/drive-download-20220713T130556Z-001/employee.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("employee");
            for (int i = 0;i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    String fname= eElement.getElementsByTagName("firstName").item(0).getTextContent();
                    String lname= eElement.getElementsByTagName("lastName").item(0).getTextContent();
                    String dob= eElement.getElementsByTagName("dateOfBirth").item(0).getTextContent();
                    String expe= eElement.getElementsByTagName("experience").item(0).getTextContent();
                    //System.out.println("XML"+" "+fname +" " +lname +" " +dob +" "+ expe);
                    Employee emp=new Employee();
                    emp.setFirstName(fname);
                    emp.setLastName(lname);
                    Date date = new SimpleDateFormat("dd/MM/yy").parse(dob);
                    Long exp = Long.parseLong(expe);
                    emp.setDateOfBirth(date);
                    emp.setExperience(exp);
                    myCollection.add(emp);
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void writeToFile() throws Exception
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            Element rootElement =
                    doc.createElementNS("", "Employees");
            doc.appendChild(rootElement);

            for(int i=0;i<100;i++) {
                Employee employees=myCollection.get();
                String ex = Double.toString(employees.getExperience());
                String strDate = (new SimpleDateFormat("dd-mm-yy")).format(Calendar.getInstance().getTime());

                rootElement.appendChild(getEmployee(doc, employees.getFirstName(), employees.getLastName(), strDate, ex));

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("/Users/sreeramkt/Downloads/xmlWriteOutput.xml"));

            transformer.transform(source, file);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    private static Node getEmployee(Document doc, String fName, String lName, String DOB, String exprience) {
        Element employee = doc.createElement("Employee");
        employee.appendChild(getEmployeeElements(doc, employee, "firstName", fName));
        employee.appendChild(getEmployeeElements(doc, employee, "lastName", lName));
        employee.appendChild(getEmployeeElements(doc, employee, "dateOfBirth", DOB));
        employee.appendChild(getEmployeeElements(doc, employee, "experience", exprience));
        return employee;
    }
    private static Node getEmployeeElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}

