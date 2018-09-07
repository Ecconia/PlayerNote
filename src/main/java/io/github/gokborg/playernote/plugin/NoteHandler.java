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
		if(serverNotes.containsKey(uuid))
		{
			int noteId = serverNotes.get(uuid).size() + 1;
			serverNotes.get(uuid).add(new Note(sender.getName(), targetPlayer.getName(), date, msg, type, noteId));
		}
		else
		{
			List<Note> playerNotes = new ArrayList<Note>();
			playerNotes.add(new Note(sender.getName(), targetPlayer.getName(), date, msg, type, 1));
			serverNotes.put(uuid, playerNotes);
		}
	}
	
	public void removeNote(UUID uuid, int noteID)
	{
		serverNotes.get(uuid).remove(noteID);
	}
	
	public void wipe(UUID uuid)
	{
		serverNotes.remove(uuid);
		serverNotes.put(uuid, new ArrayList<Note>());
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
	
	public void createPage(UUID uuid)
	{
		serverNotes.put(uuid, new ArrayList<Note>());
	}
}
