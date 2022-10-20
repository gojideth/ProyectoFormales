package models;
import java.util.ArrayList;

public interface Tree<T> {
	
	boolean isEmpty();
	void addElement(T element);
	String print();
	boolean contains(T element);
	String removeElement(String value);
	
	
	
	

}
