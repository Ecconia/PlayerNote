package io.github.gokborg.playernote.cmd;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.Judgement;
import io.github.gokborg.playernote.plugin.NoteHandler;

public class CreateNote implements CommandExecutor
{
	private NoteHandler notehdlr;
	
	public CreateNote(NoteHandler notehdlr)
	{
		this.notehdlr = notehdlr;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length >= 3)
		{
			OfflinePlayer other = Bukkit.getOfflinePlayer(args[0]);
			if(other != null)
			{
				String msg = "";
				for(int i = 2; i < args.length; i++)
				{
					msg += args[i] + " ";
				}
				
				Judgement judgement = null;
				
				if(args[1].equalsIgnoreCase("+"))
				{
					judgement = Judgement.POSITIVE;
				}
				else if(args[1].equalsIgnoreCase("-"))
				{
					judgement = Judgement.NEGATIVE;
				}
				else if(args[1].equalsIgnoreCase("!"))
				{
					judgement = Judgement.ISSUE;
				}
				else
				{
					sender.sendMessage("Invalid type!");
					return false;
				}
				
				notehdlr.addNote(sender, other, new Date(), msg, judgement);
				sender.sendMessage("Note has been added!");
			}
			else
			{
				sender.sendMessage("Player is not online!");
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}
