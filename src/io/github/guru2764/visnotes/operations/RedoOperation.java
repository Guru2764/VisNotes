package io.github.guru2764.visedit.operations;

import io.github.guru2764.visedit.users.UserData;
import java.io.IOException;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class RedoOperation extends BlockCommandOperation {
 
  //Variables
  private int[] coords = new int[6];
  private int x1,x2,y1,y2,z1,z2,number,iterations;
  
  public RedoOperation(World newWorld, CommandSender newSender, JavaPlugin newPlugin) {
    super(newSender, newPlugin, newWorld);
    fillCoords();
  }
  
  //Coordinates methods
  public void fillCoords() {
    FileConfiguration config = this.plugin.getConfig();
    this.x1 = config.getInt("PlayerCoords." + getUserName() + ".x1");
    this.y1 = config.getInt("PlayerCoords." + getUserName() + ".x1");
    this.z1 = config.getInt("PlayerCoords." + getUserName() + ".x1");
    this.x2 = config.getInt("PlayerCoords." + getUserName() + ".x2");
    this.y2 = config.getInt("PlayerCoords." + getUserName() + ".x1");
    this.z2 = config.getInt("PlayerCoords." + getUserName() + ".x1");
    if (this.x1 <= this.x2) {
      this.coords[0] = this.x1;
      this.coords[1] = this.x2;
    } else {
      this.coords[0] = this.x2;
      this.coords[1] = this.x1;
    } 
    if (this.y1 <= this.y2) {
      this.coords[2] = this.y1;
      this.coords[3] = this.y2;
    } else {
      this.coords[2] = this.y2;
      this.coords[3] = this.y1;
    } 
    if (this.z1 <= this.z2) {
      this.coords[4] = this.z1;
      this.coords[5] = this.z2;
    } else {
      this.coords[4] = this.z2;
      this.coords[5] = this.z1;
    } 
  }
  public int[] getCoords() {
    return this.coords;
  }
  
  //Iterations methods
  public void setIterations(int newIterations) {
    this.iterations = newIterations;
  }
  public int getIterations() {
    return this.iterations;
  }
  
  //Number methods
  public void setNumber() {
    try {
      this.number = UserData.getNumber(getUserName(), this.plugin);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  public int getNumber() {
    return this.number;
  }
}
