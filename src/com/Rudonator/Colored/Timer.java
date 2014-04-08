package com.Rudonator.Colored;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable{

	private Main main;
	
	public Timer(Main main){
		this.main = main;
	}
	
	public void run() {
		for(int i = 0; i < main.hustle_players.size(); i++){
			Player player = main.hustle_players.get(i);
			main.changeColor(player.getInventory().getHelmet(), null);
			main.changeColor(player.getInventory().getChestplate(), null);
			main.changeColor(player.getInventory().getLeggings(), null);
			main.changeColor(player.getInventory().getBoots(), null);
		}
		
		for(int i = 0; i < main.full_players.size(); i++){
			Player player = main.full_players.get(i);
			Color color = null;
			if(player.getInventory().getHelmet() != null){
				color = ((LeatherArmorMeta) player.getInventory().getHelmet().getItemMeta()).getColor();
			}
			else if(player.getInventory().getChestplate() != null){
				color = ((LeatherArmorMeta) player.getInventory().getChestplate().getItemMeta()).getColor();
			}
			else if(player.getInventory().getLeggings() != null){
				color = ((LeatherArmorMeta) player.getInventory().getLeggings().getItemMeta()).getColor();
			}
			else if(player.getInventory().getBoots() != null){
				color = ((LeatherArmorMeta) player.getInventory().getBoots().getItemMeta()).getColor();
			}
			main.changeColor(player.getInventory().getHelmet(), main.getNextColor(color));
			main.changeColor(player.getInventory().getChestplate(), main.getNextColor(color));
			main.changeColor(player.getInventory().getLeggings(), main.getNextColor(color));
			main.changeColor(player.getInventory().getBoots(), main.getNextColor(color));
		}
		
		for(int i = 0; i < main.random_players.size(); i++){
			Player player = main.random_players.get(i);
			main.changeColor(player.getInventory().getHelmet(), main.getRandomColor());
			main.changeColor(player.getInventory().getChestplate(), main.getRandomColor());
			main.changeColor(player.getInventory().getLeggings(), main.getRandomColor());
			main.changeColor(player.getInventory().getBoots(), main.getRandomColor());
		}
	}
}
