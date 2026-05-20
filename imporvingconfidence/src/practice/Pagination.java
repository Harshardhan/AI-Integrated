package practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pagination {

    public static List<String> paginate(
            List<String> data,
            int pageNumber,
            int pageSize) {

        return data.stream()
                .skip((long) pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
		
    	List<String> list = Arrays.asList("A","B","c","D","E","F",
    			"G","H","I","J","K");
    			
    List<String> pages = paginate(list, 1, 5);
    
    System.out.println(pages);
    
    
	}
}
