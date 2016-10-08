package org.cyberpwn.effex;

import org.phantomapi.construct.Ghost;

public class Effex extends Ghost
{
	private EffexController effexController;
	
	@Override
	public void preStart()
	{
		this.effexController = new EffexController(this);
		
		register(effexController);
	}

	@Override
	public void onStart()
	{
		
	}

	@Override
	public void onStop()
	{
		
	}

	@Override
	public void postStop()
	{
		
	}
}
