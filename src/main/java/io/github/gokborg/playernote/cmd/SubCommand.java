package io.github.gokborg.playernote.cmd;

import org.bukkit.command.CommandSender;

public abstract class SubCommand
{
	public void onCommand(CommandSender sender, String[] args)
	{
		//Remove first arguement
		int subArgsAmount = args.length - 1;
		String[] subArguments = new String[subArgsAmount];
		System.arraycopy(args, 1, subArguments, 0, subArgsAmount);
		
		execute(sender, subArguments);
	}
	
	protected abstract void execute(CommandSender sender, String[] args);
}
