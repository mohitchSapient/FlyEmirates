package com.emirates.superhelper;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlReader {
	
	public static String xmlReader(String NodeName, String PropertyValue) {
		Element element = null;
		String Value = null;
		try {

			File Prop = new File("config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse(Prop);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			/*
			 * System.out.println("Root for xml file: " +
			 * doc.getDocumentElement().getNodeName());
			 */
			NodeList nodes = doc.getElementsByTagName(NodeName);

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) node;
					/*
					 * System.out.println("UserName is: " + getValue(username,
					 * element));
					 */
				}
			}
			
			Value = getValue(PropertyValue, element);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Value;
	}
	
	private static String getValue(String tag, Element element) {

		NodeList nodes = element.getElementsByTagName(tag).item(0)
				.getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();

	}
}
