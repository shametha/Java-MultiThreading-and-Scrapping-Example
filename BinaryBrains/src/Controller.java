public class Controller extends Thread{
    public static void main(String[] args) throws Exception{
        JsonRThread JsonReadThread = new JsonRThread();
        XmlRThread XmlReadThread = new XmlRThread();
        CsvRThread CsvReadThread = new CsvRThread();
        JsonReadThread.start();
        XmlReadThread.start();
        CsvReadThread.start();
        JsonReadThread.join();
        XmlReadThread.join();
        CsvReadThread.join();
        JsonWThread JsonWriteThread=new JsonWThread();
        XmlWThread XmlWriteThread=new XmlWThread();
        CsvWThread CsvWriteThread=new CsvWThread();
        JsonWriteThread.start();
        XmlWriteThread.start();
        CsvWriteThread.start();
        JsonWriteThread.join();
        XmlWriteThread.join();
        CsvWriteThread.join();
    }
}
