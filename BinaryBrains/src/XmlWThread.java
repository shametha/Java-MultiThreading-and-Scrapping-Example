public class XmlWThread extends Thread {
    public void run(){
        XmlFileHandler xml=new XmlFileHandler();
        try {
            xml.writeToFile();
            System.out.println("XML write executed");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
