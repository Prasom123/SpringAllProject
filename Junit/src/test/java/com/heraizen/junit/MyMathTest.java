package com.heraizen.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyMathTest {
	private MyMath obj;

	@BeforeEach
	void init() {
		obj = new MyMath();
	}

	@Test
	@DisplayName("Biggest as first number in two numbers")
	void BiggestOfTwoNumberAsFirstNumber() {
		Assertions.assertEquals(15, obj.biggest(15, 10));
	}

	@Test
	@DisplayName("Biggest as second number in two numbers")
	void BiggestAsOfTwoNumberSecondNumber() {
		Assertions.assertEquals(20, obj.biggest(10, 20));
	}

	@Test
	@DisplayName("Biggest as first number in three numbers A")
	void BiggestOfThreeNumberAsFirstNumberA() {
		Assertions.assertEquals(30, obj.biggest(30, 13, 15));
	}
	@Test
	@DisplayName("Biggest as first number in three numbers B")
	void BiggestOfThreeNumberAsFirstNumberB() {
		Assertions.assertEquals(30, obj.biggest(30, 28, 15));
	}
	@Test
	@DisplayName("Biggest as second number in three numbers A")
	void BiggestOfThreeNumberAsSecondNumberA() {
		Assertions.assertEquals(20, obj.biggest(10, 20, 15));
	}
	@Test
	@DisplayName("Biggest as second number in three numbers B")
	void BiggestOfThreeNumberAsSecondNumberB() {
		Assertions.assertEquals(20, obj.biggest(15, 20, 10));
	}
	@Test
	@DisplayName("Biggest as third number in three numbers A")
	void BiggestOfThreeNumberAsThirdNumberA() {
		Assertions.assertEquals(25, obj.biggest(10, 13, 25));
	}
	@Test
	@DisplayName("Biggest as third number in three numbers A")
	void BiggestOfThreeNumberAsThirdNumberB() {
		Assertions.assertEquals(25, obj.biggest(20, 13, 25));
	}
}
