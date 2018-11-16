package com.arjvik.jtesting.examviewparser;

import java.io.InputStream;

import com.arjvik.jtesting.examviewparser.domain.TestBank;

public interface TestBankParser {

	public TestBank parse(InputStream inputStream);
	
}