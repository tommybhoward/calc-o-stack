/** Main meathod for calculator*/
public class Calculator {
	public static void main(String[] args) {
		LinkedStack calcPostfix = new LinkedStack();
		ResizableArrayStack<Integer> calcSolution = new ResizableArrayStack<>();
		String infix = "a*b/(c-a)+d*e";
		String postfix = calcPostfix.convertToPostfix(infix);
		System.out.println("Infix: " + infix);
		System.out.println("Postfix: " + postfix);
		int solution = calcSolution.evaluatePostfix(postfix);
		System.out.println("Solution: " + solution);
	}
}
