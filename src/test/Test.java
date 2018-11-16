package test;

import java.io.FileInputStream;
import java.io.IOException;

import com.arjvik.jtesting.examviewparser.BlackboardZipParser;
import com.arjvik.jtesting.examviewparser.TestBankParser;

public class Test {

	public static void main(String[] args) throws IOException {
		System.out.println(((TestBankParser) new BlackboardZipParser())
				.parse(new FileInputStream("/home/arjvik/Documents/ExamView/MyBankBlackboard6-7.zip")));
	}

}
