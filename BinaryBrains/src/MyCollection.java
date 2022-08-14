
public class MyCollection {
    private static final int MAX_SIZE = 300;
    private Employee[] lockedEmployees;
    private int readCounter= 0 ;
    private int writeCounter = 0;


    private static MyCollection  myCollection = null;

    private MyCollection() {
        lockedEmployees = new Employee[MAX_SIZE];
    }

    public int getLength(){
        return lockedEmployees.length;
    }

    public synchronized static MyCollection initialise(){

        if (myCollection==null) {
            myCollection = new MyCollection();
        }
        return myCollection;
    }

    public synchronized Employee get(){
        if(readCounter>=300){
            System.out.println("Error pa");
            return null;
        }
        return lockedEmployees[readCounter++];
    }



    public  synchronized void  add(Employee employee){
        if(writeCounter>299) return ;
        lockedEmployees[writeCounter++] = employee;
    }
}
