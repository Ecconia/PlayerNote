package io.github.gokborg.playernote.plugin;

import java.util.Date;

public class Note
{
	private String msg;
	private Date date;
	private String sender;
	private String other;
	private Judgement judgement;
	private int noteID;
	
	public Note(String sender, String other, Date date, String msg, Judgement type, int noteID)
	{
		this.msg = msg;
		this.date = date;
		this.sender = sender;
		this.other = other;
		this.judgement = type;
		this.noteID = noteID;
	}
	
	public String getMsg()
	{
		return msg;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public String getSender()
	{
		return sender;
	}
	
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	
	public String getOther()
	{
		return other;
	}
	
	public void setOther(String other)
	{
		this.other = other;
	}
	
	public Judgement getJudgement()
	{
		return judgement;
	}
	
	public void setJudgement(Judgement judgement)
	{
		this.judgement = judgement;
	}
	
	public int getNoteID()
	{
		return noteID;
	}
	
	public void setNoteID(int noteID)
	{
		this.noteID = noteID;
	}
}
