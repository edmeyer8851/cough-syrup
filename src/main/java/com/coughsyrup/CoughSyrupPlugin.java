package com.coughsyrup;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.callback.ClientThread;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.*;

@Slf4j
@PluginDescriptor(
		name = "Cough Syrup",
		description = "Removes player cough spam in public chat during Nex's Choke attack."
)
public class CoughSyrupPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	private static final String COUGH_TEXT = "*Cough*";

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage)
	{
		if (chatMessage.getType() != ChatMessageType.PUBLICCHAT)
		{
			return;
		}
		final ChatLineBuffer lineBuffer = client.getChatLineMap().get(ChatMessageType.PUBLICCHAT.getType());
		if (lineBuffer == null)
		{
			return;
		}
		if (COUGH_TEXT.equals(chatMessage.getMessage()))
		{
			lineBuffer.removeMessageNode(chatMessage.getMessageNode());
			clientThread.invoke(() -> client.runScript(ScriptID.BUILD_CHATBOX));
		}
	}

}
