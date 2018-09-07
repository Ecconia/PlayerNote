package io.github.gokborg.playernote.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLogin implements Listener
{
	private NoteHandler noteHandler;
	
	public PlayerLogin(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if(!noteHandler.hasNotes(player.getUniqueId()))
		{
			noteHandler.createPage(player.getUniqueId());
		}
	}
}
