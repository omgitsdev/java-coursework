//author Zifeng Mai, Dev Sethi

//package cit590;

import java.util.*;

/**
 * 
 * Rational polynomials class
 */
public class Polynomial {

	// 2/3 x^2 + 4/5 x^1 + 1 is represented as {2: Rational(2,3), 1:
	// Rational(4,5), 0: Rational(1,1)}
	// The key (the integer part) is the power of x
	HashMap<Integer, Rational> coefficients;

	/**
	 * Create a new polynomial with a HashMap of coefficients
	 * 
	 * @param coefficient
	 */
	public Polynomial(HashMap<Integer, Rational> coefficients) {
		this.coefficients = coefficients;
	}

	/**
	 * Initialize the polynomial with a list of coefficients in order - constant
	 * term, linear term, square term, etc etc Assumption is the first term in
	 * the list is for the const, second for the linear term third for x^2 and
	 * so on
	 * 
	 * @param coeffs
	 */
	/**
	 * public Polynomial(ArrayList<Rational> coeffs) { coefficients = new
	 * HashMap<Integer, Rational>(); for (int i = 0; i < coeffs.size(); i++) {
	 * coefficients.put(i, coeffs.get(i)); } }
	 */
	/**
	 * main function to be filled in at the end
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// see specs of the assignment
		// you have more freedom about what you do here

		int degree;
		HashMap<Integer, Rational> emptyMap = new HashMap<Integer, Rational>();
		Polynomial poly1 = new Polynomial(emptyMap);
		Polynomial poly2 = new Polynomial(emptyMap);
		String select;
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;

		System.out.print("Enter degree of first polynomial: ");
		degree = scanner.nextInt();
		poly1 = poly1.userInput(degree);
		System.out.print("Enter degree of second polynomial: ");
		degree = scanner.nextInt();
		poly2 = poly2.userInput(degree);
		
		while (flag) {
			System.out.println("Polynomial 1: " + poly1);
			System.out.println("Polynomial 2: " + poly2);
			System.out.println("Select an action: (a)dd, (s)ubtract, (m)ultiply or (q)uit");
			select = scanner.next();
			switch (select) {
			case ("a"):
				System.out.println("Result: " + poly1.add(poly2));
				break;
			case ("s"):
				System.out.println("Result: " + poly1.sub(poly2));
				break;
			case ("m"):
				System.out.println("Result: " + poly1.multiply(poly2));
				break;
			case ("q"):
				flag = false;
				break;
			default:
				System.out.println("Invalid selection.");
			}
		}
		//scanner.close();
	}
	
	/**
	 * Reduces coefficient to 0 if numerator is 0
	 */
	public void consolidate() {
		int degree = this.degree();
		for (int i = degree; i > -1; i--) {
			if (this.coefficients.containsKey(i)) {
				if (this.coefficients.get(i).getNum() == 0) {
					this.coefficients.remove(i);
				}
			}
		}
	}

	/**
	 * get the coefficient of x^power
	 * 
	 * @param power
	 * @return coefficient of x^power
	 */
	public Rational getCoefficient(int power) {
		// this.coefficients<power>;
		return this.coefficients.get(power);
	}

	/**
	 * add the secondPolynomial to the current polynomial and return a new
	 * Polynomial
	 * 
	 * @param secondPolynomial
	 * @return sum of polynomials
	 */
	public Polynomial add(Polynomial secondPolynomial) {
		int largerDegree = Math.max(this.degree(), secondPolynomial.degree());
		HashMap<Integer, Rational> x = new HashMap<Integer, Rational>();

		for (int i = 0; i < largerDegree + 1; i++) {
			if ((this.coefficients.containsKey(i) && secondPolynomial.coefficients.containsKey(i))) {
				x.put(i, this.coefficients.get(i).add(secondPolynomial.coefficients.get(i)));
			} else if (this.coefficients.containsKey(i)){
				x.put(i, this.coefficients.get(i));
			} else if (secondPolynomial.coefficients.containsKey(i)){
				x.put(i, secondPolynomial.coefficients.get(i));
			}
		}
		return new Polynomial(x);
	}

