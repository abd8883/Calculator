package calculator;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// Set the locale to US to ensure the decimal separator is a dot ('.') rather than a comma (',')
		scanner.useLocale(Locale.US);
		try {
			// Prompt the user to enter the first number
			double firstOperand = getDouble(scanner, "Enter a number: ");
			// Prompt the user to enter the second number
			double secondOperand = getDouble(scanner, "Enter a number: ");
			// Prompt the user to enter an operator
			char operator = getOperator(scanner, "Enter an operator (+, -, *, /, %): ");
			// Calculate the result based on the operands and the operator
			double result = calculate(firstOperand, secondOperand, operator);

			// Check if the result is NaN (which indicates a division by zero error)
			if (Double.isNaN(result)) {
				System.out.println("Division by 0 is undefined.");
				scanner.close();
				return;
			} else {
				// Print the result of the operation
				System.out.println("The result of " + firstOperand + " " + operator + " " + secondOperand + " = " + result);
			}
		} finally {
			// Close the scanner to free up resources
			scanner.close();
		}
	}

	// Method to get a double value from the user with error handling
	private static double getDouble(Scanner scanner, String prompt) {
		boolean validDouble = false;
		double number = 0;
		while (!validDouble) {
			try {
				// Prompt the user to enter a number
				System.out.println(prompt);
				number = scanner.nextDouble();
				scanner.nextLine(); // Clear the buffer
				validDouble = true; // Exit the loop if a valid double is entered
			} catch (InputMismatchException e) {
				// Handle invalid input and prompt the user again
				System.out.println("Please enter a valid double.");
				scanner.next(); // Clear the invalid input
			}
		}
		return number; // Return the valid double
	}

	// Method to get an operator from the user with error handling
	private static char getOperator(Scanner scanner, String prompt) {
		char operator = ' ';
		boolean validOperator = false;
		while (!validOperator) {
			// Prompt the user to enter an operator
			System.out.println(prompt);
			String input = scanner.nextLine().trim(); // Trim whitespace
			if (input.length() == 1) {
				operator = input.charAt(0);
				// Check if the operator is valid
				if (operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '%') {
					validOperator = true; // Exit the loop if a valid operator is entered
				} else {
					// Handle invalid operator and prompt the user again
					System.out.println("This operator is not supported.");
				}
			} else {
				// Handle input that is not a single character
				System.out.println("Please enter a single operator.");
			}
		}
		return operator; // Return the valid operator
	}

	// Method to perform the calculation based on the operator
	private static double calculate(double firstOperand, double secondOperand, char operator) {
		double result = 0;
		switch (operator) {
			case '+':
				result = firstOperand + secondOperand;
				break;
			case '*':
				result = firstOperand * secondOperand;
				break;
			case '/':
				// Handle division by zero
				if (secondOperand == 0) {
					result = Double.NaN;
				} else {
					result = firstOperand / secondOperand;
				}
				break;
			case '%':
				// Handle modulus by zero
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
		return result; // Return the result of the calculation
	}
}
