package infix.postfix;

import java.util.Stack;

public class InfixToPostfix {
    public String infixToPostfix(String infixString){
        String postfix = "";
        // Step 1
        Stack<Character> stack = new Stack<>();
        stack.push('(');
        infixString = infixString.concat("" +')');
        // Step 2
        for (int i = 0; i < infixString.length(); i++) {
            char charactor = infixString.charAt(i);
            // Step 3
            if (Character.isAlphabetic(charactor)) {
                postfix = postfix.concat("" + charactor);
            }
            // Step 4
            else if (charactor == '(') {
                stack.push(charactor);
            }
            // Step 5
            else if (isOperator(charactor)) {
                if (stack.peek() != '(') {
                    while (!stack.empty()) {
                        if (precedence(stack.peek()) >=
                                precedence(charactor)) {
                            postfix = postfix.concat("" + stack.pop());
                        }
                        else {
                            stack.push(charactor);
                            break;
                        }
                    }

                }
                // step 6
                else if (charactor == ')'){
                    while (!stack.empty()){
                        if ((stack.peek() != '(')){
                            postfix = postfix.concat("" + stack.pop());
                        }
                        else {
                            stack.pop();
                            break;
                        }
                    }
                }
            }

        }
        return postfix;
    }
    private boolean isOperator(char character){
        boolean response = false;
        switch (character){
            case '^':
            case '/':
            case '*':
            case '+':
            case '-':
                response = true;
        }

        return response;
    }
    private int precedence(char operator){
        int response = 0;
        switch (operator) {
            case '^':
                response = 3;
                break;
            case '/':
            case '*':
                response = 2;
                break;
            case '+':
            case '-':
                response = 1;
        }

        return response;
    }
}
