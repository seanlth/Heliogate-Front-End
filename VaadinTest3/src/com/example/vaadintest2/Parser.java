package com.example.vaadintest2;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.File;

import org.xml.sax.InputSource;
import java.io.StringReader;


public class Parser {
	
	private String[] parameterArray;
	private String[][] eventArray;
	
	public Parser(){}
	
	public String[] getParameterArray(){
		return parameterArray;
	}
	public String[][] getEventArray(){
		return eventArray;
	}
	
	public void parse(String xml){
		try{	
			//File xmlFile = new File("sampleXML/sample.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document parsedDoc = builder.parse(new InputSource(new StringReader(xml)));
						
			NodeList parameterList = parsedDoc.getElementsByTagName("FIELD");
			NodeList eventList = parsedDoc.getElementsByTagName("TR");
			NodeList variableList = parsedDoc.getElementsByTagName("TD");
			
			parameterArray = new String[parameterList.getLength()];
			for(int i = 0; i <parameterArray.length; i++){
				Node tempNode = parameterList.item(i);
				Element element = (Element) tempNode;
				parameterArray[i] = element.getAttribute("name");
			}
					
			eventArray = new String[eventList.getLength()][parameterList.getLength()];
			
			int counter = 0;
			for(int i = 0; i < eventList.getLength(); i++){
				for(int j = 0; j < parameterList.getLength();j++){
					Node temp = variableList.item(counter++);
					Element element = (Element) temp;
					eventArray[i][j] = element.getTextContent();
				}		
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
