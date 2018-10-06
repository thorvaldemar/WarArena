package me.thorvaldemar.com;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.thorvaldemar.com.commands.arenaCommands;
import me.thorvaldemar.com.events.PlayerLeave;
import me.thorvaldemar.com.loops.CounterLoop;
import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin {

	public HashMap<Player, Integer> arenaJoiners = new HashMap<Player, Integer>();

	CounterLoop counterLoop;

	public void onEnable() {

		counterLoop = new CounterLoop(this);
		counterLoop.runTaskTimer(this, 0, 20);

		registerCommands();
		registerEvents();
		registerConfig();

	}

	public void onDisable() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (arenaJoiners.containsKey(p))
				leaveFight(p);
		}
	}

	public void joinFight(Player p) {
		arenaJoiners.put(p, 0);
		p.sendMessage(ChatColor.DARK_RED + "!" + ChatColor.GOLD + " You've queued a fight " + ChatColor.DARK_RED + "!");
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (p != players) {
				players.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "! ARENA ! " + ChatColor.YELLOW + p.getName()
						+ " just joined queue, \"/arena queuefight\" to join him");
			}
		}
	}

	public void leaveFight(Player p) {
		arenaJoiners.remove(p);
		p.sendMessage(ChatColor.DARK_RED + "!" + ChatColor.RED + " You've leaved queue " + ChatColor.DARK_RED + "!");
		for (Player players : Bukkit.getOnlinePlayers()) {
			if (p != players) {
				players.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "! ARENA ! " + ChatColor.RED + p.getName()
						+ " just leaves queue");
			}
		}
	}

	public void registerCommands() {
		// getCommand("bct").setExecutor(new broadcastTitle());
		getCommand("arena").setExecutor(new arenaCommands(this));

	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerLeave(this), this);

	}

	public void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

}