package com.arjvik.jtesting.examviewparser.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class AnsweredFreeResponseQuestion extends FreeResponseQuestion implements AnsweredQuestion {

	private final String studentAnswer;
	@Getter(AccessLevel.NONE)
	private final boolean correct;
	
	public AnsweredFreeResponseQuestion(String question, String studentAnswer, boolean correct) {
		super(question);
		this.studentAnswer = studentAnswer;
		this.correct = correct;
	}
	
	@Override
	public boolean isCorrect() {
		return correct;
	}

}
