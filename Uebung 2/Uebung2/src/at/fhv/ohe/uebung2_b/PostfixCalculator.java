package at.fhv.ohe.uebung2_b;

import java.io.IOException;

/**
 * A final class for postfix calculation
 * 
 * @author      Oliver Heil - fhv.at
 * @version     1.0
 * @since   	2017-03-17
 */
public abstract class PostfixCalculator {
	
	/**
	 * calculate the result of the postfixnotation. The postfixnotation must end with a ";".
	 * 
	 * @param postfixnotation - the complete postfixnotation
	 * @return the result as integer
	 * @throws ArithmeticException 
	 */
	public static int calculate(String postfixnotation) throws ArithmeticException {
		String[] parts = postfixnotation.split(" ");
		Stack stack = new Stack(parts.length / 2);
		int index = 0;
		int firstNum;
		int seconNum;
		
		try {
			while (!parts[index].equals(";")){	
				switch (parts[index]) {
				case "+":
					stack.push(stack.pop() + stack.pop());
					break;
				
				case "-":
					firstNum = stack.pop();
					seconNum = stack.pop();
					stack.push(seconNum - firstNum);
					break;
					
				case "*":
					stack.push(stack.pop() * stack.pop());
					break;
					
				case "/":
					firstNum = stack.pop();
					if (firstNum == 0) {
						throw new ArithmeticException("Divide by Zero");
					} 
					seconNum = stack.pop();
					stack.push(seconNum / firstNum);
					break;
					
				default:
					try {
						stack.push(Integer.parseInt(parts[index]));
					} catch(Exception e) {
						throw new ArithmeticException("Unknown character/number");
					}
					
				}
				index++;
			}
			
			int result = stack.pop();
			if (!stack.isEmpty()) {
				throw new IOException("Postfixnotation wrong");
			}
			return result;
			
		} catch (ArithmeticException e) {
			throw e;
			
		} catch (IOException e) {
			throw new ArithmeticException("Postfixnotation wrong");
		}
	}
}
