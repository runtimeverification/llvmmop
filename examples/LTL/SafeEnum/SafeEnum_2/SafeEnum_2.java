
import java.util.*;

public class SafeEnum_2 {
	public static void main(String[] args){
		Vector<Integer> v = new Vector<Integer>();

		v.add(1);
		v.add(2);
		v.add(4);
		v.add(8);

		Enumeration e = v.elements();

		int sum = 0;

		while(e.hasMoreElements()){
			sum += (Integer)e.nextElement();
		}

		v.add(11);
		v.clear();

		System.out.println("sum: " + sum);
	}
}



