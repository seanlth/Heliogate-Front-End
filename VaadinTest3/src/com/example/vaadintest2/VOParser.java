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


public class VOParser {
	
	private String[] parameterArray;
	private String[][] eventArray;
	
	public VOParser(){}
	
	public String[] getParameterArray(){
		return parameterArray;
	}
	public String[][] getEventArray(){
		return eventArray;
	}
	
	public String[][] getAverageOfEvents()
	{
		String average[][] = new String[1][this.parameterArray.length];
		
		for (int j = 0; j < this.parameterArray.length; j++) {
			double averageOfParam = 0;
			boolean ignore = false;
			for (int i = 0; i < this.eventArray.length; i++) {
				try {
					averageOfParam += Double.parseDouble(eventArray[i][j]);
				}
				catch (Exception e) {
					//not a value
					ignore = true;
					break;
				}
			}
			if (ignore) {
				average[0][j] = "";
			}
			else {
				averageOfParam /= (double)eventArray.length;
				average[0][j] = "" + averageOfParam;
			}
		}
		
		return average;
	}

	
	
	public void parse(String xml){
		try{	
			//File xmlFile = new File("/Users/seanlth/git/Heliogate-Front-End/Heliogate/VaadinTest3/sampleXML/sample.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document parsedDoc = builder.parse(new InputSource(new StringReader(xml)));
			//Document parsedDoc = builder.parse(xmlFile);
		
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
