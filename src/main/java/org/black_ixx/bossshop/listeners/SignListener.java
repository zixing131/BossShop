package org.black_ixx.bossshop.listeners;
import java.util.HashMap;
import org.black_ixx.bossshop.BossShop;
import org.black_ixx.bossshop.core.BSShop;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener{

	public SignListener(boolean s, BossShop plugin){
		this.s=s;
		this.plugin=plugin;
	}

	private boolean s;

	private BossShop plugin;



	private BSShop getBossShopSign(String line){

		if (line==null||line==""){
			return null;
		}
		line=line.toLowerCase();
		HashMap<Integer, BSShop>  set = plugin.getClassManager().getShops().getShops();

		for (Integer s : set.keySet() ){

			if (line.endsWith(set.get(s).getSignText().toLowerCase())){
				return set.get(s);
			}


		}

		return null;
	}

	@EventHandler
	public void createSign(SignChangeEvent e){

		if (!s){
			return;
		}

		final BSShop shop = getBossShopSign(e.getLine(0));
		if (shop!=null){

			if (shop.needPermToCreateSign()){


				if (!e.getPlayer().hasPermission("BossShop.createSign")){

					plugin.getClassManager().getMessageHandler().sendMessage("Main.NoPermission", e.getPlayer());
					e.setCancelled(true);
					return;
				}
			}

			if (e.getLine(0)!=""){
				e.setLine(0, plugin.getClassManager().getStringManager().transform(e.getLine(0), null, shop, null));
			}
			if (e.getLine(1)!=""){
				e.setLine(1, plugin.getClassManager().getStringManager().transform(e.getLine(1), null, shop, null));
			}
			if (e.getLine(2)!=""){
				e.setLine(2, plugin.getClassManager().getStringManager().transform(e.getLine(2), null, shop, null));
			}
			if (e.getLine(3)!=""){
				e.setLine(3, plugin.getClassManager().getStringManager().transform(e.getLine(3), null, shop, null));
			}


		}
	}


	@EventHandler
	public void interactSign(PlayerInteractEvent e){
		if (!s){
			return;
		}

		if (e.getClickedBlock()!=null){
			if (e.getAction()==Action.RIGHT_CLICK_BLOCK){

				Block b = e.getClickedBlock();
				if (b.getType()==Material.SIGN|| b.getType()==Material.SIGN_POST|| b.getType()==Material.WALL_SIGN){

					if (b.getState() instanceof Sign){
						Sign s = (Sign)b.getState();

						BSShop shop = getBossShopSign(s.getLine(0));
						if (shop!=null){

							if (e.getPlayer().hasPermission("BossShop.open") || e.getPlayer().hasPermission("BossShop.open.sign") || e.getPlayer().hasPermission("BossShop.open.sign."+shop.getShopName())){
								plugin.getClassManager().getShops().openShop(e.getPlayer(), shop);								
								return;
							}

							plugin.getClassManager().getMessageHandler().sendMessage("Main.NoPermission", e.getPlayer());							
							return;
						}

					}		
				}
			}
		}
	}


	public void setSignsEnabled(boolean b){
		s=b;
	}





}
