public class CsvWThread extends Thread{
    @Override
    public void run() {
        CsvFileHandler csv = new CsvFileHandler();
        try {
            csv.writeToFile();
            System.out.println("Csv Write executed");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
