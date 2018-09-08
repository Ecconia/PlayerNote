package io.github.gokborg.playernote.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;

//TODO: Add option and confirm to delete all notes of a player.
public class RemoveNote extends SubCommand
{
	NoteHandler noteHandler;
	
	public RemoveNote(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args)
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
								return;
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
						return;
					}
				}
			}
			else
			{
				sender.sendMessage("Invalid player!");
				return;
			}
		}
		else
		{
			//TODO: Usage
			return;
		}
	}
}
