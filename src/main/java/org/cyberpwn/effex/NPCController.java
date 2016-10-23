package org.cyberpwn.effex;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.phantomapi.clust.ConfigurableController;
import org.phantomapi.command.Command;
import org.phantomapi.command.CommandFilter.ArgumentRange;
import org.phantomapi.command.CommandFilter.Permission;
import org.phantomapi.command.CommandFilter.PlayerOnly;
import org.phantomapi.command.PhantomCommand;
import org.phantomapi.command.PhantomSender;
import org.phantomapi.construct.Controllable;
import org.phantomapi.util.C;
import org.phantomapi.world.W;
import org.phantomapi.wraith.FocusAIController;
import org.phantomapi.wraith.GUIAIController;
import org.phantomapi.wraith.PhantomWraith;
import org.phantomapi.wraith.Wraith;
import org.phantomapi.wraith.WraithEquipment;

public class NPCController extends ConfigurableController
{
	private Wraith enchanter;
	
	public NPCController(Controllable parentController)
	{
		super(parentController, "npc-spawns");
		
		enchanter = new PhantomWraith(C.LIGHT_PURPLE + "Enchanter", this);
	}
	
	@Override
	public void onStart()
	{
		loadCluster(this);
		
		spawnEnchanter();
	}
	
	private void spawnEnchanter()
	{
		Location l = Bukkit.getWorlds().get(0).getSpawnLocation();
		
		if(getConfiguration().contains("enchanter.x"))
		{
			l = new Location(Bukkit.getWorld(getConfiguration().getString("enchanter.world")), getConfiguration().getInt("enchanter.x"), getConfiguration().getInt("enchanter.y"), getConfiguration().getInt("enchanter.z"));
		}
		
		enchanter.spawn(l);
		enchanter.setAggressive(false);
		
		ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leggs = new ItemStack(Material.LEATHER_LEGGINGS);
		W.colorArmor(chest, Color.BLACK);
		W.colorArmor(leggs, Color.BLACK);
		enchanter.setEquipment(WraithEquipment.CHESTPLATE, chest);
		enchanter.setEquipment(WraithEquipment.LEGGINGS, leggs);
		enchanter.setEquipment(WraithEquipment.HAND, new ItemStack(Material.ENCHANTED_BOOK));
		
		new GUIAIController(enchanter)
		{
			@Override
			public void onUnbind()
			{
				
			}
			
			@Override
			public void onTick()
			{
				
			}
			
			@Override
			public void onBind()
			{
				
			}
			
			@Override
			public void onInterfaceLaunch(Wraith wraith, Player p)
			{
				EffexController.inst.openWindow(p);
			}
		};
		
		new FocusAIController(enchanter, 6.77)
		{
			@Override
			public void onFocusChanged(Player player)
			{
				enchanter.say("Hello again " + player.getName(), player);
			}
		};
	}
	
	@Override
	public void onStop()
	{
		enchanter.destroy();
	}
	
	@ArgumentRange({0, 0})
	@PlayerOnly
	@Permission("enchanter.god")
	@Command("enchanter-spawn")
	public void onEnchanterSpawn(PhantomSender sender, PhantomCommand cmd)
	{
		getConfiguration().set("enchanter.world", sender.getPlayer().getLocation().getWorld().getName());
		getConfiguration().set("enchanter.x", sender.getPlayer().getLocation().getBlockX());
		getConfiguration().set("enchanter.y", sender.getPlayer().getLocation().getBlockY());
		getConfiguration().set("enchanter.z", sender.getPlayer().getLocation().getBlockZ());
		enchanter.teleport(sender.getPlayer().getLocation().getBlock().getLocation());
		saveCluster(this);
	}
}
