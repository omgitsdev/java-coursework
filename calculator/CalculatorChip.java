//Dev Sethi and Ce Wang
//CIT 590 

package calculator;

public class CalculatorChip {
	Double first, second, alternate, memAlternative;
	boolean decFlag, zeroDiv;
	boolean firstDone;
	boolean digitFlag;
	boolean clearFlag;
	int zeroCount;
	int decCount;
	String operator;
	Double memoryFirst;
	String zeroReturn;

	/**
	 * Constructor, initiates all data and flags to allClear state
	 */
	public CalculatorChip() {
		operator = "";
		clearAll();
	}

	/**
	 * Resets all variables except first and firstDone
	 * @return 0
	 */
	public String clear() {
		second = 0.;
		decFlag = false;
		zeroDiv = false;
		decCount = 1;
		digitFlag = false;
		clearFlag = false;
		zeroCount = 0;
		return "0";
	}

	/**
	 * Resets all variables
	 * @return 0
	 */
	public String clearAll() {
		first = 0.;
		firstDone = false;
		return clear();
	}

	/**
	 * Clears Memory
	 * @return 0
	 */
	public String memClear() {
		memoryFirst = 0.;
		second = 0.;
		decCount = 1;
		digitFlag = false;
		return "0";
	}

	/**
	 * Recalls memory and stores in second
	 * @return contents of memory
	 */
	public String memRead() {
		clearFlag = true;
		return Double.toString(memoryFirst);
	}

	/**
	 * Adds to memory
	 * @return contents of memory
	 */
	public String memPlus() {
		if (memAlternative != 0) {
			memoryFirst += memAlternative;

		} else {
			memoryFirst += second;
		}
		decCount = 1;
		memAlternative = 0.;
		second = 0.;
		return Double.toString(memoryFirst);
	}

	/**
	 * Subtracts from memory
	 * @return contents of memory
	 */
	public String memMinus() {
		if (memAlternative != 0) {
			memoryFirst -= memAlternative;
		} else {
			memoryFirst -= second;
		}
		decCount = 1;
		memAlternative = 0.;
		second = 0.;
		return Double.toString(memoryFirst);
	}

	/**
	 * Builds number
	 * @param digit
	 * @return current state of number
	 */
	public String digit(int digit) {
		digitFlag = true;
		clearFlag = true;
		memAlternative = 0.;
		if (!decFlag) {
			second = second * 10 + digit;
		} else {
			if (digit == 0) {
				zeroCount++;
				decCount++;
				zeroReturn = Double.toString(second);
				for (int i = 0; i < zeroCount; i++) {
					zeroReturn += "0";
				}
				return zeroReturn;
			} else {
				zeroCount = 0;
				if (second >= 0) {
					second = second + ((digit * 1.0) / Math.pow(10, decCount));
				} else {
					second = (Math.abs(second) + ((digit * 1.0) / Math.pow(10,
							decCount))) * -1;
				}
			}
			decCount++;
		}

		return Double.toString(second);
	}

	/**
	 * Appends decimal point
	 * @return original number with decimal point, sets flags for decmial point
	 */
	public String decimalPoint() {
		memAlternative = 0.;
		clearFlag = true;
		decFlag = true;
		return Double.toString(second);
	}

	/**
	 * Performs add function and preps for interaction with equals()
	 * @return value of second (if first press) or first (if in sequence)
	 */
	public String add() {
		memAlternative = second;
		digitFlag = false;
		if (firstDone != true) {
			first = second;
		} else {
			first = switchOp();
		}
		firstDone = true;
		second = 0.;
		decCount = 1;
		operator = "+";
		alternate = first;
		if (zeroDiv == false) {
			return Double.toString(first);
		} else {
			first = 0.;
			zeroDiv = true;
			return "Error";
		}

	}

	/**
	 * Performs subtract function and preps for interaction with equals()
	 * @return value of second (if first press) or first (if in sequence)
	 */
	public String subtract() {
		memAlternative = second;
		digitFlag = false;
		if (firstDone != true) {
			first = second;
		} else if (operator == "=") {

		} else {
			first = switchOp();
		}
		firstDone = true;
		second = 0.;
		alternate = first;
		decCount = 1;
		operator = "-";
		if (zeroDiv == false) {
			return Double.toString(first);
		} else {
			first = 0.;
			zeroDiv = true;
			return "Error";
		}
	}

