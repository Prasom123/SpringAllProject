package com.heraizen.cj.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.heraizen.cj.domain.Questions;

public class ExaminationContainer {

	private static List<Questions> questions = new ArrayList<>();
	private static Map<Integer, String> questionAnswer;
	static int attempt = 0;

	public ExaminationContainer() {

		questions.add(new Questions(1, "What is the range of short data type in Java ?",
				Arrays.asList(new String[] { "-128 to 127", "-32768 to 32767", "-2147483648 to 2147483647", "none" }),
				"b"));
		questions.add(new Questions(2, "What is the range of byte data type in Java ?",
				Arrays.asList(new String[] { "-128 to 127", "-32768 to 32767", "-2147483648 to 2147483647", "none" }),
				"a"));
		questions.add(new Questions(3,
				"An expression involving byte, int, and literal numbers is promoted to which of these ?",
				Arrays.asList(new String[] { "int", "long", "byte", "float" }), "a"));
		questions.add(new Questions(4, "Which of these literals can be contained in float data type variable ?",
				Arrays.asList(new String[] { "-1.7e+308", "-3.4e+038", "+1.7e+308", "-3.4e+050" }), "b"));
		questions.add(new Questions(5, "Which data type value is returned by all transcendental math functions ?",
				Arrays.asList(new String[] { "int", "float", "double", "long" }), "c"));
		questionAnswer = getQuestionAndAnswer(questions);
	}

	private static Map<Integer, String> getQuestionAndAnswer(List<Questions> questions) {
		Map<Integer, String> map = new HashMap<>();
		for (Questions q : questions) {
			map.put(q.getQid(), q.getAnswer());
		}
		return map;
	}

	public void beginTest() {
		Map<Integer, String> answer = new HashMap<>();
		List<String> index = Arrays.asList("a,b,c,d".split(","));
		for (Questions q : questions) {
			System.out.println();
			System.out.println(q.getQid() + "." + q.getQuestionData());
			int i = 0;
			for (String option : q.getOptions()) {
				System.out.println(index.get(i++) + ")" + option);
			}
			System.out.println("Enter Your Choice:..");
			@SuppressWarnings("resource")
			String choice = new Scanner(System.in).nextLine();
			if (choice.length() > 0)
				attempt++;
			answer.put(q.getQid(), choice.toLowerCase());
		}
		showResult(answer);

	}

	private void showResult(Map<Integer, String> answer) {
		System.out.println("Total Questions: " + questions.size());
		System.out.println("Total Attempted: " + attempt);
		Set<Integer> keys = answer.keySet();
		int correct = 0;
		int wrong = 0;
		for (Integer key : keys) {
			if (answer.get(key).equalsIgnoreCase(questionAnswer.get(key))) {
				correct++;

			} else if (!(answer.get(key).equalsIgnoreCase(questionAnswer.get(key)))) {
				wrong++;
			}
		}
		System.out.println("No. of correct answer: " + correct);
		System.out.println("No. of wrong answer: " + wrong);
		double correctAnswer = correct;
		double totalQuestion = questions.size();
		double percentage = (correctAnswer / totalQuestion) * 100;
		System.out.println("Your secure Percentage: " + percentage);
	}
}
