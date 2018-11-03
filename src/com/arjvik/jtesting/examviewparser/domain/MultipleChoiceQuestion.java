package com.arjvik.jtesting.examviewparser.domain;

import java.util.List;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class MultipleChoiceQuestion implements Question {

	private final String questionText;
	private final List<String> answers;
	private final int correctAnswer;
	
	@Override
	public QuestionType getType() {
		return QuestionType.MULTIPLE_CHOICE;
	}

}
