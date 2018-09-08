package io.github.gokborg.playernote.cmd;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.Judgement;
import io.github.gokborg.playernote.plugin.NoteHandler;

public class CreateNote extends SubCommand
{
	private NoteHandler noteHandler;
	
	public CreateNote(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		if(args.length >= 3)
		{
			OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[0]);
			if(targetPlayer != null)
			{
				String msg = "";
				for(int i = 2; i < args.length; i++)
				{
					msg += args[i] + " ";
				}
				
				Judgement judgement = Judgement.getFrom(args[1]);
				if(judgement == null)
				{
					sender.sendMessage(ChatColor.RED + "Invalid type, use: + - !");
					return;
				}
				
				noteHandler.addNote(sender, targetPlayer, new Date(), msg, judgement);
				sender.sendMessage("Note has been added!");
			}
			else
			{
				sender.sendMessage("Player is not online!");
				return;
			}
			
			return;
		}
		
		//TODO: Usage
		return;
	}
}
