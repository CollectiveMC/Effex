package org.cyberpwn.effex.effect;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.phantomapi.nms.NMSX;
import org.phantomapi.sync.Task;
import org.phantomapi.world.Shape;

public class FrostEffect
{
	private Float power;
	
	public FrostEffect(Float power)
	{
		this.power = power;
	}
	
	public void play(Location l, Vector dir, Location bx)
	{
		int[] k = new int[]{0};
		
		Shape s = new Shape(bx.clone().add(Math.random() - 0.5, -1.8, Math.random() - 0.5), new Vector(0.4, 1.4, 0.4));
		
		new Task(0)
		{
			@Override
			public void run()
			{
				if(k[0] > 2)
				{
					cancel();
				}
				
				for(int j = 0; j < 2 * power; j++)
				{
					NMSX.breakParticles(s.randomLocation(), Material.SNOW_BLOCK, 2);
					NMSX.breakParticles(s.randomLocation(), Material.ICE, 2);
				}
				
				k[0]++;
			}
		};
	}
}
