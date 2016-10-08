package org.cyberpwn.effex;

import org.bukkit.Material;
import org.phantomapi.lang.GList;

public class Matte
{
	public static Material[] concat(Material[]... mattes)
	{
		GList<Material> matts = new GList<Material>();
		
		for(Material[] i : mattes)
		{
			matts.add(i);
		}
		
		matts.removeDuplicates();
		
		return matts.toArray(new Material[matts.size()]);
	}
	
	public static Material[] axes()
	{
		return new Material[] {Material.GOLD_AXE, Material.IRON_AXE, Material.STONE_AXE, Material.WOOD_AXE, Material.DIAMOND_AXE};
	}
	
	public static Material[] bow()
	{
		return new Material[] {Material.BOW};
	}
	
	public static Material[] tnt()
	{
		return new Material[] {Material.TNT};
	}
	
	public static Material[] swords()
	{
		return new Material[] {Material.GOLD_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD, Material.DIAMOND_SWORD};
	}
	
	public static Material[] armor()
	{
		return concat(helmets(), chestplates(), leggings(), boots());
	}
	
	public static Material[] helmets()
	{
		return new Material[] {Material.GOLD_HELMET, Material.IRON_HELMET, Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.DIAMOND_HELMET};
	}
	
	public static Material[] chestplates()
	{
		return new Material[] {Material.GOLD_CHESTPLATE, Material.IRON_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE};
	}
	
	public static Material[] boots()
	{
		return new Material[] {Material.GOLD_BOOTS, Material.IRON_BOOTS, Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.DIAMOND_BOOTS};
	}
	
	public static Material[] leggings()
	{
		return new Material[] {Material.GOLD_LEGGINGS, Material.IRON_LEGGINGS, Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_LEGGINGS};
	}
	
	public static Material[] shovels()
	{
		return new Material[] {Material.GOLD_SPADE, Material.IRON_SPADE, Material.STONE_SPADE, Material.WOOD_SPADE, Material.DIAMOND_SPADE};
	}
	
	public static Material[] hoes()
	{
		return new Material[] {Material.GOLD_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.WOOD_HOE, Material.DIAMOND_HOE};
	}
	
	public static Material[] fishing()
	{
		return new Material[] {Material.FISHING_ROD};
	}
	
	public static Material[] shears()
	{
		return new Material[] {Material.SHEARS};
	}
	
	public static Material[] pickaxes()
	{
		return new Material[] {Material.GOLD_PICKAXE, Material.IRON_PICKAXE, Material.STONE_PICKAXE, Material.WOOD_PICKAXE, Material.DIAMOND_PICKAXE};
	}
}
