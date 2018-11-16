package com.arjvik.jtesting.examviewparser;

import static com.arjvik.jtesting.examviewparser.util.NodeUtils.getChild;
import static com.arjvik.jtesting.examviewparser.util.NodeUtils.getChildren;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.arjvik.jtesting.examviewparser.domain.MultipleChoiceQuestion;
import com.arjvik.jtesting.examviewparser.domain.Question;
import com.arjvik.jtesting.examviewparser.domain.TestBank;
import com.arjvik.jtesting.examviewparser.domain.TrueFalseQuestion;

import lombok.SneakyThrows;

public class BlackboardZipParser implements TestBankParser {

	XPath xpath = XPathFactory.newInstance().newXPath();

	@Override
	@SneakyThrows
	public TestBank parse(InputStream raw) {
		InputStream unzipped = getUnzipped(raw);
		return parseXMLStream(unzipped);
	}

	private TestBank parseXMLStream(InputStream unzipped) throws SAXException, IOException, ParserConfigurationException {
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(unzipped);
		Node root = doc.getFirstChild();
		NodeList children = root.getChildNodes();
		String title = null;
		List<Question> questions = new ArrayList<>();
		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE)
				continue;
			Element e = (Element) node;
			switch (e.getNodeName()) {
			case "TITLE":
				title = e.getAttribute("value");
				break;
			case "QUESTION_MULTIPLECHOICE":
				questions.add(getMultipleChoiceQuestion(e));
				break;
			case "QUESTION_TRUEFALSE":
				questions.add(getTrueFalseQuestion(e));
				break;
			}
		}
		return new TestBank(title, questions);
	}

	private MultipleChoiceQuestion getMultipleChoiceQuestion(Element questionRoot) {
		String questionText = getChild(questionRoot, "BODY", "TEXT").getTextContent();
		String correctAnswerID = getChild(questionRoot, "GRADABLE", "CORRECTANSWER").getAttribute("answer_id");
		List<Element> answerElements = getChildren(questionRoot, "ANSWER");
		List<String> answers = new ArrayList<>();
		int correctAnswer = -1;
		for (int i = 0; i < answerElements.size(); i++) {
			Element e = answerElements.get(i);
			if (e.getAttribute("id").equals(correctAnswerID))
				correctAnswer = i;
			answers.add(getChild(e, "TEXT").getTextContent());
		}
		return new MultipleChoiceQuestion(questionText, answers, correctAnswer);
	}

	private TrueFalseQuestion getTrueFalseQuestion(Element questionRoot) {
		String questionText = getChild(questionRoot, "BODY", "TEXT").getTextContent();
		String correctAnswerID = getChild(questionRoot, "GRADABLE", "CORRECTANSWER").getAttribute("answer_id");
		List<Element> answerElements = getChildren(questionRoot, "ANSWER");
		boolean correctAnswer = false;
		for (int i = 0; i < answerElements.size(); i++) {
			Element e = answerElements.get(i);
			if (e.getAttribute("id").equals(correctAnswerID))
				correctAnswer = Boolean.parseBoolean(getChild(e, "TEXT").getTextContent());
		}
		return new TrueFalseQuestion(questionText, correctAnswer);
	}

	private InputStream getUnzipped(InputStream raw) throws IOException {
		ZipInputStream zis = new ZipInputStream(raw);
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null && !entry.getName().equals("res00000.dat"));
		return zis;
	}

	public static void main(String[] args) throws IOException {
		((TestBankParser) new BlackboardZipParser())
				.parse(new FileInputStream("/home/arjvik/Documents/ExamView/MyBankBlackboard6-7.zip"));
	}

}
