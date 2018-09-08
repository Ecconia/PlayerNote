package io.github.gokborg.playernote.plugin;

import org.bukkit.ChatColor;

public enum Judgement
{
	POSITIVE(ChatColor.GREEN, '+'),
	NEGATIVE(ChatColor.RED, '-'),
	ISSUE(ChatColor.YELLOW, '!');
	
	private final ChatColor color;
	private final char judgementChar;
	
	private Judgement(ChatColor color, char c)
	{
		this.color = color;
		this.judgementChar = c;
	}
	
	public ChatColor getColor()
	{
		return this.color;
	}
	
	public char getChar()
	{
		return judgementChar;
	}
	
	public static Judgement getFrom(String in)
	{
		if(in.length() == 1)
		{
			char charIn = in.charAt(0);
			if(charIn == '!')
			{
				return ISSUE;
			}
			else if(charIn == '+')
			{
				return POSITIVE;
			}
			else if(charIn == '-')
			{
				return NEGATIVE;
			}
		}
		
		return null;
	}
}
