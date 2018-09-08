package io.github.gokborg.playernote.cmd;

import java.util.Date;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.Judgement;
import io.github.gokborg.playernote.plugin.NoteHandler;
import io.github.gokborg.playernote.plugin.NotePlugin;

public class CreateNote extends SubCommand
{
	private NoteHandler noteHandler;
	
	public CreateNote(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if(args.length >= 3)
		{
			UUID uuid = NotePlugin.getPlayerUUID(sender.getServer(), args[0]);
			if(uuid != null)
			{
				Judgement judgement = Judgement.getFrom(args[1]);
				if(judgement == null)
				{
					sender.sendMessage(ChatColor.RED + "Invalid type, use: + - !");
					return;
				}
				
				String msg = "";
				for(int i = 2; i < args.length; i++)
				{
					msg += args[i] + " ";
				}
				
				//TODO: Get the correct name at some point, with the help of a caching plugin?
				noteHandler.addNote(sender, uuid, args[0], new Date(), msg, judgement);
				sender.sendMessage(ChatColor.GREEN + "Note has been added.");
			}
			else
			{
				sender.sendMessage(ChatColor.RED + "Player has never visited this server.");
			}
		}
		else
		{
			sender.sendMessage(ChatColor.RED + "Usage: /note write <playername> <+, -, !> <note message>");
		}
	}
}
