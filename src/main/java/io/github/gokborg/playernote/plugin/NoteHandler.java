package io.github.gokborg.playernote.plugin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class NoteHandler
{
	private final Map<UUID, List<Note>> serverNotes;
	
	public NoteHandler(Map<UUID, List<Note>> serverNotes)
	{
		this.serverNotes = serverNotes;
	}
	
	public void addNote(CommandSender sender, OfflinePlayer targetPlayer, Date date, String msg, Judgement type)
	{
		UUID uuid = targetPlayer.getUniqueId();
		List<Note> playerNotes = serverNotes.get(uuid);
		if(playerNotes == null)
		{
			playerNotes = new ArrayList<Note>();
			serverNotes.put(uuid, playerNotes);
		}
		
		//TODO: Will cause duplicated ID's after removal
		int noteId = playerNotes.size() + 1;
		playerNotes.add(new Note(sender.getName(), targetPlayer.getName(), date, msg, type, noteId));
	}
	
	public void removeNote(UUID uuid, int noteID)
	{
		List<Note> playerNotes = serverNotes.get(uuid);
		if(playerNotes != null)
		{
			playerNotes.remove(noteID);
			if(playerNotes.isEmpty())
			{
				serverNotes.remove(uuid);
			}
		}
	}
	
	public void wipe(UUID uuid)
	{
		serverNotes.remove(uuid);
	}
	
	public List<Note> getNotes(UUID uuid)
	{
		return serverNotes.get(uuid);
	}
	
	public Map<UUID, List<Note>> getServerNotes()
	{
		return serverNotes;
	}
	
	public boolean hasNotes(UUID uuid)
	{
		return serverNotes.containsKey(uuid);
	}
}
