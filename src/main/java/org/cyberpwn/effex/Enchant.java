package org.cyberpwn.effex;

public class Enchant
{
	private Enchanted e;
	private int level;
	
	public Enchant(Enchanted e, int level)
	{
		this.e = e;
		this.level = level;
	}
	
	public Enchanted getE()
	{
		return e;
	}
	
	public void setE(Enchanted e)
	{
		this.e = e;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
}
