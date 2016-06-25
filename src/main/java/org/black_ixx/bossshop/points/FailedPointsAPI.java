package org.black_ixx.bossshop.points;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class FailedPointsAPI extends IPointsAPI {

	public FailedPointsAPI() {
		super("Failed");
	}

	public int getPoints(OfflinePlayer player) {
		informPlayer(player);
		return 0;
	}

	public int setPoints(OfflinePlayer player, int points) {
		informPlayer(player);
		return 0;
	}

	public int takePoints(OfflinePlayer player, int points) {
		informPlayer(player);
		return 0;
	}

	public int givePoints(OfflinePlayer player, int points) {
		informPlayer(player);
		return 0;
	}

	private void informPlayer(OfflinePlayer player) {
		Bukkit.getConsoleSender().sendMessage("PlayerPoints/CommandPoints was not found... " + "You need one of that plugins if you want to work with Points! " + "Get PlayerPoints there: " + "http://dev.bukkit.org/server-mods/playerpoints/");
		if(player.isOnline())
			if(player.isOp()){
				player.getPlayer().sendMessage("[BossShop] No Points Plugin installed. If you want to work with Points please install one.");
			}else{
				player.getPlayer().sendMessage("[BossShop] No Points Plugin installed. Please inform an administrator.");
			}
	}
}
