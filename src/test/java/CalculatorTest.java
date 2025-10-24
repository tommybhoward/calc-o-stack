import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    
    @Test
    public void testCalculatorMainCompleteOutput() {
        Calculator.main(new String[]{});
        String output = outContent.toString();
        
        // Verify all three lines are present
        assertTrue("Should output infix", output.contains("Infix:"));
        assertTrue("Should output postfix", output.contains("Postfix:"));
        assertTrue("Should output solution", output.contains("Solution:"));
        
        // Verify correct values
        assertTrue("Postfix should be ab*ca-/de*+", output.contains("ab*ca-/de*+"));
        assertTrue("Solution should be 33", output.contains("33"));
    }
    
    
    
    
    @Test
    public void testConvertToPostfixSimple() {
        LinkedStack stack = new LinkedStack();
        String infix = "a+b";
        String postfix = stack.convertToPostfix(infix);
        assertEquals("Simple addition", "ab+", postfix);
    }
    
    @Test
    public void testConvertToPostfixWithPrecedence() {
        LinkedStack stack = new LinkedStack();
        String infix = "a+b*c";
        String postfix = stack.convertToPostfix(infix);
        assertEquals("Multiplication has higher precedence", "abc*+", postfix);
    }
    
    @Test
    public void testConvertToPostfixComplex() {
        LinkedStack stack = new LinkedStack();
        String infix = "a*b/(c-a)+d*e";
        String postfix = stack.convertToPostfix(infix);
        assertEquals("Complex expression", "ab*ca-/de*+", postfix);
    }
    
    
    
    @Test
    public void testEvaluatePostfixSimple() {
        ResizableArrayStack<Integer> stack = new ResizableArrayStack<>();
        String postfix = "ab+"; // 2 + 3 = 5
        int result = stack.evaluatePostfix(postfix);
        assertEquals("Simple addition evaluation", 5, result);
    }
    
    @Test
    public void testEvaluatePostfixComplex() {
        ResizableArrayStack<Integer> stack = new ResizableArrayStack<>();
        String postfix = "ab*ca-/de*+"; // (2*3)/(4-2) + (5*6) = 3 + 30 = 33
        int result = stack.evaluatePostfix(postfix);
        assertEquals("Complex expression evaluation", 33, result);
    }
}