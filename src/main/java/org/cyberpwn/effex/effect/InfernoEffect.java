package org.cyberpwn.effex.effect;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.phantomapi.nms.NMSX;
import org.phantomapi.sync.Task;
import org.phantomapi.vfx.ParticleEffect;
import org.phantomapi.world.Shape;

public class InfernoEffect
{
	private Float power;
	
	public InfernoEffect(Float power)
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
					NMSX.breakParticles(s.randomLocation(), Material.LAVA, 2);
					ParticleEffect.LAVA.display(0f, 1, s.randomLocation(), 24);
				}
				
				k[0]++;
			}
		};
	}
}
