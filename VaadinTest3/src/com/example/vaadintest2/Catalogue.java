package com.example.vaadintest2;

import java.util.Date;

public class Catalogue {
	private String nameId;
	private String name;
	private Date firstDate;
	private Date lastDate;
	
	public Catalogue(String nameId, String name, Date firstDate, Date lastDate)
	{
		this.nameId = nameId;
		this.name = name;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
	}
	public String getNameId()
	{
		return this.nameId;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Date getFirstDate()
	{
		return this.firstDate;
	}
	
	public Date getLastDate()
	{
		return this.lastDate;
	}
	
	public void setNameId(String nameId)
	{
		this.nameId = nameId;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setFirstDate(Date date)
	{
		this.firstDate = date;
	}
	
	public void setLastDate(Date date)
	{
		this.lastDate = date;
	}
}
