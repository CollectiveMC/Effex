package org.cyberpwn.effex.effect;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import org.phantomapi.nms.NMSX;
import org.phantomapi.sync.Task;
import org.phantomapi.vfx.LineParticleManipulator;

public class TaintEffect
{
	private Float power;
	
	public TaintEffect(Float power)
	{
		this.power = power;
	}
	
	public void play(Location l, Vector dir, Location bx)
	{
		int[] k = new int[] {0};
		
		new Task(0)
		{
			@Override
			public void run()
			{
				if(k[0] > 2)
				{
					cancel();
				}
				
				for(int j = 0; j < 3; j++)
				{
					Vector direction = dir.clone();
					Location start = l.clone();
					
					for(float i = 0; i < Math.abs(power); i += 0.93f)
					{
						direction.add(new Vector(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5).multiply(Math.random() * 8 * i));
						Location b = start.clone().add(direction);
						getArm().play(start, b.clone(), ((double) (1 + (power - i)) / 4));
						start = b;
					}
				}
				
				k[0]++;
			}
		};
	}
	
	public LineParticleManipulator getArm()
	{
		return new LineParticleManipulator()
		{
			@Override
			public void play(Location l)
			{
				if(Math.random() > 0.2)
				{
					NMSX.breakParticles(l, Material.SNOW_BALL, 1);
				}
			}
		};
	}
}
