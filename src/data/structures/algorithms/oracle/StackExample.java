package data.structures.algorithms.oracle;

public class StackExample {
    private int maxSize;
    private int top;
    private int[] stackArray;

    // Constructor to initialize the stack
    public StackExample(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1; // Stack is empty initially
    }

    // Method to push an element to the stack
    public void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value;
            System.out.println(value + " pushed to stack.");
        } else {
            System.out.println("Stack is full. Cannot push " + value);
        }
    }

    // Method to pop an element from the stack
    public int pop() {
        if (top >= 0) {
            int poppedValue = stackArray[top--];
            return poppedValue;
        } else {
            System.out.println("Stack is empty.");
            return -1; // Return -1 if stack is empty
        }
    }

    // Method to peek the top element of the stack
    public int peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            System.out.println("Stack is empty.");
            return -1;
        }
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Method to check if the stack is full
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // Method to get the size of the stack
    public int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        StackExample stack = new StackExample(5); // Creating a stack of size 5

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        System.out.println("Top element is " + stack.peek());

        System.out.println("Popped element is " + stack.pop());

        System.out.println("Top element is " + stack.peek());
        System.out.println("Size of stack: " + stack.size());

        System.out.println("Is the stack empty? " + stack.isEmpty());
        System.out.println("Is the stack full? " + stack.isFull());
    }
}

