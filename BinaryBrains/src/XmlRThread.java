public class XmlRThread extends Thread {
    @Override
    public void run() {
        XmlFileHandler xml = new XmlFileHandler();
        try {
            xml.readFromFile();
            System.out.println("Xml Read executed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
