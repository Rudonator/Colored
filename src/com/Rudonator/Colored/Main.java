package com.Rudonator.Colored;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private Random random = new Random();

	public List<Player> hustle_players;
	public List<Player> full_players;
	public List<Player> random_players;
	public Color[] colors = 
		{Color.SILVER, Color.GRAY, 
		Color.BLACK, Color.RED, 
		Color.MAROON, Color.YELLOW, 
		Color.OLIVE, Color.LIME, 
		Color.GREEN, Color.AQUA,
		Color.TEAL, Color.BLUE, 
		Color.NAVY, Color.FUCHSIA, 
		Color.PURPLE, Color.ORANGE, 
		Color.WHITE};
	
	@SuppressWarnings("unused")
	private int timer = 0;
	private int SECONDS = 1;
	
	@Override
	public void onEnable() {
		timer = getServer().getScheduler().scheduleSyncRepeatingTask(this, new Timer(this), 20L, SECONDS * 20);
		hustle_players = new ArrayList<Player>();
		full_players = new ArrayList<Player>();
		random_players = new ArrayList<Player>();
	}
	
	@Override
	public void onDisable() {
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("rainbow")){
			Player player = (Player)sender;
			if(args.length > 1){
				Player p = Bukkit.getPlayer(args[1]);
				if(p != null){
					player = p;
				}
				else{
					player.sendMessage(ChatColor.GOLD + "Player " + ChatColor.RED + args[1] + ChatColor.GOLD + " is not online.");
					return false;
				}
			}
			if(args.length > 0){
				if(args[0].equalsIgnoreCase("hustle")){
					removePlayer(player);
					player.sendMessage(ChatColor.GOLD + "Hustle Color mode");
					changeColor(player.getInventory().getHelmet(), Color.WHITE);
					changeColor(player.getInventory().getChestplate(), Color.GREEN);
					changeColor(player.getInventory().getLeggings(), Color.PURPLE);
					changeColor(player.getInventory().getBoots(), Color.AQUA);
					hustle_players.add(player);
				}
				if(args[0].equalsIgnoreCase("full")){
					removePlayer(player);
					player.sendMessage(ChatColor.GOLD + "Full Color mode");
					full_players.add(player);
				}
				if(args[0].equalsIgnoreCase("random")){
					removePlayer(player);
					player.sendMessage(ChatColor.GOLD + "Random Color mode");
					random_players.add(player);
				}
			}
			else{
				if(hustle_players.contains(player) || full_players.contains(player) || random_players.contains(player)){
					hustle_players.remove(player);
					full_players.remove(player);
					random_players.remove(player);
					player.sendMessage(ChatColor.GOLD + "Disabled rainbow");
				}
				else{
					player.sendMessage(ChatColor.GOLD + "Usage: " + ChatColor.RED + "/rainbow [" + ChatColor.AQUA + "hustle" + ChatColor.RED + ", " + ChatColor.AQUA + "full" + ChatColor.RED + ", " + ChatColor.AQUA + "random" + ChatColor.RED + "] [" + ChatColor.AQUA + "player" + ChatColor.RED + "]");
				}
			}
		}
		return false;
	}
	
	private void removePlayer(Player player){
		hustle_players.remove(player);
		full_players.remove(player);
		random_players.remove(player);
	}
	
	public void changeColor(ItemStack itemstack, Color c){
		if(itemstack != null){
			LeatherArmorMeta m = (LeatherArmorMeta)itemstack.getItemMeta();
			Color color = getNextColor(m.getColor());
			if(c != null){
				color = c;
			}
			m.setColor(color);
			itemstack.setItemMeta(m);
		}
	}
	
	public Color getNextColor(Color now){
		for(int i = 0; i < colors.length; i++){
			Color n = colors[i];
			if(now.equals(n)){
				if(i < 16){
					return colors[i + 1];
				}
				else{
					return colors[0];
				}
			}
		}
		return now;
	}
	
	public Color getRandomColor(){
		int i = random.nextInt(17);
		return colors[i];
	}
}