package io.github.gokborg.playernote.cmd;

import java.util.UUID;

import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;
import io.github.gokborg.playernote.plugin.NotePlugin;

//TODO: Add option and confirm to delete all notes of a player.
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
			UUID uuid = NotePlugin.getPlayerUUID(sender.getServer(), args[0]);
			if(uuid != null)
			{
				if(noteHandler.hasNotes(uuid))
				{
					int value = Integer.valueOf(args[1]);
					if(value >= 1)
					{
						if(noteHandler.getNotes(uuid) != null)
						{
							if(value > noteHandler.getNotes(uuid).size())
							{
								sender.sendMessage("Invalid number!");
								return;
							}
							
							value -= 1;
							noteHandler.removeNote(uuid, value);
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
