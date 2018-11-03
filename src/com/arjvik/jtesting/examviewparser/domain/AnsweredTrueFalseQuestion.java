package com.arjvik.jtesting.examviewparser.domain;

import lombok.Value;

@Value
public class AnsweredTrueFalseQuestion extends TrueFalseQuestion implements AnsweredQuestion {

	private final boolean studentAnswer;
	
	public AnsweredTrueFalseQuestion(String questionText, boolean correctAnswer, boolean studentAnswer) {
		super(questionText, correctAnswer);
		this.studentAnswer = studentAnswer;
	}
	
	public AnsweredTrueFalseQuestion(TrueFalseQuestion question, boolean studentAnswer) {
		this(question.getQuestionText(), question.getCorrectAnswer(), studentAnswer);
	}

	@Override
	public boolean isCorrect() {
		return getCorrectAnswer() == getStudentAnswer();
	}

}
