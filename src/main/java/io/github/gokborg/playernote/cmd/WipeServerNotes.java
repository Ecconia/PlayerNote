package io.github.gokborg.playernote.cmd;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;

public class WipeServerNotes implements CommandExecutor
{
	NoteHandler notehdlr;
	
	public WipeServerNotes(NoteHandler notehdlr)
	{
		this.notehdlr = notehdlr;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length == 1)
		{
			if(args[0].equalsIgnoreCase("confirm"))
			{
				for(UUID pid : notehdlr.getServerNotes().keySet())
				{
					notehdlr.getServerNotes().get(pid).clear();
				}
				
				return true;
			}
			else
			{
				return false;
			}
		}
		
		return false;
	}
}
