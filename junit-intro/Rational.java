//author Zifeng Mai, Dev Sethi

//package Assignment7;
/**
 * Homework7
 * 
 * @author Zifeng Mai, Dev Sethi
 * 
 */
public class Rational {
	private int num;
	private int den;

	/**
	 * Constructor for Rational.
	 * 
	 * @param numerator
	 * @param denominator
	 */
	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new RuntimeException("denominator cannot be 0");
		} else {
			this.num = numerator;
			this.den = denominator;
		}
	}

	/**
	 * 
	 * @param other
	 * @return Add two rational rational.
	 */
	public Rational add(Rational other) {
		Rational newAdd = new Rational(this.num * other.den + other.num * this.den, this.den * other.den);
		newAdd.reduceToLowestForm();
		return newAdd;

	}

	/**
	 * 
	 * @param other
	 * @return Return one rational minus rational 'other'.
	 */
	public Rational sub(Rational other) {
		Rational newSub = new Rational(this.num * other.den - other.num	
				* this.den, this.den * other.den);
		newSub.reduceToLowestForm();
		return newSub;
	}

	/**
	 * 
	 * @param other
	 * @return Multiplication between two rational.
	 */
	public Rational mul(Rational other) {
		Rational newMul = new Rational(this.num * other.num, this.den* other.den);
		newMul.reduceToLowestForm();
		return newMul;
	}

	/**
	 * 
	 * @param other
	 * @return Squaring of the rational.
	 */
	public Rational div(Rational other) {
		Rational newDiv = new Rational(this.num * other.den, this.den* other.num);
		newDiv.reduceToLowestForm();
		return newDiv;
	}

	/**
	 * 
	 * @return Squaring of the rational.
	 */
	public Rational sqr() {
		Rational newSqr = this.mul(this);
		newSqr.reduceToLowestForm();
		return newSqr;
	}

	/**
	 * Cancel out common factors between numerator and denominator.
	 */
	public void reduceToLowestForm() {
		int maxPossibleFactor = Math.min(this.num, this.den);
		for (int i = 2; i < maxPossibleFactor + 1; i++) {
			while (this.num % i == 0 && this.den % i == 0) {
				this.num = this.num / i;
				this.den = this.den / i;
				/*
				 * Below solve the problem that result such as 4/4 or 36/9
				 * cannot return to 1 or 4 in the original Python file
				 */
				if (this.num % this.den == 0) {
					this.num = this.num / this.den;
					this.den = this.den / this.den;
				} else if (this.den % this.num == 0) {
					this.den = this.den / this.num;
					this.num = this.num / this.num;
				}
			}
		}
	}

	/**
	 * Display rational as a string.
	 */
	public String toString() {
		if (this.den != 1) {
			return this.num + "/" + this.den;
		} else {
			return this.num + "";
		}
	}

	/**
	 * 
	 * @return double conversion function.
	 */
	public double decimal() {
		double temp;
		temp = (double) this.num / (double) this.den;
		return temp;
	}

	public int getNum() {
		return this.num;
	}

	/**
	 * Create instances to calculate rational.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Rational rational1 = new Rational(1, 4);
		Rational rational2 = new Rational(1, 4);
		Rational rationalAdd = rational2.add(rational1);
		rationalAdd.reduceToLowestForm();
		System.out.println(rational1 + " added to " + rational2 + " = "
				+ rationalAdd + " and the decimal representation is "
				+ rationalAdd.decimal());

		Rational rational5 = new Rational(1, 4);
		Rational rational6 = new Rational(1, 8);
		Rational rationalSub = rational5.sub(rational6);
		rationalSub.reduceToLowestForm();
		System.out.println(rational5 + " subtracted by " + rational6 + " = "
				+ rationalSub + " and the decimal representation is "
				+ rationalSub.decimal());

		Rational rational3 = new Rational(2, 3);
		Rational rational4 = new Rational(3, 2);
		Rational rationalMul = rational4.mul(rational3);
		rationalMul.reduceToLowestForm();
		System.out.println(rational3 + " multiplied by " + rational4 + " = "
				+ rationalMul + " and the decimal representation is "
				+ rationalMul.decimal());

		Rational rational7 = new Rational(4, 8);
		Rational rational8 = new Rational(3, 4);
		Rational rationalDiv = rational8.div(rational7);
		rationalDiv.reduceToLowestForm();
		System.out.println(rational8 + " divided by " + rational7 + " = "
				+ rationalDiv + " and the decimal representation is "
				+ rationalDiv.decimal());

		Rational rational9 = new Rational(3, 2);
		Rational rationalSqr = rational9.sqr();
		rationalSqr.reduceToLowestForm();
		System.out
				.println("The square of " + rational9 + " = " + rationalSqr
						+ " and the decimal representation is "
						+ rationalSqr.decimal());

		System.out.println(new Rational(3, 8).sqr().toString());
		System.out.println(new Rational(16, 17).sqr().toString());
		System.out.println(new Rational(1, 4).sqr().toString());

		System.out.println(new Rational(3, 2).decimal());

		Rational rational10 = new Rational(2, 3);
		Rational rational11 = new Rational(-1, 1);
		Rational rationalMul2 = rational11.mul(rational10);
		rationalMul.reduceToLowestForm();
		System.out.println(rational10 + " multiplied by " + rational11 + " = "
				+ rationalMul2 + " and the decimal representation is "
				+ rationalMul2.decimal());

	}
}
