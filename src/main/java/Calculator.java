
public class Calculator {
	public static void main(String[] args) {
		LinkedStack calc = new LinkedStack();
		String infix = "a*b/(c-a)+d*e";
		String postfix = calc.convertToPostfix(infix);
		System.out.printf("Infix: " + infix + "\n");
		System.out.println("Postfix: " + postfix);
	}
}
