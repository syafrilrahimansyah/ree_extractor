package com.tselree.extractor;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class XExtractVTS {
public Map<String,String> execute(String payload, List<XpathList> xpathList) throws Exception{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document document = builder.parse(payload);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		Map<String,String> mapColValue = new HashMap<String,String>();
		for(XpathList xpathSrc : xpathList) {
			if(xpathSrc.getMultiplevalue()) {
				XPathExpression expr = xpath.compile(xpathSrc.getParent()+xpathSrc.getPath());
		        Object result = expr.evaluate(document, XPathConstants.NODESET);
		        NodeList nodes = (NodeList) result;
		        List<String> resultList = new ArrayList<>();
		        for (int i = 0; i < nodes.getLength(); i++) {
		        	resultList.add(nodes.item(i).getNodeValue());
		        }
		        mapColValue.put(xpathSrc.getColumn(), String.join("~", resultList));
				//return String.join("~", resultList);
			}else{
				mapColValue.put(xpathSrc.getColumn(), xpath.evaluate(xpathSrc.getParent()+xpathSrc.getPath(), document));
			}
		}
		return mapColValue;
	}
}
