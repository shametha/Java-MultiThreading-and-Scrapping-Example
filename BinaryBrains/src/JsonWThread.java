public class JsonWThread extends Thread {
    @Override
    public void run() {
        JsonFileHandler json = new JsonFileHandler();
        try {
            json.writeToFile();
            System.out.println("Json Write executed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