	/**
	 * subtract one polynomial from the other
	 * 
	 * @param secondPolynomial
	 * @return difference of polynomials
	 */
	public Polynomial sub(Polynomial secondPolynomial) {
		// write this method by calling add
		// reuse code!
		int degree = secondPolynomial.degree();
		HashMap<Integer, Rational> x = new HashMap<Integer, Rational>();

		for (int i = degree; i > -1; i--) {
			if (secondPolynomial.coefficients.containsKey(i)) {
				x.put(i,secondPolynomial.coefficients.get(i).mul(new Rational(-1, 1)));
			}
		}

		return this.add(new Polynomial(x));
	}

	/**
	 * multiply two polynomials the current polynomial (this) and the
	 * secondPolynomial as shown in lab/
	 * 
	 * @param secondPolynomial
	 * @return product of polynomials
	 */
	public Polynomial multiply(Polynomial secondPolynomial) {
		HashMap<Integer, Rational> x = new HashMap<Integer, Rational>();

		for (int i = 0; i < this.degree() + 1; i++) {
			for (int j = 0; j < secondPolynomial.degree() + 1; j++) {
				if (this.coefficients.containsKey(i)
						&& secondPolynomial.coefficients.containsKey(j)) {
					if (x.containsKey(i + j)) {
						x.put(i + j,
								x.get(i + j).add(
										this.getCoefficient(i).mul(
												secondPolynomial
														.getCoefficient(j))));
					} else {
						x.put(i + j,
								this.getCoefficient(i).mul(
										secondPolynomial.getCoefficient(j)));
					}
				}
			}
		}
		return new Polynomial(x);

	}

	/**
	 * return the degree of polynomial the degree of the polynomial is defined
	 * as the maximum power that has a non-zero coefficient.
	 * 
	 * @return degree
	 */
	public int degree() {
		Set<Integer> x = this.coefficients.keySet();
		int max = 0;
		for (Integer i : x) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public HashMap<Integer, Rational> getCoefficients() {
		return coefficients;
	}

	/**
	 * Use this function to do the splitting of the user input
	 * 
	 * @param s
	 * @return input string in form of Rational class
	 */
	public Rational splitAboutSlash(String s) {
		String[] numDen = s.split("/");
		int numerator = Integer.parseInt(numDen[0]);
		int denominator = Integer.parseInt(numDen[1]);
		return new Rational(numerator, denominator);
	}

	/**
	 * Allows user to create polynomials of given degree
	 * @param degree
	 * @return Polynomial
	 */
	public Polynomial userInput(int degree) {
		int power = 0;
		String iCoeff;
		HashMap<Integer, Rational> coeffMap = new HashMap<Integer, Rational>();
		Scanner scanner = new Scanner(System.in);

		while (power != -1) {
			System.out
					.print("Please enter the power of the term for which you want to assign a coefficient (input -1 when finished): ");
			power = scanner.nextInt();
			if ((power > -1) && (power <= degree)) {
				System.out.print("Please enter coefficient associated with x^"
						+ power + " in format (x/y): ");
				iCoeff = scanner.next();
				coeffMap.put(power, splitAboutSlash(iCoeff));

			} else if (power == -1) {
				break;

			} else {
				System.out
						.println("Invalid power. Must be integer between 0 and "
								+ degree + ". Please try again");
			}
		}
		//scanner.close();
		return new Polynomial(coeffMap);
	}

	/**
	 * allows for conversion of Polynomial class to text
	 * @returns string of polynomial in the form [...] ax^2 + bx + c
	 */
	public String toString() {
		this.consolidate();
		int degree = this.degree();
		String output = "";
		for (int i = degree; i > -1; i--) {

			if (this.coefficients.containsKey(i)) {
				if (i < degree) {
					output = output + " + ";
				}
				if (i == 0) {
					output = output + this.coefficients.get(i);
				} else if (i == 1) {
					output = output + this.coefficients.get(i) + "x";
				} else {
					output = output + this.coefficients.get(i) + "x^" + i;
				}
			}
		}
		if ((output == "") || (output == " ")){
			return "0";
		}else{
			return output;
		}
	}
}
