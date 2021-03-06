package io.github.gokborg.playernote.cmd;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.gokborg.playernote.plugin.NoteHandler;

public class RemoveNote implements CommandExecutor{
	NoteHandler notehdlr;
	public RemoveNote(NoteHandler notehdlr) {
		this.notehdlr = notehdlr;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("rmplayernote") && sender.hasPermission("rmplayernote.permission")) {
			if(args.length == 2) {
				OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
				if(player != null) {
					if (notehdlr.hasNotes(player.getUniqueId())) {
						int value = Integer.valueOf(args[1]);
						if (value >= 1) {
							if(notehdlr.getNotes(player.getUniqueId()) != null) {
								if (value > notehdlr.getNotes(player.getUniqueId()).size()) {
									sender.sendMessage("Invalid number!");
									return false;
								}
								value -= 1;
								notehdlr.removeNote(player.getUniqueId(), value);
								sender.sendMessage("The note has been removed!");
							}
							else {
								sender.sendMessage("The player's list is already empty!");
							}
						}
						else {
							sender.sendMessage("Not a valid number!");
							return false;
						}
						
					}
				}
				else {
					sender.sendMessage("Invalid player!");
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
		return false;
	}
	
}