	/**
	 * Performs multiply function and preps for interaction with equals()
	 * @return value of second (if first press) or first (if in sequence)
	 */
	public String multiply() {
		memAlternative = second;
		digitFlag = false;
		if (firstDone != true) {
			first = second;
		} else if (operator == "=") {

		} else {
			first = switchOp();
		}
		second = 0.;
		alternate = first;
		decCount = 1;
		operator = "x";
		firstDone = true;
		if (zeroDiv == false) {
			return Double.toString(first);
		} else {
			first = 0.;
			zeroDiv = true;
			return "Error";
		}
	}

	/**
	 * Performs divide function and preps for interaction with equals()
	 * @return value of second (if first press) or first (if in sequence)
	 */
	public String divide() {
		memAlternative = second;
		digitFlag = false;
		if (firstDone != true) {
			first = second;
		} else if (operator == "=") {

		} else {
			first = switchOp();
		}
		firstDone = true;
		second = 0.;
		alternate = first;
		decCount = 1;
		operator = "/";
		if (zeroDiv == false) {
			return Double.toString(first);
		} else {
			first = 0.;
			zeroDiv = true;
			return "Error";
		}
	}

	/**
	 * Calculates result of previously entered operators or duplicates operation if pressed in sequence
	 * @return result of operations
	 */
	public String equals() {

		String result = "";

		if (digitFlag) {
			switch (operator) {
			case "":
				result = "0";
				break;
			case "+":
				first = first + second;
				result = Double.toString(first);
				break;
			case "-":
				first = first - second;
				result = Double.toString(first);
				break;
			case "x":
				first = first * second;
				result = Double.toString(first);
				break;
			case "/":
				if (second == 0) {
					result = "Error";
				} else {
					first = first / second;
					result = Double.toString(first);
				}
				break;

			}
		} else {
			switch (operator) {
			case "":
				result = "0";
				break;
			case "+":
				first = first + alternate;
				result = Double.toString(first);
				break;
			case "-":
				first = first - alternate;
				result = Double.toString(first);
				break;
			case "x":
				first = first * alternate;
				result = Double.toString(first);
				break;
			case "/":
				if (alternate == 0) {
					result = "Error";
				} else {
					first = first / alternate;
					result = Double.toString(first);
				}
				break;
			}
		}
		second = 0.;
		decCount = 1;
		digitFlag = false;
		memAlternative = first;
		return result;
	}

	/**
	 * Performs operations based on the state of 'operator'
	 * @return result of operation
	 */
	public double switchOp() {
		Double result = 0.;
		switch (operator) {
		case "":
			result = 0.0;
			break;
		case "+":
			result = first + second;
			break;
		case "-":
			result = first - second;
			break;
		case "x":
			result = first * second;
			break;
		case "/":
			if (second == 0) {
				zeroDiv = true;
			} else {
				result = first / second;
			}
			break;
		}
		return result;
	}

	/**
	 * Checks for legality, calculates square root
	 * @return square root of number or error
	 */
	public String sqrt() {
		clearFlag = true;
		if (second > 0) {
			second = Math.sqrt(second);
			return Double.toString(second);
			 } else if (first > 0) {
			 first = Math.sqrt(first);
			 return Double.toString(first);
		} else if (second == 0.){
			return Double.toString(second);
		}else{
			return "Error";
		}
	}

	/**
	 * Calculates percent
	 * @return percent of number
	 */
	public String percent() {
		clearFlag = true;
		if (second != 0. && first != 0.) {
			second = second * 0.01;
			first = switchOp();
			return Double.toString(first);
		} else if (second != 0.) {
			second = second * 0.01;
			return Double.toString(second);
		} else if (first != 0.) {
			first = first * 0.01;
			return Double.toString(first);
		} else {
			return Double.toString(second);
		}

	}

	/**
	 * Checks for legality, calculates inverse of number
	 * @return inverse of number or error
	 */
	public String invert() {
		clearFlag = true;
		if (second != 0.) {
			second = 1 / second;
			return Double.toString(second);
		} else if (first != 0.) {
			first = 1 / first;
			return Double.toString(first);
		} else {
			return "Error";
		}

	}

	/**
	 * Changes sign of number
	 * @return number * -1
	 */
	public String changeSign() {
		clearFlag = true;
		if (second != 0.) {
			second = 0 - second;
			return Double.toString(second);
		} else {
			first = 0 - first;
			return Double.toString(first);
		}

	}

	/**
	 * @return string of number
	 */
	public String getText() {
		return second.toString();
	}

}
