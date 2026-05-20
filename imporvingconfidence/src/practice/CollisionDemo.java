package practice;

import java.util.HashMap;
import java.util.Map;

public class CollisionDemo {
    public static void main(String[] args) {

        Map<StudentKey, String> map = new HashMap<>();

        map.put(new StudentKey(1), "Harsha");
        map.put(new StudentKey(2), "Vardhan");
        map.put(new StudentKey(3), "Java");
        map.put(new StudentKey(4), "Backend");

        map.get(new StudentKey(3));

        System.out.println(map);
    }
}
