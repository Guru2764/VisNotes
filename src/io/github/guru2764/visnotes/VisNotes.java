package io.github.guru2764.visnotes;

import org.bukkit.command.CommandExecutor;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

//Main Plugin Class
public class VisNotes extends JavaPlugin
{
	//On Startup
    public void onEnable() {
        this.getLogger().info("VisNotes has been enabled!");
        
        //Checks for plugin folder
        if (!this.getDataFolder().exists()) {
            this.getLogger().info("No plugin folder detected...");
            this.getDataFolder().mkdir();
            this.getLogger().info("Made a new plugin folder!");
        }
        
        //Checks for notes folder
        final File notesFolder = new File(this.getDataFolder() + "/notes");
        if (!notesFolder.exists()) {
            this.getLogger().info("No notes folder detected...");
            notesFolder.mkdir();
            this.getLogger().info("Made a new notes folder!");
        }
        
        //Checks for config
        final File configFile = new File(this.getDataFolder() + "/config.yml");
        if (!configFile.exists()) {
            this.getLogger().info("No config.yml file detected...");
            this.saveDefaultConfig();
            this.getLogger().info("Made a new config file!");
        }

        //Initializes commands
        this.getCommand("vnotes").setExecutor((CommandExecutor)new VisNotesCommandExecutor(this));
    }
    
    
    //On shutdown
    public void onDisable() {
        this.getLogger().info("VisNotes is shutting down...");
    }
}