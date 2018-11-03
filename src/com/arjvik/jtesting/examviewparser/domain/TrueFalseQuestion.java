package com.arjvik.jtesting.examviewparser.domain;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class TrueFalseQuestion implements Question {

	private final String questionText;
	private final boolean correctAnswer;
	
	@Override
	public QuestionType getType() {
		return QuestionType.TRUE_FALSE;
	}

}
