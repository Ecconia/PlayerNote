package io.github.gokborg.playernote.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;

//TODO: Add option and confirm to delete all notes of a player.
public class RemoveNote implements CommandExecutor
{
	NoteHandler noteHandler;
	
	public RemoveNote(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(args.length == 2)
		{
			OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[0]);
			if(targetPlayer != null)
			{
				if(noteHandler.hasNotes(targetPlayer.getUniqueId()))
				{
					int value = Integer.valueOf(args[1]);
					if(value >= 1)
					{
						if(noteHandler.getNotes(targetPlayer.getUniqueId()) != null)
						{
							if(value > noteHandler.getNotes(targetPlayer.getUniqueId()).size())
							{
								sender.sendMessage("Invalid number!");
								return false;
							}
							
							value -= 1;
							noteHandler.removeNote(targetPlayer.getUniqueId(), value);
							sender.sendMessage("The note has been removed!");
						}
						else
						{
							sender.sendMessage("The player's list is already empty!");
						}
					}
					else
					{
						sender.sendMessage("Not a valid number!");
						return false;
					}
				}
			}
			else
			{
				sender.sendMessage("Invalid player!");
				return false;
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
}
