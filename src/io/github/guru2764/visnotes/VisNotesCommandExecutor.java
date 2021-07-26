package io.github.guru2764.visnotes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisNotesCommandExecutor implements CommandExecutor {

    private final VisNotes plugin;
    public PrintWriter writer;

    public VisNotesCommandExecutor(VisNotes plugin) {
        this.plugin = plugin;
    }

    //Command parsing
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    
	    //Vnote command
	    if (cmd.getName().equalsIgnoreCase("vnote")) {

	        //Permission Validation
            if(!sender.hasPermission("visnotes.note")){
                sender.sendMessage("No permission for that command!");
                return false;
            }

            //Correct Format Validation
            if(args[0].isBlank()||args[1].isBlank()) {
                sender.sendMessage("You must not leave filename or message blank!");
                return false;
            }
            String filename = args[0]+".txt";
            String message = args[1];

            File notesFile = new File(plugin.getDataFolder() + "/notes/" + filename);

            //Checks to see if player file exists
            if (!notesFile.exists()) {
                try {
                    notesFile.createNewFile();
                }
                catch (IOException e) {
                    Bukkit.getLogger().info("[VisNotes] There was a problem making the notes file for " + sender.getName() + "!");
                    return false;
                }
            }

            try {
                writer = new PrintWriter(new FileWriter(notesFile,true));
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                writer.println(System.lineSeparator()+"["+format.format(now)+"] "+message);
                writer.close();

                plugin.getLogger().info("Successfully wrote new line to "+filename+"!");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
	    else
            return false;
    }
}
