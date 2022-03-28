package com.week2;

import com.week2.Calculator;
import java.util.Scanner;

public class CalcWithInput {
	public static void main(String[] args) {
		System.out.print("your expression >> ");
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		Calculator c = new Calculator(expression);
		System.out.println("your stuff:\n" + c);
	}
}
