package data.structures.algorithms.oracle;

import java.util.Stack;

public class ImplementQueueUsingStack {
    Stack<Integer> inStack = new Stack<>();
    Stack<Integer> outStack = new Stack<>();

    public void push(Integer value) {
        System.out.println(value + " ");
        inStack.push(value);
    }

    public int poll() {
        moveFromInStackToOutStack();
        return (!outStack.isEmpty()) ? outStack.pop() : -1;
    }

    public boolean isEmpty() {
        return outStack.isEmpty() && inStack.isEmpty();
    }

    public int peek() {
        moveFromInStackToOutStack();
        return (!outStack.isEmpty()) ? outStack.peek() : -1;
    }

    private void moveFromInStackToOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public static void main(String[] args) {
        ImplementQueueUsingStack queue = new ImplementQueueUsingStack();
        for (int i = 0; i < 10; i++) {
            queue.push(i);
        }
        System.out.println("First Element : " + queue.peek());
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
    }

}
