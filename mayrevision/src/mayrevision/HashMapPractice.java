package mayrevision;

import java.util.HashMap;


public class HashMapPractice {
    public static void main(String[] args) {
    	HashMap<Employee,String> map = new HashMap<>();


    	Employee e1 = new Employee(101, null, null, 0, 0, 0);
    	Employee e2 = new Employee(101, null, null, 0, 0, 0);

    	map.put(e1, "Alice");
    	map.put(e2, "Bob");

    	System.out.println(map.size());
    	System.out.println(e1.hashCode());
    	System.out.println(e2.hashCode());
    	
    }}