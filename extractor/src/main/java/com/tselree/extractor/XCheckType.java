package com.tselree.extractor;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.tselree.extractor.model.Check;

public class XCheckType {
	public Check exec(String xml) throws Exception{
		xml = xml.replaceAll("[^\\x20-\\x7e\\x0A]", "");
		InputSource source = new InputSource(new StringReader(xml));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(source);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		Check check = new Check();
		check.setBusinessID(xpath.evaluate("/Root/Package/@BusinessID", document));
		check.setType(xpath.evaluate("/Root/Package/@type", document));
		return check;
	}
}
