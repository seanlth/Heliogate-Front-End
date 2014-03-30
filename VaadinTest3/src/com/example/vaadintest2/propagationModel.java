package com.example.vaadintest2;


import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class propagationModel {
	
	private String startTime;
	private String startPosit;
	private String startAngle;
	private String startSpeed;
	private String speedError;
	
	private String inputLine;
	private String resultsURL;
	
	
	
	public propagationModel(String startTime, String startPosit, String startAngle, String startSpeed, String speedError){
		this.startTime = startTime;
		this.startPosit = startPosit;
		this.startAngle = startAngle;
		this.startSpeed = startSpeed;
		this.speedError = speedError;
	}
	public String results(){
		return resultsURL;
	}
		
	public void sendParameter() throws Exception{
			URL url = new URL("http://cagnode58.cs.tcd.ie:8080/PropagationModelGUI/executeCME.jsp");
			HttpURLConnection connect = (HttpURLConnection) url.openConnection();
			connect.setRequestMethod("POST");
			
			String parameters = "startTime=" + startTime + "&startPosition=" + startPosit +
					"&startAngle=" + startAngle + "&startSpeed=" + startSpeed + "&errorSpeed=" + speedError;
			
			connect.setDoOutput(true);
			connect.setDoInput(true);
			DataOutputStream wr = new DataOutputStream(connect.getOutputStream());
			wr.writeBytes(parameters);
			wr.flush();
			wr.close();
			
			
			System.out.println(connect.getHeaderFields());
			System.out.println(connect.getURL());
			DataInputStream input = new DataInputStream(connect.getInputStream());
							
			for(int c = input.read(); c != -1; c = input.read()){
				inputLine = inputLine + (char) c;
			}
			
			Document doc = Jsoup.parse(inputLine);
			Elements links = doc.select("a[href]");
			for(Element link : links){
				resultsURL = link.attr("abs:href");
			}
			
	}
	public void printInput(){
		System.out.println(resultsURL);
	}
}

