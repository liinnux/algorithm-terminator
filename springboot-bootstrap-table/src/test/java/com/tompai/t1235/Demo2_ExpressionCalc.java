package com.tompai.t1235;

import java.util.Stack;
/**
 * 表达式求值 算符优先法
 * 3*(5-2)#  #在这里表示结尾
 *
 * 思路：
 * 使用两个栈，分别是操作数栈 存储数字 和操作符栈 存储运算符
 * 读入表达式时
 * 如果是操作数 则入操作数栈
 * 如果是运算符 则入操作符栈
 * 当运算符入栈时，和操作符栈栈顶元素比较优先级
 * 如果优先级比栈顶元素高，则入栈,并接收下一个字符
 * 如果和栈顶元素相同，则脱括号 并接收下一个字符 （ 因为相同只有( )括号）
 * 如果小于栈顶元素优先级，则操作数出栈，操作符出栈 并计算运算结果再入栈
 *
 * 关键点：循环的退出条件 直到运算全部结束，即当前栈顶元素和读入的操作符均为#
 *
 * 例子：
 * 3*(5-2)#
 *
 * 算符优先级：
 * + -按顺序先后，先来优先级大于后来的，即从左到右依次计算
 * * / 优先级大于+ - ， 与* /比较则 先来的优先级大于后来的
 * + - * / 优先级均大于(  小于)
 */
public class Demo2_ExpressionCalc {
	
	public static int priority(char a) {//判断符号优先级
        switch (a) {
            case '+':
            case '-':
            case '(':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return 0;
    }

    public static char[] middleToBack(String exp) {//中缀表达式转后缀表达式
        Stack<Character> stack = new Stack();//栈用来进出运算的符号
        char[] arr = exp.toCharArray();
        char[] brr = new char[arr.length * 2];//保存后缀表达式，需在数字和符号之间加空格区分，因此长度定为arr的2倍
        int count = 0;//标记brr

        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {//防止越界
                brr[count++] = arr[i];
                brr[count++] = ' ';
            } else if ((arr[i] >= '0' && arr[i] <= '9') && !(arr[i + 1] >= '0' && arr[i + 1] <= '9')) {
                brr[count++] = arr[i];
                brr[count++] = ' ';
            } else if (arr[i] >= '0' && arr[i] <= '9') {
                brr[count++] = arr[i];
            } else {//符号
                if (stack.isEmpty()) {
                    stack.push(arr[i]);
                } else if (arr[i] == '(') {
                    stack.push(arr[i]);
                } else if (arr[i] == ')') {
                    while (stack.peek() != '(') {//'('与')'之间的符号出栈
                        brr[count++] = stack.peek();
                        brr[count++] = ' ';
                        stack.pop();
                    }
                    stack.pop();//将'('出栈
                } else if (priority(arr[i]) >= priority(stack.peek())) {//如果优先级高于或等于栈顶元素，直接入栈
                    stack.push(arr[i]);
                } else if (priority(arr[i]) < priority(stack.peek())) {//如果优先级低于栈顶元素，依次出栈
                    while (!stack.isEmpty()) {
                        brr[count++] = stack.peek();
                        brr[count++] = ' ';
                        stack.pop();
                    }
                    stack.push(arr[i]);
                }
            }
        }
        while (!stack.empty()) {
            brr[count++] = stack.peek();
            brr[count++] = ' ';
            stack.pop();
        }
        System.out.print(brr);
        System.out.println();
        return brr;
    }

    public static void calculate(char[] brr) {//后缀表达式求得计算结果
        Stack<Integer> stack = new Stack();//栈用来进出运算的数字
        for (int i = 0; i < brr.length; ) {
            String number = "";
            if (brr[i] == ' '||brr[i]=='\0') {//空格或为空
                i++;
                continue;
            }
            if (brr[i] >= '0' && brr[i] <= '9') {//数字入栈
                while (brr[i] >= '0' && brr[i] <= '9') {
                    number += brr[i];
                    i++;
                }
                stack.push(Integer.valueOf(number));//字符型转整型入栈
            } else if(brr[i]=='+'||brr[i]=='-'||brr[i]=='*'||brr[i]=='/') {//符号
                int top1 = stack.peek();
                stack.pop();
                int top2 = stack.peek();
                stack.pop();
                int result = operate(top2,top1,brr[i]);//注意top2和top1的位置
                stack.push(result);
                i++;
            }
        }
        System.out.println(stack.peek());
    }

    public static int operate(int item1, int item2, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = item1 + item2;
                break;
            case '-':
                result = item1 - item2;
                break;
            case '*':
                result = item1 * item2;
                break;
            case '/':
                result = item1 / item2;
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        String exp = " 9+(3-1)*3+10/2";
        char[] brr = middleToBack(exp);
        calculate(brr);
    }
}
