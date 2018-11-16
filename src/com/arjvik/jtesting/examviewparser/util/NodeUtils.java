package com.arjvik.jtesting.examviewparser.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NodeUtils {
	public static Element getChild(Node n, String name) {
		NodeList nodeList = n.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			if(nodeList.item(i).getNodeName().equals(name))
				return (Element) nodeList.item(i);
		}
		return null;
	}
	
	public static Element getChild(Node n, String... name) {
		if(name.length == 1)
			return getChild(n, name[0]);
		else
			return getChild(getChild(n, Arrays.copyOfRange(name, 0, name.length-1)), name[name.length-1]);
	}
	
	public static List<Element> getChildren(Node n, String name) {
		List<Element> nodes = new ArrayList<>();
		NodeList nodeList = n.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			if(nodeList.item(i).getNodeName().equals(name))
				nodes.add((Element) nodeList.item(i));
		}
		return nodes;
	}
}