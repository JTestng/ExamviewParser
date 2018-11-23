/**
 * @author arjvik
 *
 */
@SuppressWarnings("module")
module com.arjvik.jtesting.examviewparser {
	requires static lombok;
	requires java.xml;
	exports com.arjvik.jtesting.examviewparser;
	exports com.arjvik.jtesting.examviewparser.domain;
}