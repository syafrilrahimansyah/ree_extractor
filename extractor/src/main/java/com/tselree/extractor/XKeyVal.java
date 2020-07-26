package com.tselree.extractor;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public class XKeyVal {
	public String exec(String xml, String key_path) throws Exception{
		xml = xml.replaceAll("[^\\x20-\\x7e\\x0A]", "");
		InputSource source = new InputSource(new StringReader(xml));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(source);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		return xpath.evaluate(key_path, document);
	}
}
