package com.tselree.extractor;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.tselree.extractor.DAO.EntityHTSDAO;
import com.tselree.extractor.model.XpathList;

public class XExtract {
		
	public String execute(String payload, String xpathSrc, Boolean multiplevalue) throws Exception{
		
		payload = payload.replaceAll("[^\\x20-\\x7e\\x0A]", "");
		InputSource source = new InputSource(new StringReader(payload));

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(source);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		if(multiplevalue) {
			XPathExpression expr = xpath.compile(xpathSrc);
	        Object result = expr.evaluate(document, XPathConstants.NODESET);
	        NodeList nodes = (NodeList) result;
	        List<String> resultList = new ArrayList<>();
	        for (int i = 0; i < nodes.getLength(); i++) {
	        	resultList.add(nodes.item(i).getNodeValue());
	        }
			return String.join("~", resultList);
		}else {
			return xpath.evaluate(xpathSrc, document);
		}
	}
}
