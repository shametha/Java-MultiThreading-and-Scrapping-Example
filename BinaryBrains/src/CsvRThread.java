public class CsvRThread extends Thread {
    @Override
    public void run() {
        CsvFileHandler csv=new CsvFileHandler();
        try {
            csv.readFromFile();
            System.out.println("CSV Read executed");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
