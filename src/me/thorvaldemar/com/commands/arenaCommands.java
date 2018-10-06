package me.thorvaldemar.com.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.thorvaldemar.com.main;

public class arenaCommands implements CommandExecutor {

	static main plugin;

	public arenaCommands(main pl) {
		plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("arena")) {
			Player p = null;
			if (sender instanceof Player) {
				p = ((Player) sender).getPlayer();
			} else {
				return false;
			}

			if (args.length == 3) {
//				if (args[0].equalsIgnoreCase("help")) {
//					helpCC(p, 1);
//				} else {
//					p.sendMessage(ChatColor.RED + "Wrong argument \"" + args[0] + "\", try \"/arena help\" for more help");
//				}
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("help")) {
					try {
						helpCC(p, Integer.parseInt(args[1]));
					} catch (NumberFormatException nfe) {
						p.sendMessage(ChatColor.RED + "The argument " + ChatColor.DARK_RED + args[1] + ChatColor.RED
								+ " need to be a number");
					}
				} else {
					p.sendMessage("Wrong argument \"" + args[0] + "\", try \"/arena help\" for more help");
				}
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("leavequeue")) {
					plugin.joinFight(p);
				} else if (args[0].equalsIgnoreCase("joinqueue")) {
					plugin.joinFight(p);
				} else if (args[0].equalsIgnoreCase("help")) {
					helpCC(p, 1);
				} else {
					p.sendMessage(
							ChatColor.RED + "Wrong argument \"" + args[0] + "\", try \"/arena help\" for more help");
				}
			} else if (args.length == 0) {
				helpCC(p, 1);
			} else {
				p.sendMessage(ChatColor.RED + "To many arguments. \"/arena help\" for more help");
			}
		}
		return false;
	}

	void helpCC(Player p, int site) {
		ArrayList<String> normalCommands = new ArrayList<String>();
		ArrayList<String> adminCommands = new ArrayList<String>();
		ArrayList<String> pCommands = new ArrayList<String>();

		/* Normal Commands */
		normalCommands.add("help [number] /To get more information about the command/");
		normalCommands.add("joinqueue [mode] /Set a fight in queue. Mode can be single or double/");
		normalCommands.add("leavequeue /To leave a ongoing queue/");

		/* Admin Commands */
		adminCommands.add("createarena [name] /To create an arena, and set a name for it/");
		adminCommands.add("setspawnpoint [arena] /Set a spawnpoit to an arena/");
		adminCommands.add("getspawnpoints [arena] /Get all spawnpoints for an arena/");
		adminCommands.add("removespawnpoint [arena] [spawnpoint] /Removes a spawnpoint from an arena/");
		adminCommands.add("removearena [arena] /Removes an arena/");

		int plusser = 0;

		for (String s : normalCommands)
			pCommands.add(s);

		if (p.isOp()) {
			for (String s : adminCommands)
				pCommands.add(s);
		}

		int commandCount = pCommands.size();
		int maxCommandsPerSite = 5;

		if ((commandCount % maxCommandsPerSite) > 0)
			plusser = 1;

		if (site > (commandCount / maxCommandsPerSite + plusser)) {
			p.sendMessage(ChatColor.RED + "The site " + ChatColor.DARK_RED + site + ChatColor.RED + " doesn't exist");
			return;
		}

		p.sendMessage(ChatColor.AQUA + "Help site " + site + "/" + (commandCount / maxCommandsPerSite + plusser));
		for (int i = 0 + (maxCommandsPerSite * (site - 1)); i < maxCommandsPerSite * site && i < commandCount; i++) {
			p.sendMessage(
					ChatColor.GREEN + " - " + pCommands.get(i).replaceAll("/", ChatColor.RED + "\"" + ChatColor.GOLD));
		}
	}
}