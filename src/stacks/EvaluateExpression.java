package stacks;

import java.util.Stack;

public class EvaluateExpression {

    boolean isAlnum(char c) {
        return c >= 48 && c <= 57;
    }

    boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    boolean isUnary(char c) {
        return c == '+' || c == '-';
    }

    boolean isLeftAssociative(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // a^b^c ==> (a^(b^c))
    boolean isRightAssociative(char c) {
        return c == '^';
    }

    int priority(int ascii_op) {
        if (ascii_op < 0)             // unary operator (-ve ascii)
            return 4;

        char op = (char)ascii_op;
        if (op == '^')
            return 3;
        if (op == '+' || op == '-')
            return 2;
        if (op == '*' || op == '/')
            return 1;
        return 0;
    }

    void processOperator(Stack<Integer> operand, int ascii_op)   {
        if (ascii_op < 0) {
            // unary operator
            // while pushing we pushed -ascii_char for validating unary
            int l = operand.peek();
            operand.pop();

            switch ((char)-ascii_op) {
                case '+': operand.push(l); break;
                case '-': operand.push(-l); break;
            }
        } else {
            int second = operand.peek();
            operand.pop();

            int first = operand.peek();
            operand.pop();

            switch ((char)ascii_op) {
                case '+': operand.push(first + second); break;
                case '-': operand.push(first - second); break;
                case '*': operand.push(first * second); break;
                case '/': operand.push(first / second); break;
                case '^': operand.push((int)Math.pow(first,second)); break;
            }
        }
    }

    int evaluate(String expression) {
        Stack<Integer> operand = new Stack<>();
        Stack<Integer> operator = new Stack<>();        // ascii_value of character's higher precedence

        boolean mayUnary = false;

        for(int i = 0 ; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            int ascii_ch = ch;

            if (ch == '(') {
                operator.push(ascii_ch);
                mayUnary = true;

            }  else if (ch == ')') {
                while (operator.peek() != '(') {
                    processOperator(operand, operator.peek());
                    operator.pop();
                }
                // '('
                operator.pop();
                mayUnary = false;

            } else if (isOperator(ch)) {
                if (mayUnary && isUnary(ch)) {
                    // (- or (+ case is encountered do ch = -ch : flag
                    ascii_ch = -ascii_ch;
                }

                // operator stack of higher precedence
                while (!operator.empty() &&
                        // non-unary operator
                        ascii_ch >= 0 && (isLeftAssociative(ch) && priority(ch) <= priority(operator.peek())  ||
                                   isRightAssociative(ch) && priority(ch) < priority(operator.peek()))  ||
                        // unary operators has right associativity
                        ascii_ch < 0 && priority(ch) < priority(operator.peek())
                ) {
                    processOperator(operand, operator.peek());
                    operator.pop();
                }

                operator.push(ascii_ch);
                mayUnary = true;

            } else {
                // operand
                int number = 0;
                while (i < expression.length() && isAlnum(expression.charAt(i))) {
                    number = number * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                // restore to (i-1) for false check as for is doing i++
                i--;
                operand.push(number);
                mayUnary = false;
            }

        }
       return operand.peek();
    }


    public static void main(String ...args){
        String expr = "(-2+(-3)+(3*-4)+(4*3/2)+(2^2^2))";
        System.out.println(new EvaluateExpression().evaluate(expr));
    }
}
