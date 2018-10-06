package me.thorvaldemar.com.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.thorvaldemar.com.main;

public class PlayerLeave implements Listener {
	
	private main plugin;
	
	public PlayerLeave(main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onPlayerLeaves(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if (plugin.arenaJoiners.containsKey(p)) {
			plugin.leaveFight(p);
		}
	}
	
}
