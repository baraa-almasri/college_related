import stack.*;
import java.lang.Math;

public class ConverterEvaluatorDemo {
    public static void main(String []argv) {

        // converter demo
        System.out.printf("enter string in infix notation: ");
        // examples to try :)
        // ( ( 1 + ( 2.9 * 8 ) ) / ( 2 - 3 ) )
        // ( 12 + ( 69 * 4 ) )
        // ( ( 20 + 16 ) * ( 420 + 33 ) )
        //  ( 24 * ( 1 * ( ( ( 4 + 5 ) + 5 ) * 66 ) ) )
        // ( ( 33 * ( ( ( ( 12 + ( ( 1 + 12 ) * 4 ) ) * 1 ) * 3 ) * 12 ) ) + 4 )
        // ( ( 2 + 1 ) / 3 ) + 1 / 2

        //System.out.printf("postfix: %s\n", convertInfix2Postfix("A + B"));
        System.out.printf("postfix: %s\n", convertInfix2Postfix("( 12 + ( 69 * 4 ) )"));

        // evaluator demo
        System.out.println(evaluatePostfix("33 12 1 12 + 4 * + 1 * 3 * 12 * * 4 +"));
    }

    public static double evaluatePrefix(String prefixExpression) {

        ArrayStack<Double> numbers = new ArrayStack<>(prefixExpression.length()/2);

        for (String entry :
            getEntriesFromExpressionString(prefixExpression)) {

            if (isNumber(entry)) {
                numbers.push( Double.parseDouble(entry));
            }
            if (isOperator(entry)) {
                final double firstOperand = numbers.top();
                numbers.pop();
                final double secondOperand = numbers.top();
                numbers.pop();

                numbers.push(
                    execOperator(firstOperand, secondOperand, entry.charAt(0))
                );

            }
        }

        // the remaining number is the answer blyat
        return numbers.top();
    }

    public static double evaluatePostfix(String postfixExpression) {

        ArrayStack<Double> numbers = new ArrayStack<>(postfixExpression.length()/2);

        for (String entry :
             getEntriesFromExpressionString(postfixExpression)) {

            if (isNumber(entry)) {
                numbers.push( Double.parseDouble(entry));
            }
            if (isOperator(entry)) {
                final double secondOperand = numbers.top();
                numbers.pop();
                final double firstOperand = numbers.top();
                numbers.pop();

                numbers.push(
                    execOperator(firstOperand, secondOperand, entry.charAt(0))
                );

            }
        }

        // the remaining number is the answer blyat
        return numbers.top();
    }

    // keep scrolling blyat
    private static String []getEntriesFromExpressionString(String expression) {
        // replace additional whitespaces with single whitespace
        expression = expression.replaceAll("\\s+", " ");
        // add entries to an array to process them one by one

        return expression.split("\\s");
    }

    // needs checking :)
    // if you're really interested, go to:
    // https://github.com/baraa-almasri/math_related/blob/master/ExpressionToolbox/ExpressionConverter.kt
    public static String convertInfix2Postfix(String expression) {

        // add braces to the expression
        String []entries =
            getEntriesFromExpressionString(String.format("( %s )", expression));
        // operators stack
        ArrayStack<Character> operators = new ArrayStack<>(entries.length/2);
        // hmm
        String postfixExpression = "";

        // process entries
        for(String entry : entries) {
            // alphabet if you're crazy :)
            if (isNumber(entry) || Character.isAlphabetic(entry.charAt(0)) ) {
                postfixExpression += String.format(" %s", entry);

            } else if (isParenth(entry)) {
                if (entry.equals("(")) {
                    operators.push(entry.charAt(0));

                } else if (entry.equals(")")) {

                    while ( !operators.top().equals('(') && !operators.isEmpty()) {
                        if( !operators.top().equals(')') || !operators.top().equals(')') ) {
                            postfixExpression += String.format(" %s",operators.top());
                        }
                        operators.pop();
                    }
                    if (operators.top().equals('(')) {
                        operators.pop();
                    }

                }

            } else if (isOperator(entry)) {
                if (getOperatorPrecedence(entry.charAt(0)) >
                    getOperatorPrecedence(operators.top())) {

                    operators.push(entry.charAt(0));

                } else if (getOperatorPrecedence(entry.charAt(0)) <
                    getOperatorPrecedence(operators.top())) {

                    postfixExpression += String.format(" %s", operators.top());
                    operators.pop();
                    operators.push(entry.charAt(0));

                } else if (getOperatorPrecedence(entry.charAt(0)) ==
                    getOperatorPrecedence(operators.top())) {
                    postfixExpression += String.format(" %s", operators.top());
                    operators.pop();
                    operators.push(entry.charAt(0));

                }
            }
        }

        return postfixExpression;
    }

    private static boolean isNumber(String entry) {
        try {
            Double.parseDouble(entry);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    private static boolean isParenth(String entry) {
        return entry.charAt(0) == ')' || entry.charAt(0) == '(';
    }

    private static boolean isOperator(String entry) {
        char op = entry.charAt(0);
        try {
            entry.charAt(1);

        } catch (StringIndexOutOfBoundsException e) {
            return op == '+' || op == '-' ||
                op == '*' || op == '/' ||
                op == 'p';

        }

        return false;
    }

    private static int getOperatorPrecedence(char op) {
        switch (op) {
            case '+':
                return 1;
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
            case '^':
            case 'p':
                return 5;
        }

        return 0;
    }

    private static double execOperator(double rightOperand, double leftOperand, char op) {
        // sorry, c++ habits ;)
        return (op == '+')? rightOperand + leftOperand:
            (op == '-')? rightOperand - leftOperand:
                (op == '*')? rightOperand * leftOperand:
                    (op == '/')? rightOperand / leftOperand:
                        (op == '^')? Math.pow(rightOperand, leftOperand):
                            0;
    }

}
