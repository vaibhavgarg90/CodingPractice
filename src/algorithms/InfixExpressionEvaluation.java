package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class InfixExpressionEvaluation {

  public static double calc(String expression) {
    return eval(tokenize(expression));
  }

  private static List<String> tokenize(String expression) {
    List<String> tokens = new ArrayList<>();
    Character lastChar = null;
    String curOperand = null;
    for (char c : expression.toCharArray()) {
      if (c == ' ') {
        continue;
      }
      // digit or '.'
      else if (Character.isDigit(c) || c == '.') {
        if (curOperand == null) {
          curOperand = "" + c;
        } else if (lastChar != null && (Character.isDigit(lastChar) || lastChar == '.')) {
          curOperand += c;
        }
      }
      // operator or '(' or ')'
      else {
        if (curOperand != null) {
          tokens.add(curOperand);
        }
        tokens.add("" + c);
        curOperand = null;
      }
      lastChar = c;
    }
    if (curOperand != null) {
      tokens.add(curOperand);
    }
    System.out.println(tokens);
    tokens = sanitizeTokens(tokens);
    return tokens;
  }

  private static List<String> sanitizeTokens(List<String> tokens) {
    return mergeDanglingOperatorsWithOperands(mergeAdjacentOperators(tokens));
  }

  private static List<String> mergeAdjacentOperators(List<String> tokens) {
    List<String> tokensWithMergedAdjacentOperators = new ArrayList<>();
    String lastOperator = null;
    for (String token : tokens) {
      if (token.equals("+")) {
        if (lastOperator == null) {
          lastOperator = "+";
        } else {
          if (lastOperator.equals("+")) {
            lastOperator = "+";
          } else {
            lastOperator = "-";
          }
        }
      } else if (token.equals("-")) {
        if (lastOperator == null) {
          lastOperator = "-";
        } else {
          if (lastOperator.equals("+")) {
            lastOperator = "-";
          } else {
            lastOperator = "+";
          }
        }
      } else {
        if (lastOperator != null) {
          tokensWithMergedAdjacentOperators.add(lastOperator);
        }
        tokensWithMergedAdjacentOperators.add(token);
        lastOperator = null;
      }
    }
    System.out.println(tokensWithMergedAdjacentOperators);
    return tokensWithMergedAdjacentOperators;
  }

  private static List<String> mergeDanglingOperatorsWithOperands(List<String> tokens) {
    List<String> tokensWithMergedDanglingOperatorsWithOperands = new ArrayList<>();
    String operatorToMerge = "";
    for (int i = 0; i < tokens.size(); i++) {
      String token = tokens.get(i);
      String prevToken = (i > 0) ? tokens.get(i - 1) : null;
      if (token.equals("+") || token.equals("-")) {
        if (prevToken == null
            || prevToken.equals("*")
            || prevToken.equals("/")
            || prevToken.equals("(")) {
          operatorToMerge = token;
        } else {
          tokensWithMergedDanglingOperatorsWithOperands.add(token);
          operatorToMerge = "";
        }
      } else if (token.equals("(")) {
        String prevToPrevToken = (i > 1) ? tokens.get(i - 2) : null;
        if (prevToken != null
            && (prevToken.equals("-") || prevToken.equals("+"))
            && (prevToPrevToken == null
            || prevToPrevToken.equals("*")
            || prevToPrevToken.equals("/"))) {
          if (prevToken.equals("-")) {
            tokensWithMergedDanglingOperatorsWithOperands.add("-1");
            tokensWithMergedDanglingOperatorsWithOperands.add("*");
          }
          tokensWithMergedDanglingOperatorsWithOperands.add(token);
        } else {
          if (!operatorToMerge.equals("")) {
            tokensWithMergedDanglingOperatorsWithOperands.add(operatorToMerge);
          }
          tokensWithMergedDanglingOperatorsWithOperands.add(token);
        }
        operatorToMerge = "";
      } else {
        tokensWithMergedDanglingOperatorsWithOperands.add(operatorToMerge + token);
        operatorToMerge = "";
      }
    }
    System.out.println(tokensWithMergedDanglingOperatorsWithOperands);
    return tokensWithMergedDanglingOperatorsWithOperands;
  }

  private static double eval(List<String> tokens) {
    Stack<Double> operandStack = new Stack<>();
    Stack<String> operatorStack = new Stack<>();
    for (String token : tokens) {
      if (token.equals("(")) {
        operatorStack.push("(");
      } else if (token.equals(")")) {
        while (true) {
          String operator = operatorStack.pop();
          if (operator.equals("(")) {
            break;
          }
          Double val = performOperation(operandStack.pop(), operandStack.pop(), operator);
          operandStack.push(val);
        }
      } else if (isOperator(token)) {
        String lastOperator = operatorStack.isEmpty() ? null : operatorStack.pop();
        if (lastOperator == null) {
          operatorStack.push(token);
        } else if (lastOperator.equals("(") || isHigherPriority(token, lastOperator)) {
          operatorStack.push(lastOperator);
          operatorStack.push(token);
        } else {
          Double val = performOperation(operandStack.pop(), operandStack.pop(), lastOperator);
          operandStack.push(val);
          operatorStack.push(token);
        }
      } else {
        operandStack.push(Double.parseDouble(token));
      }
    }
    System.out.println(operandStack);
    System.out.println(operatorStack);
    while (!operatorStack.isEmpty()) {
      Double val = performOperation(operandStack.pop(), operandStack.pop(), operatorStack.pop());
      operandStack.push(val);
    }
    return operandStack.pop();
  }

  private static double performOperation(Double operand1, double operand2, String operation) {
    if (operation.equals("^")) {
      return Math.pow(operand2, operand1);
    } else if (operation.equals("*")) {
      return operand2 * operand1;
    } else if (operation.equals("/")) {
      return operand2 / operand1;
    } else if (operation.equals("+")) {
      return operand2 + operand1;
    }
    return operand2 - operand1;
  }

  private static boolean isOperator(String token) {
    return token.equals("^")
        || token.equals("+")
        || token.equals("-")
        || token.equals("*")
        || token.equals("/");
  }

  private static boolean isHigherPriority(String operator1, String operator2) {
    return getPriority(operator1) > getPriority(operator2);
  }

  private static int getPriority(String operator) {
    if (operator.equals("+") || operator.equals("-")) {
      return 0;
    } else if (operator.equals("*") || operator.equals("/")) {
      return 1;
    }
    return 2;
  }

  public static void main(String[] args) {
    System.out.println(calc("+ - + + +(1 2    .34 ^ 2 * 3) * + -- -+1 0 1 - 123 / 2"));
  }
}
