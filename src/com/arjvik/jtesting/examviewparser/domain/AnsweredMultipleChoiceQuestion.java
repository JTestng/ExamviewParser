package com.arjvik.jtesting.examviewparser.domain;

import java.util.List;

import lombok.Value;

@Value
public class AnsweredMultipleChoiceQuestion extends MultipleChoiceQuestion implements AnsweredQuestion {

	private final int studentAnswer;
	
	public AnsweredMultipleChoiceQuestion(String questionText, List<String> answers, int correctAnswer, int studentAnswer) {
		super(questionText, answers, correctAnswer);
		this.studentAnswer = studentAnswer;
	}
	
	public AnsweredMultipleChoiceQuestion(MultipleChoiceQuestion question, int studentAnswer) {
		this(question.getQuestionText(), question.getAnswers(), question.getCorrectAnswer(), studentAnswer);
	}

	@Override
	public boolean isCorrect() {
		return getStudentAnswer() == getCorrectAnswer();
	}

}
