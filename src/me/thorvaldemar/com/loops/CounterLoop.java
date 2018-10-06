package me.thorvaldemar.com.loops;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.thorvaldemar.com.main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CounterLoop extends BukkitRunnable {

	static main plugin;

	public CounterLoop(main pl) {
		plugin = pl;
	}

	public void run() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (plugin.arenaJoiners.containsKey(p)) {
				int timeIA = plugin.arenaJoiners.get(p);
				int min = timeIA/60;
				int sec = timeIA%60;
				
				String minstr = min + "";
				String secstr = sec + "";
				
				if (minstr.length() < 2)
					minstr = "0" + minstr;
				if (secstr.length() < 2)
					secstr = "0" + secstr;
				
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§2§lQueue time: " + minstr + ":" + secstr));
				
				plugin.arenaJoiners.put(p, timeIA+1);
			}
		}
	}

}
