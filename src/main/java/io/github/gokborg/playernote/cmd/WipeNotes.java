package io.github.gokborg.playernote.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;

public class WipeNotes implements CommandExecutor
{
	NoteHandler noteHandler;
	
	public WipeNotes(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length == 1)
		{
			OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[0]);
			if(targetPlayer != null)
			{
				if(noteHandler.hasNotes(targetPlayer.getUniqueId()))
				{
					noteHandler.wipe(targetPlayer.getUniqueId());
				}
				else
				{
					sender.sendMessage("Player does not have an entry!");
				}
			}
			else
			{
				sender.sendMessage("Player does not exist!");
			}
			
			return true;
		}
		
		return false;
	}
}
