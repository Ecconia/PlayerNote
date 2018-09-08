package io.github.gokborg.playernote.cmd;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;
import io.github.gokborg.playernote.plugin.NotePlugin;

//TODO: Add option and confirm to delete all notes of a player.
//TODO: Add pagination
public class RemoveNote extends SubCommand
{
	NoteHandler noteHandler;
	
	public RemoveNote(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if(args.length == 2)
		{
			//TODO: Query all notes instead.
			UUID uuid = NotePlugin.getPlayerUUID(sender.getServer(), args[0]);
			if(uuid != null)
			{
				if(noteHandler.hasNotes(uuid))
				{
					try
					{
						int value = Integer.parseInt(args[1]);
						if(value > noteHandler.getNotes(uuid).size())
						{
							sender.sendMessage(ChatColor.RED + "Not that many notes.");
							return;
						}
						
						noteHandler.removeNote(uuid, value - 1);
						sender.sendMessage(ChatColor.GREEN + "The note has been removed.");
					}
					catch(NumberFormatException e)
					{
						sender.sendMessage(ChatColor.RED + "Not a valid number.");
						return;
					}
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "This player has no notes stored.");
					return;
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Player never visited the server.");
				return;
			}
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "Usage: /note read <playername> <note number>");
			return;
		}
	}
}
