package calculator;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		to make sure that decimal is a dot not a comma or other sign.
		scanner.useLocale(Locale.US);
		try {
			double firstOperand = getdouble(scanner, "Enter a number: ");
			double secondOperand = getdouble(scanner, "Enter a number: ");
			char operater = getOperator(scanner, "Enter an operator (+, -, *, /, %): ");
			double result = calculator(firstOperand, secondOperand, operater);
			if (Double.isNaN(result)) {
				System.out.println("Division by 0 is undefined.");
				scanner.close();
				return;
			} else {
				System.out.println(
						"The result of " + firstOperand + " " + operater + " " + secondOperand + " = " + result);
			}

		} finally {
			scanner.close();
		}
	}

	private static double getdouble(Scanner scanner, String prompt) {
		boolean validDouble = false;
		double number = 0;
		while (!validDouble) {
			try {
				System.out.println(prompt);
				number = scanner.nextDouble();
				scanner.nextLine();// Clear the invalid input
				validDouble = true;
			}

			catch (InputMismatchException e) {
				System.out.println("Please enter a valid double.");
				scanner.next(); // Clear the invalid input
			}

		}
		return number;
	}

	private static char getOperator(Scanner scanner, String prompt) {
		char operator = ' ';
		boolean validOperator = false;
		while (!validOperator) {
			System.out.println(prompt);
			String input = scanner.nextLine();
			input = input.trim();
			if (input.length() == 1) {
				operator = input.charAt(0);
				if (operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '/'
						|| operator == '%') {
					validOperator = true;
				} else {
					System.out.println("This operator is not supported.");
				}
			} else {
				System.out.println("Please enter a single operator.");
			}
		}
		return operator;
	}

	private static double calculator(double firstOperand, double secondOperand, char operator) {
		double result = 0;
		switch (operator) {
		case '+':
			result = firstOperand + secondOperand;
			break;
		case '*':
			result = firstOperand * secondOperand;
			break;
		case '/':
			if (secondOperand == 0) {
				result = Double.NaN;
			} else {
				result = firstOperand / secondOperand;
			}
			break;

		case '%':
			if (secondOperand == 0) {
				result = Double.NaN;
			} else {
				result = firstOperand % secondOperand;
			}
			break;
		case '-':
			result = firstOperand - secondOperand;
			break;
		}

		return result;

	}
}
