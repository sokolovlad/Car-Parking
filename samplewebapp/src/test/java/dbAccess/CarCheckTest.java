package dbAccess;

import junit.framework.TestCase;

public class CarCheckTest extends TestCase {

	CarCheck obj;
	String Car_plate;
	
	protected void setUp() throws Exception {
	obj = new CarCheck();
	Car_plate = "111";
	}

	protected void tearDown() throws Exception {
		obj = null;
	}

	public void testDoGetHttpServletRequestHttpServletResponse() {
		String expected = Car_plate;
		String actual = obj.getInitParameter(Car_plate);
		 assertEquals(expected, actual);
	}

}
