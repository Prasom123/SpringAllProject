package com.heraizen.cj.domain;

import java.util.ArrayList;
import java.util.List;

public class Questions {
	private int qid;
	private String questionData;
	private List<String> options=new ArrayList<>();
	private String  answer;
	public Questions()
	{
		
	}
	public Questions(int qid, String questionDate, List<String> options, String answer) {
		this.qid = qid;
		this.questionData = questionDate;
		this.options = options;
		this.answer = answer;
	}
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQuestionData() {
		return questionData;
	}
	public void setQuestionData(String questionDate) {
		this.questionData = questionDate;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Questions [qid=" + qid + ", questionDate=" + questionData + ", options=" + options + ", answer="
				+ answer + "]";
	}
	
	
	
}

