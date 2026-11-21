package mayrevision;

public class ExceptionPractice {

		
	
	public void methodA() {
	    methodB();
	}

	public void methodB() {
	    methodC();
	}

	public void methodC() {
	    throw new RuntimeException("Database Error");
	}
}
