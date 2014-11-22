
public class Rational {
	
	int num;
	int den;

	
	public Rational(int numerator, int denominator){
		if (denominator == 0){
			throw new RuntimeException("Denominator cannot be 0");
		}
		else {
		num = numerator;
		den = denominator;
		}
	}

	/**
	 * Returns fraction in string form
	 */
	public String toString(){
		return this.num + "/" + this.den;
	}
	
	/**
	 * Displays Rational fraction
	 * @param x use println when false, print when true
	 */
	void display(boolean x){
		if (x){
			System.out.print(this.num);
			if (den!=1) {
				System.out.print("/" + this.den);
				}
			}
		
		else{
			if (den!=1){
				System.out.println(this.num + "/" + this.den);
			}
			else{
				System.out.println(this.num);	
			}
			}
	}
	
	/**
	 * 
	 * @param other
	 * @return sum
	 */
	Rational add(Rational other) {
		return new Rational(this.num * other.den + other.num * this.den, this.den * other.den);
	}
	
	/**
	 * 
	 * @param other
	 * @return difference
	 */
	Rational sub(Rational other) {
		return new Rational(this.num * other.den - other.num * this.den, this.den * other.den);
	}
	
	/**
	 * 
	 * @param other
	 * @return product
	 */
	Rational mul(Rational other){
		return new Rational(this.num * other.num, this.den * other.den);
	}
	
	/**
	 * 
	 * @param other
	 * @return quotient
	 */
	Rational div(Rational other){
		return new Rational (this.num * other.den, this.den * other.num);
	}
	
	/**
	 * 
	 * @return square
	 */
	Rational sqr(){
		return this.mul(this);
	}
	
	/**
	 * Reduces fraction of Rational instance
	 */
	void reduceToLowestForm(){
		int maxPossibleFactor = Math.min(this.num, this.den);
		for (int i = 2; i < (maxPossibleFactor + 1); i++){
			while ((this.num % i == 0) && (this.den % i ==0)){
				this.num = this.num/i;
				this.den = this.den/i;
			}
		}
	}
		
	public static void main(String[] args) {
		Rational r, r1, r2, r3, r4, r5;
		r = new Rational(1,4);
		r1 = new Rational(1,4);
		r2 = r.add(r1);
		r2.reduceToLowestForm();
		r1.display(true);
		System.out.print(" added to ");
		r.display(true);
		System.out.print(" = ");
		r2.display(false);
		r3 = new Rational(2,3);
		r4 = new Rational(3,2);
		r5 = r3.mul(r4);
		r5.reduceToLowestForm();
		r5.display(false);
	}
}