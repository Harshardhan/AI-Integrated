package mayrevision;

import java.util.Objects;

public class StringPrograms {

	int id;
	String name;
	
	
	
	
	public StringPrograms(int id, String name) {
		this.id = id;
		this.name = name;
	}




	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StringPrograms other = (StringPrograms) obj;
		return id == other.id && Objects.equals(name, other.name);
	}




	public static void main(String[] args) {

		StringPrograms s1 = new StringPrograms(1, "Harsha");
		StringPrograms s2 = new StringPrograms(1, "Harsha");

		System.out.println(s1.equals(s2));
}
}