package org.cyberpwn.effex;

import org.phantomapi.construct.Ghost;
import org.phantomapi.core.SyncStart;

@SyncStart
public class Effex extends Ghost
{
	private EffexController effexController;
	
	@Override
	public void preStart()
	{
		effexController = new EffexController(this);
		
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
