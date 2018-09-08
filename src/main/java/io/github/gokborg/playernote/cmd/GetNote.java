package io.github.gokborg.playernote.cmd;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.Judgement;
import io.github.gokborg.playernote.plugin.Note;
import io.github.gokborg.playernote.plugin.NoteHandler;

public class GetNote extends SubCommand
{
	//How many notes to display in chat
	private final int DISPLAYAMT = 10;
	private NoteHandler noteHandler;
	
	public GetNote(NoteHandler noteHandler)
	{
		this.noteHandler = noteHandler;
	}
	
	//Function to see if a string is an int
	public boolean isInteger(String s)
	{
		try
		{
			Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args)
	{
		/*
		 * 1. Format: /getplayernote <player> <+ or - or !>
		 * 2. Format: /getplayernote <player> 
		 * 3. Format: /getplayernote <player> <pagenumber>
		 * 4. Format: /getplayernote <player> <+ or - or !> <pagenumber>
		 * TODO: 3 & 4
		 * 1. Returns a specific list of notes
		 * 2. Returns all notes
		 */
		
		if(args.length >= 1)
		{
			//Going through all players in the server notes
			for(UUID uuid : noteHandler.getServerNotes().keySet())
			{
				OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(uuid);
				//TODO: Missing null check.
				//Check if that player is the one the person is looking for
				if(targetPlayer.getName().equalsIgnoreCase(args[0]))
				{
					//Saving all the notes of the person they specified
					List<Note> playerNotes = noteHandler.getNotes(targetPlayer.getUniqueId());
					
					//Make it so that it always shows correct total pages
					int sizeOfNotes = playerNotes.size();
					int totalPages = sizeOfNotes / (DISPLAYAMT + 1) + 1;
					
					int pageNum = 1;
					
					int startIndex = 0;
					int endIndex = 0;
					Judgement judgement = null;
					//For command 2 & 3, [Reference the multi-line comment below onCommand function]
					
					if(args.length > 1 && isInteger(args[1]))
					{
						pageNum = Integer.parseInt(args[1]);
					}
					else
					{
						if(args.length > 1)
						{
							if(args.length == 3)
							{
								pageNum = Integer.parseInt(args[2]);
								startIndex = (pageNum - 1) * DISPLAYAMT;
								endIndex = pageNum * DISPLAYAMT;
							}
							
							if(pageNum > totalPages || pageNum <= 0)
							{
								sender.sendMessage("Page number is invalid!");
								return;
							}
							else
							{
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
									return;
								}
							}
						}
					}
					
					if(pageNum >= 1 && pageNum <= totalPages)
					{
						//Basic layout when sent
						
						endIndex = sizeOfNotes - 1 - ((pageNum - 1) * (DISPLAYAMT + 1));
						startIndex = endIndex - DISPLAYAMT;
						sender.sendMessage(targetPlayer.getName() + "'s Notes");
						sender.sendMessage("----------------------------");
						SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
						//Limiting displaying of notes to the amount specified in the variable DISPLAYAMT
						for(int i = endIndex; i >= startIndex; i--)
						{
							if(i < 0)
							{
								break;
							}
							
							if(i <= sizeOfNotes - 1)
							{
								String msg = " " + format.format(playerNotes.get(i).getDate()) + " " + playerNotes.get(i).getSender() + ": " + playerNotes.get(i).getMsg();
								if(judgement != null)
								{
									if(playerNotes.get(i).getJudgement() == judgement)
										sender.sendMessage(judgement.getColor() + Integer.toString(noteHandler.getNotes(uuid).indexOf(playerNotes.get(i))) + ". " + "(" + args[1] + ")" + msg);
								}
								else
								{
									int num = noteHandler.getNotes(uuid).indexOf(playerNotes.get(i)) + 1;
									if(playerNotes.get(i).getJudgement() == Judgement.POSITIVE)
									{
										sender.sendMessage(playerNotes.get(i).getJudgement().getColor() + Integer.toString(num) + ". " + "(+)" + msg);
									}
									else if(playerNotes.get(i).getJudgement() == Judgement.NEGATIVE)
									{
										sender.sendMessage(playerNotes.get(i).getJudgement().getColor() + Integer.toString(num) + ". " + "(-)" + msg);
									}
									else if(playerNotes.get(i).getJudgement() == Judgement.ISSUE)
									{
										sender.sendMessage(playerNotes.get(i).getJudgement().getColor() + Integer.toString(num) + ". " + "(!)" + msg);
									}
								}
							}
						}
						
						sender.sendMessage("Page: " + pageNum + "/" + totalPages);
					}
					else
					{
						//TODO: Return Error
						return;
					}
					
					return;
				}
			}
			
			sender.sendMessage("Unknown Minecraft Username!");
			return;
		}
	}
}
