package io.github.gokborg.playernote.plugin;

import org.bukkit.ChatColor;

public enum Judgement
{
	POSITIVE(ChatColor.GREEN),
	NEGATIVE(ChatColor.RED),
	ISSUE(ChatColor.YELLOW);
	
	private ChatColor color;
	
	private Judgement(ChatColor color)
	{
		this.color = color;
	}
	
	public ChatColor getColor()
	{
		return this.color;
	}
}
