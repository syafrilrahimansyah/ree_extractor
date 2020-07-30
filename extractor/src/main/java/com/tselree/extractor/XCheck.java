package com.tselree.extractor;

import java.io.StringReader;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.tselree.extractor.model.Check;

public class XCheck {
	public Check exec(String xml) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xml);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		Check check = new Check();
		check.setEntityID(xpath.evaluate("/Root/Package/@BusinessID", document));
		check.setType(xpath.evaluate("/Root/Package/@type", document));
		return check;
	}
}
