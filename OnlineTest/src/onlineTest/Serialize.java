package onlineTest;
import java.io.*;
public class Serialize<T> implements Serializable{

	protected void saveObject(T object, String fileName) {
		
		File file = new File(fileName);
		try {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));

		output.writeObject(object);
		output.close();
		} catch (IOException e){
			
			e.printStackTrace();
		}
	}
	
	protected SystemManager restoreObject(String fileName) {
		
		File file = new File(fileName);

		try {
			
			if (!file.exists()) {
				return new SystemManager();
			} else {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
				SystemManager manager = (SystemManager) input.readObject();
				input.close();
				
				return manager;
			}
		} catch(IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}
	
}
