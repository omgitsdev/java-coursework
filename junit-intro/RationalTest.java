//author Zifeng Mai, Dev Sethi

import static org.junit.Assert.*;

import org.junit.Test;


public class RationalTest {

	@Test
	public void testAdd() {
		assertEquals(new Rational(3,4).toString(),new Rational(1,4).add(new Rational(1,2)).toString());
		assertEquals(new Rational(1,1).toString(),new Rational(16,17).add(new Rational(1,17)).toString());
		assertEquals(new Rational(1,4).toString(),new Rational(1,8).add(new Rational(1,8)).toString());
				
	}

	@Test
	public void testSub() {
		assertEquals(new Rational(1,4).toString(),new Rational(3,4).sub(new Rational(1,2)).toString());
		assertEquals(new Rational(1,17).toString(),new Rational(1,1).sub(new Rational(16,17)).toString());
		assertEquals(new Rational(1,8).toString(),new Rational(1,4).sub(new Rational(1,8)).toString());
	}

	@Test
	public void testMul() {
		assertEquals(new Rational(3,8).toString(),new Rational(3,4).mul(new Rational(1,2)).toString());
		assertEquals(new Rational(16,17).toString(),new Rational(1,1).mul(new Rational(16,17)).toString());
		assertEquals(new Rational(1,32).toString(),new Rational(1,4).mul(new Rational(1,8)).toString());
	}

	@Test
	public void testDiv() {
		assertEquals(new Rational(3,4).toString(),new Rational(3,8).div(new Rational(1,2)).toString());
		assertEquals(new Rational(16,17).toString(),new Rational(16,17).div(new Rational(1,1)).toString());
		assertEquals(new Rational(8,1).toString(),new Rational(1,4).div(new Rational(1,32)).toString());
	}

	@Test
	public void testSqr() {
		assertEquals(new Rational(9,64).toString(),new Rational(3,8).sqr().toString());
		assertEquals(new Rational(256,289).toString(),new Rational(16,17).sqr().toString());
		assertEquals(new Rational(1,16).toString(),new Rational(1,4).sqr().toString());
		}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDecimal() {
		assertEquals(new Rational(6,4).decimal(), new Rational(3,2).decimal(),0);
		assertEquals((float)3/2, new Rational(3,2).decimal(),0);
		assertEquals(1, new Rational(3,3).decimal(),0);
		assertEquals(0, new Rational(0,2).decimal(),0);
		}

	@Test
	public void testReduceToLowestForm() {
		// Tested implicitly in other functions which call this

}
}
