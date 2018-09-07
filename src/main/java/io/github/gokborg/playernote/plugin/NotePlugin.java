package io.github.gokborg.playernote.plugin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.gokborg.playernote.cmd.CreateNote;
import io.github.gokborg.playernote.cmd.GetNote;
import io.github.gokborg.playernote.cmd.RemoveNote;
import io.github.gokborg.playernote.cmd.WipeNotes;
import io.github.gokborg.playernote.cmd.WipeServerNotes;

public class NotePlugin extends JavaPlugin
{
	//TODO: Add pages to notes
	//TODO: Remove option for notes
	private NoteHandler noteHandler;
	
	public void onEnable()
	{
		Map<UUID, List<Note>> serverNotes = readNotes();
		if(serverNotes == null)
		{
			serverNotes = new HashMap<UUID, List<Note>>();
		}
		
		noteHandler = new NoteHandler(serverNotes);
		getServer().getPluginManager().registerEvents(new PlayerLogin(noteHandler), this);
		
		getCommand("playernote").setExecutor(new CreateNote(noteHandler));
		getCommand("getplayernote").setExecutor(new GetNote(noteHandler));
		getCommand("rmplayernote").setExecutor(new RemoveNote(noteHandler));
		getCommand("clearplayernotes").setExecutor(new WipeNotes(noteHandler));
		getCommand("clearservernotes").setExecutor(new WipeServerNotes(noteHandler));
	}
	
	public void onDisable()
	{
		writeNotes(noteHandler.getServerNotes());
	}
	
	public static void writeNotes(Map<UUID, List<Note>> serverNotes)
	{
		try
		{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("notesdata.bin"));
			os.writeObject(serverNotes);
			os.close();
		}
		catch(IOException e)
		{
			//TODO: Provide proper feedback
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<UUID, List<Note>> readNotes()
	{
		Map<UUID, List<Note>> serverNotes = null;
		try
		{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("notesdata.bin"));
			serverNotes = (Map<UUID, List<Note>>) is.readObject();
			is.close();
		}
		catch(IOException | ClassNotFoundException e)
		{
		}
		
		return serverNotes;
	}
}
