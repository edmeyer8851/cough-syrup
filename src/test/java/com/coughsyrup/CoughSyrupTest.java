package com.coughsyrup;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CoughSyrupTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CoughSyrupPlugin.class);
		RuneLite.main(args);
	}
}