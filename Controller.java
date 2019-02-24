package sample;
import java.util.Scanner;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label welcomeLabel;
    @FXML
    public Label InprepostLabel;
    @FXML
    public Label prefixLabel;
    @FXML
    public Label postfixLabel;
    @FXML
    public TextField userinputField;
    @FXML
    public TextField preoutField;
    @FXML
    public TextField postoutField;
    @FXML
    public Button convertButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                convert();
            }
        });

    }

    private void convert() {
        String input=userinputField.getText();
        if ("".equals(input)){
            warnUser();
        }
        else {
            String postout= infixToPostfix(input);
            String preout=infixToPrefix(input);

            postoutField.setText(postout);
            preoutField.setText(preout);
        }
    }
    private void warnUser() {
        System.out.println("On di");
        Alert alertDialog= new Alert(Alert.AlertType.ERROR);
        alertDialog.setTitle(" Error Occured ");
        alertDialog.setHeaderText(" *** Error *** ");
        alertDialog.setContentText(" please enter a valid String");
        alertDialog.show();
        return;
    }

    // Infix to postfix

    static int Prec(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
    // The main method that converts given infix expression
    // to postfix expression.
    static String infixToPostfix(String exp)
    {
        // initializing empty String for result
        String result = new String("");
        // initializing empty stack
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);
            // If the scanned character is an operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;
                // If the scanned character is an '(', push it to the stack.
            else if (c == '(')
                stack.push(c);
                // If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();
                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // invalid expression
                else
                    stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek()))
                    result += stack.pop();
                stack.push(c);
            }
        }
        // pop all the operators from the stack
        while (!stack.isEmpty())
            result += stack.pop();
        return result;
    }


 // Infix to prefix
 static boolean isOperator(char c)
 {
     return (!(c >= 'a' && c <= 'z') &&
             !(c >= '0' && c <= '9') &&
             !(c >= 'A' && c <= 'Z'));
 }
    // Function to find priority
// of given operator.
    static int getPriority(char C)
    {
        if (C == '-' || C == '+')
            return 1;
        else if (C == '*' || C == '/')
            return 2;
        else if (C == '^')
            return 3;
        return 0;
    }
    // Function that converts infix
// expression to prefix expression.
    static String infixToPrefix(String infix)
    {
        // stack for operators.
        Stack<Character> operators = new Stack<Character>();
        // stack for operands.
        Stack<String> operands = new Stack<String>();
        for (int i = 0; i < infix.length(); i++)
        {
            // If current character is an
            // opening bracket, then
            // push into the operators stack.
            if (infix.charAt(i) == '(')
            {
                operators.push(infix.charAt(i));
            }
            // If current character is a
            // closing bracket, then pop from
            // both stacks and push result
            // in operands stack until
            // matching opening bracket is
            // not found.
            else if (infix.charAt(i) == ')')
            {
                while (!operators.empty() &&
                        operators.peek() != '(')
                {
                    // operand 1
                    String op1 = operands.peek();
                    operands.pop();
                    // operand 2
                    String op2 = operands.peek();
                    operands.pop();
                    // operator
                    char op = operators.peek();
                    operators.pop();
                    // Add operands and operator
                    // in form operator +
                    // operand1 + operand2.
                    String tmp = op + op2 + op1;
                    operands.push(tmp);
                }
                // Pop opening bracket
                // from stack.
                operators.pop();
            }
            // If current character is an
            // operand then push it into
            // operands stack.
            else if (!isOperator(infix.charAt(i)))
            {
                operands.push(infix.charAt(i) + "");
            }
            // If current character is an
            // operator, then push it into
            // operators stack after popping
            // high priority operators from
            // operators stack and pushing
            // result in operands stack.
            else
            {
                while (!operators.empty() &&
                        getPriority(infix.charAt(i)) <=
                                getPriority(operators.peek()))
                {
                    String op1 = operands.peek();
                    operands.pop();
                    String op2 = operands.peek();
                    operands.pop();
                    char op = operators.peek();
                    operators.pop();
                    String tmp = op + op2 + op1;
                    operands.push(tmp);
                }
                operators.push(infix.charAt(i));
            }
        }
        // Pop operators from operators
        // stack until it is empty and
        // operation in add result of
        // each pop operands stack.
        while (!operators.empty())
        {
            String op1 = operands.peek();
            operands.pop();
            String op2 = operands.peek();
            operands.pop();
            char op = operators.peek();
            operators.pop();
            String tmp = op + op2 + op1;
            operands.push(tmp);
        }
        // Final prefix expression is
        // present in operands stack.
        return operands.peek();
    }




}
