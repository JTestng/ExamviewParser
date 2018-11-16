package com.arjvik.jtesting.examviewparser.domain;

import java.util.List;

import lombok.Value;

@Value
public class TestBank {
	private final String title;
	private final List<Question> questions;
}
