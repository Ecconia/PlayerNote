package io.github.gokborg.playernote.plugin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
	private NoteHandler notehdlr;
	
	public void onEnable()
	{
		HashMap<UUID, ArrayList<Note>> serverNotes = readNotes();
		if(serverNotes == null)
		{
			serverNotes = new HashMap<UUID, ArrayList<Note>>();
		}
		
		notehdlr = new NoteHandler(serverNotes);
		getServer().getPluginManager().registerEvents(new PlayerLogin(notehdlr), this);
		getCommand("playernote").setExecutor(new CreateNote(notehdlr));
		getCommand("getplayernote").setExecutor(new GetNote(notehdlr));
		getCommand("rmplayernote").setExecutor(new RemoveNote(notehdlr));
		getCommand("clearplayernotes").setExecutor(new WipeNotes(notehdlr));
		getCommand("clearservernotes").setExecutor(new WipeServerNotes(notehdlr));
	}
	
	public void onDisable()
	{
		writeNotes(notehdlr.getServerNotes());
	}
	
	public static void writeNotes(HashMap<UUID, ArrayList<Note>> notes)
	{
		try
		{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("notesdata.bin"));
			os.writeObject(notes);
			os.close();
		}
		catch(IOException e)
		{
			//TODO: Provide proper feedback
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<UUID, ArrayList<Note>> readNotes()
	{
		HashMap<UUID, ArrayList<Note>> notes = null;
		try
		{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("notesdata.bin"));
			notes = (HashMap<UUID, ArrayList<Note>>) is.readObject();
			is.close();
		}
		catch(IOException | ClassNotFoundException e)
		{
		}
		
		return notes;
	}
}
