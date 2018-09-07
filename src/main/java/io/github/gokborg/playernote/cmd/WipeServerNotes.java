package io.github.gokborg.playernote.cmd;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;

public class WipeServerNotes implements CommandExecutor
{
	NoteHandler noteHandler;
	
	public WipeServerNotes(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length == 1 && args[0].equalsIgnoreCase("confirm"))
		{
			for(UUID uuid : noteHandler.getServerNotes().keySet())
			{
				noteHandler.getServerNotes().get(uuid).clear();
			}
			
			return true;
		}
		
		return false;
	}
}
