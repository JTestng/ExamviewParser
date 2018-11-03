package com.arjvik.jtesting.examviewparser.domain;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class FreeResponseQuestion implements Question {

	private final String questionText;
	
	@Override
	public QuestionType getType() {
		return QuestionType.FREE_RESPONSE;
	}

}
