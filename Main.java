/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antikillaura;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
 public static Plugin plugin;
    public void onEnable() {
            plugin = this;
              
                saveDefaultConfig();
                
		if (getServer().getPluginManager().isPluginEnabled("PermissionsEx")){

		} else {
                        getServer().getLogger().info("前提プラグインが不足しています。");
                        getServer().getLogger().info("前提プラグインはPermissionEXです。");
			getServer().getPluginManager().disablePlugin(this);
		}
            getCommand("report").setExecutor(new AntiKillAura());
            getServer().getPluginManager().registerEvents((Listener) new Core(), (Plugin)this);  
            getServer().getPluginManager().registerEvents(this, this);
	    	}
	public void onDisable(){
        }   
}
