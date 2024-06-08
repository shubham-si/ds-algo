package serialization;

import java.io.*;

/** JavaMadeSoEasy.com */
public class SingletonClass implements Serializable{
    // volatile: don's consider this field for serialization
    private static final long serialVersionUID = 1L;
    private static SingletonClass INSTANCE = null;

    //method returns instance of Singleton class.
    public static SingletonClass getInstance() {
        if (INSTANCE == null) {
           synchronized (SingletonClass.class) {
                  INSTANCE = new SingletonClass();
           }
        }
        return INSTANCE;
    }

    //constructor
    private SingletonClass() {}

    /**
     *customize Serialization process.
     */
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
       System.out.println("in readObject()");
           ois.defaultReadObject();
        synchronized (SingletonClass.class) {
         if (INSTANCE == null) {
               INSTANCE = this;
         }
        }
    }

    /**
     * Method ensures that we don't break singleton pattern during DeSerialization process- method must not be called other than DeSerialization time.
     */
    private Object readResolve() throws ObjectStreamException {
       System.out.println("in readResolve()");
       return INSTANCE;
    }

    private Object writeReplace() throws ObjectStreamException {
    	return INSTANCE;
    }

    public static void main(String[] args) throws Throwable {
           System.out.println("Are objects same before serialization : "+ (getInstance() == getInstance()) );

           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           ObjectOutput oout = new ObjectOutputStream(bos);
           System.out.println("Serialization process has started...");
           oout.writeObject(getInstance());
           bos.close();
           oout.close();
           System.out.println("Object Serialization completed.");

           //DeSerialization process >>>>>>>.

           ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
           ObjectInput oin=new ObjectInputStream(bis);
           System.out.println("\nDeSerialization process has started...");
           SingletonClass deSerializedObj = (SingletonClass)oin.readObject();
           bis.close();
           oin.close();
           System.out.println("Object DeSerialization completed.");
           System.out.println("Are objects same after serialization : "+ (deSerializedObj == getInstance()) );
    }
}
