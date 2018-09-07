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
		if(cmd.getName().equalsIgnoreCase("playernote") && args.length >= 3)
		{
			OfflinePlayer other = Bukkit.getOfflinePlayer(args[0]);
			if(Bukkit.getOfflinePlayer(args[0]) != null)
			{
				String msg = "";
				for(int i = 2; i < args.length; i++)
				{
					msg += args[i] + " ";
				}
				
				Judgement type = null;
				
				if(args[1].equalsIgnoreCase("+"))
				{
					type = Judgement.POSITIVE;
				}
				else if(args[1].equalsIgnoreCase("-"))
				{
					type = Judgement.NEGATIVE;
				}
				else if(args[1].equalsIgnoreCase("!"))
				{
					type = Judgement.ISSUE;
				}
				else
				{
					sender.sendMessage("Invalid type!");
					return false;
				}
				
				notehdlr.addNote(sender, other, new Date(), msg, type);
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
