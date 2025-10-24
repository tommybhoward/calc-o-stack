
public class ResizableArrayStack<T> implements StackInterface<T> {
	private T[] array;
	private int topIndex;    // index of the top element
	private static final int INITIAL_CAPACITY = 10;

	@SuppressWarnings("unchecked")
	public ResizableArrayStack() {
		array = (T[]) new Object[INITIAL_CAPACITY];
		topIndex = -1;
	}

	// Push item onto the stack
	@Override
	public void push(T item) {
		if (topIndex + 1 == array.length) {
			resize(array.length * 2);
		}
		array[++topIndex] = item;
	}

	// Pop item from the stack
	@Override
	public T pop() {
		T item = array[topIndex];
		array[topIndex] = null;
		topIndex--;
		return item;
	}

	// Peek at top item
	@Override
	public T peek() {
		if (isEmpty()) throw new RuntimeException("Empty Stack");
		return array[topIndex];
	}

	// Check if stack is empty
	@Override
	public boolean isEmpty() {
		return topIndex == -1;
	}

	// Resize the internal array
	@SuppressWarnings("unchecked")
	private void resize(int newCapacity) {
		T[] newArray = (T[]) new Object[newCapacity];
		System.arraycopy(array, 0, newArray, 0, topIndex + 1);
		array = newArray;
	}



	public int evaluatePostfix(String postfix) {
		int a = 2;
		int b = 3;
		int c = 4;
		int d = 5;
		int e = 6;
		int operandTwo = -1;
		int operandOne = -1;
		int result;
		// Evaluates a postfix expression. 
		ResizableArrayStack<Integer> valueStack = new ResizableArrayStack<>();
		int n = postfix.length();
		for (int i=0; i<n; i++)
		{
			char nextChar = postfix.charAt(i);
			switch (nextChar)
			{
			case 'a':
				valueStack.push(a);
				break;
			case 'b':
				valueStack.push(b);
				break;
			case 'c':
				valueStack.push(c);
				break;
			case 'd':
				valueStack.push(d);
				break;
			case 'e':
				valueStack.push(e);
				break;
			case '+' :
				operandTwo = valueStack.pop();
				operandOne = valueStack.pop();
				result = operandOne + operandTwo;
				valueStack.push(result);
				break;
			case '-' :
				operandTwo = valueStack.pop();
				operandOne = valueStack.pop();
				result = operandOne - operandTwo;
				valueStack.push(result);
				break;
			case '*' :
				operandTwo = valueStack.pop();
				operandOne = valueStack.pop();
				result = operandOne * operandTwo;
				valueStack.push(result);
				break;
			case '/' :
				operandTwo = valueStack.pop();
				operandOne = valueStack.pop();
				result = operandOne / operandTwo;
				valueStack.push(result);
				break;
			case '^' :
				operandTwo = valueStack.pop();
				operandOne = valueStack.pop();
				result = (int) Math.pow(operandOne, operandTwo);
				valueStack.push(result);
				break;
			default: break; // Ignore unexpected characters 
			}
		}
		return valueStack.peek();
	}
}
