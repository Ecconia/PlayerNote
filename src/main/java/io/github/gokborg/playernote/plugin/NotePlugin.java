package io.github.gokborg.playernote.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.gokborg.playernote.cmd.CreateNote;
import io.github.gokborg.playernote.cmd.GetNote;
import io.github.gokborg.playernote.cmd.RemoveNote;
import io.github.gokborg.playernote.cmd.SubCommand;

public class NotePlugin extends JavaPlugin
{
	//TODO: Add pages to notes
	//TODO: Remove option for notes
	private NoteHandler noteHandler;
	
	private SubCommand commandWrite;
	private SubCommand commandRead;
	private SubCommand commandRemove;
	
	public void onEnable()
	{
		Map<UUID, List<Note>> serverNotes = readNotes();
		if(serverNotes == null)
		{
			serverNotes = new HashMap<UUID, List<Note>>();
		}
		
		noteHandler = new NoteHandler(serverNotes);
		
		commandWrite = new CreateNote(noteHandler);
		commandRead = new GetNote(noteHandler);
		commandRemove = new RemoveNote(noteHandler);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		String subCommand = label.toLowerCase();
		if(subCommand.equals("write"))
		{
			commandWrite.onCommand(sender, args);
		}
		else if(subCommand.equals("read"))
		{
			commandRead.onCommand(sender, args);
		}
		else if(subCommand.equals("remove"))
		{
			commandRemove.onCommand(sender, args);
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	public void onDisable()
	{
		writeNotes(noteHandler.getServerNotes());
	}
	
	public static void writeNotes(Map<UUID, List<Note>> serverNotes)
	{
		//TODO: Implement
	}
	
	public static Map<UUID, List<Note>> readNotes()
	{
		Map<UUID, List<Note>> serverNotes = null;
		
		//TODO: Implement
		
		return serverNotes;
	}
}
