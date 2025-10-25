
public class LinkedStack implements StackInterface<Character> {
	private class Node {
		char data;
		Node next;

		Node(char data) {
			this.data = data;
			this.next = null;
		}
	}

	private Node top; // top of stack

	public LinkedStack() {
		top = null;
	}

	/** StackInterface methods */
	@Override
	public void push(Character item) {
		Node node = new Node(item);
		node.next = top;
		top = node;
	}

	@Override
	public Character pop() {
		if (isEmpty()) throw new RuntimeException("Stack underflow");
		char value = top.data;
		top = top.next;
		return value;
	}

	@Override
	public Character peek() {
		if (isEmpty()) throw new RuntimeException("Stack is empty");
		return top.data;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}


	public String convertToPostfix(String infix) {
		LinkedStack operatorStack = new LinkedStack();
		String postfix = "";
		char topOperator = ' ';
		int n = infix.length();
		for (int i=0; i<n; i++)
		{
			char nextChar = infix.charAt(i);
			if (Character.isLetterOrDigit(nextChar)) {
				/**Append next char to postfix*/
				postfix += nextChar;
			} else {
				switch (nextChar)
				{
				case '^' :
					operatorStack.push(nextChar);
					break;
				case '+' : case '-' : case '*' : case '/' : 
					while (!operatorStack.isEmpty() && precedence(nextChar) <= precedence(operatorStack.peek())) {
						postfix += operatorStack.pop();
					}
					operatorStack.push(nextChar);
					break;
				case '(' :
					operatorStack.push(nextChar);
					break; 
				case ')' : // Stack is not empty if infix expression is valid 
					while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
						postfix += operatorStack.pop();
					}
					if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
						operatorStack.pop();
					}
					break;
				default: break; /** Ignore unexpected characters*/
				}
			}
		}
		while (!operatorStack.isEmpty())
		{
			topOperator = operatorStack.pop();
			postfix += topOperator;
		}
		return postfix;
	}
	private static int precedence(char op) {
		switch (op)
		{
		case '^' :
			return 3;
		case '+' :
		case '-' :
			return 1;
		case '*' :
		case '/' :
			return 2;
		case '(' :
		case ')' :
			return 0;
		default: return -1;/** Ignore unexpected characters*/
		}
	}
}
