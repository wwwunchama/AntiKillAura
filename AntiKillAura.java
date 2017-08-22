/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antikillaura;

import java.util.Date;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;

public class AntiKillAura implements CommandExecutor {
  public final String prefix = ChatColor.DARK_RED + "[" + ChatColor.AQUA + "MoguAntiCheat" + ChatColor.DARK_RED + "] " + ChatColor.GREEN;
  public final String hack = " for hacking";
  
  public boolean onCommand(CommandSender sender, Command cmd, String Commandlabel, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("report")) {
      if ((sender instanceof Player))
      {
        String myString = "";
        for (int i = 0; i < args.length; i++)
        {
          String arg = args[i] + " ";
          myString = myString + arg;
        }
        Player p = (Player)sender;
        if (p.hasPermission("report.aura"))
        {
          if (args.length == 0)
          {
            p.sendMessage(this.prefix + ChatColor.RED + "Usage:" +
            		" /report <player name> [reason]");
            return true;
          }
      	if(!args[0].equalsIgnoreCase(p.getName())){
          Date date = new Date();
          @SuppressWarnings("deprecation")
	 Player target = p.getServer().getPlayerExact(args[0]);
          Player online = (Player)sender;
          if (target != null)
          {
            p.sendMessage(ChatColor.RED + "reportを受け付けました。 あなたの情報を参考に対処します。");
             Pig pig = (Pig) target.getLocation().getWorld().spawnEntity(target.getLocation().add(0,5,0), EntityType.PIG);
             pig.setCustomName(ChatColor.RED + "WatchDog");
             pig.setCustomNameVisible(true);
             pig.setMaximumNoDamageTicks(0);
             pig.setNoDamageTicks(0);
             pig.setHealth(1);
            if (online.hasPermission("report.view")) {
                online.sendMessage(this.prefix + ChatColor.GREEN + p.getName() + ChatColor.GOLD + 
                " さんが " + ChatColor.GREEN + target.getName() + ChatColor.AQUA + 
                myString.replace(target.getName(), "") + ChatColor.GREEN + " を " + ChatColor.GOLD + date + ChatColor.GREEN  + "の理由でreportしたためWatchdogを付けました。");
            }
          }
          else
          {
            sender.sendMessage(this.prefix + args[0] + " はオンラインではありません。");
          }
          return true;
        }else{
        	p.sendMessage(this.prefix+ "申し訳ありませんが、あなた自身は報告できません。");
        }
      }else{
        p.sendMessage(this.prefix + ChatColor.RED  + "あなたはreportを行う権限を持っていません。");
      }
        return true;
      }else{
    	  System.out.println("プレイヤーだけがこのコマンドを使用できます。");
      }
    }
    return false;
  }
}
