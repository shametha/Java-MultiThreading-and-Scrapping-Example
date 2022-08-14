public class JsonRThread extends Thread {
    @Override
    public void run(){
        JsonFileHandler json=new JsonFileHandler();
        try {
            json.readFromFile();
            System.out.println("Json Read executed");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
