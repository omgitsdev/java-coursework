//author Zifeng Mai, Dev Sethi

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class PolynomialTest2 {

	Polynomial p1;
	Polynomial p2;
	Polynomial p3;
	Polynomial p4;
	Polynomial p5;
	HashMap<Integer, Rational> co1;
	HashMap<Integer, Rational> co2;
	HashMap<Integer, Rational> co3;
	HashMap<Integer, Rational> co4;
	HashMap<Integer, Rational> co5;
	
	@Before
	public void setUp() throws Exception {
		co1 = new HashMap<Integer, Rational>();
		co1.put(6, new Rational(4, 9));
		co1.put(2, new Rational(4, 5));
		co1.put(0, new Rational(2, 5));
		p1 = new Polynomial(co1); 

		co2 = new HashMap<Integer, Rational>();
		co2.put(2, new Rational(-3, 4));
		co2.put(1, new Rational(6, 7));
		p2 = new Polynomial(co2); 

		co3 = new HashMap<Integer, Rational>();
		co3.put(2, new Rational(4, 5));
		p3 = new Polynomial(co3);

		co4 = new HashMap<Integer, Rational>();
		co4.put(0, new Rational(0, 2));
		p4 = new Polynomial(co4);
		
		co5 = new HashMap<Integer, Rational>();
		co5.put(6, new Rational(-4, 9));
		co5.put(2, new Rational(-4,5));
		co5.put(0, new Rational(-2,5));
		p5 = new Polynomial(co5); 
		
	}

	@Test
	public void testAdd() {
		Polynomial result = p1.add(p5);  // adds to 0
		assertEquals("0", result.toString());
		
		result = p2.add(p3);	// adds different sizes
		assertEquals("1/20x^2 + 6/7x", result.toString());
		
		result = p1.add(p2).add(p3);	// adds multiple polys, different powers
		assertEquals("4/9x^6 + 17/20x^2 + 6/7x + 2/5", result.toString());
		
		result = p2.add(p2);	// adds to self
		assertEquals("-24/16x^2 + 12/7x", result.toString());
	}

	@Test
	public void testSub() {
		Polynomial result = p1.sub(p1); // subtract itself
		assertEquals("0", result.toString());
		
		result = p1.sub(p2); // subtract different size
		assertEquals("4/9x^6 + 31/20x^2 + -6/7x + 2/5", result.toString());
		
		result = p3.sub(p4); // subtract 0
		assertEquals(p3.toString(), result.toString());
		
	}

	@Test
	public void testMultiply() {
		Polynomial result = p1.multiply(p4);	// multiply by 0
		assertEquals("0", result.toString());
		
		result = p2.multiply(p3);	//multiply different length
		assertEquals("-12/20x^4 + 24/35x^3", result.toString());
		
		result = p5.multiply(p5); 	//multiply by self
		assertEquals("16/81x^12 + 32/45x^8 + 16/45x^6 + 16/25x^4 + 16/25x^2 + 4/25", result.toString());
		
	}
	
	@Test
	public void testDegree() {
		assertEquals(6, p1.degree());
		assertEquals(2, p2.degree());
		assertEquals(2, p3.degree());
		assertEquals(0, p4.degree());	// test 0 degree
		assertEquals(6, p5.degree());
	}

}
