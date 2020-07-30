package com.tselree.extractor;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.tselree.extractor.model.XpathList;

public class XExtract {
		
	public List<String> execute(String payload, List<XpathList> xpathList, Integer loop, String key_val) throws Exception{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(payload);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		
		List<String> valueList = new ArrayList<>();
		valueList.add("'"+key_val+"'");
		for(XpathList xpathSrc : xpathList) {
			if(xpathSrc.getMultiplevalue()) {
				XPathExpression expr = xpath.compile(xpathSrc.getParent()+"["+loop+"]"+xpathSrc.getPath());
		        Object result = expr.evaluate(document, XPathConstants.NODESET);
		        NodeList nodes = (NodeList) result;
		        List<String> resultList = new ArrayList<>();
		        for (int i = 0; i < nodes.getLength(); i++) {
		        	resultList.add(nodes.item(i).getNodeValue());
		        }
				valueList.add("'"+String.join("~", resultList)+"'");
			}else {
				valueList.add("'"+xpath.evaluate(xpathSrc.getParent()+"["+loop+"]"+xpathSrc.getPath(), document)+"'");
			}
		}
		return valueList;
		
	}
}
