package uk.co.newsbot.handlers.impl;

import org.jibble.pircbot.PircBot;

import uk.co.newsbot.formatters.ItemFormat;
import uk.co.newsbot.formatters.ItemFormatter;
import uk.co.newsbot.handlers.StoryHandler;
import de.nava.informa.core.ItemIF;

/**
 * StoryHandler implementation to publish stories to
 * an IRC channel.
 */
public class IRC extends PircBot implements StoryHandler
{
    private String channel;
    
    /**
     * Static method to start the IRC StoryHandler.
     * @param channel The channel to publish stories to.
     * @param nickname The nickname to use to connect to IRC.
     * @param server The server to connect to.
     * @return The IRC StoryHandler object created.
     * @throws Exception If the underlying IRC implementation
     * 						(PircBot) throws an exception.
     */
    public static IRC startUp(String channel, String nickname, String server) throws Exception
    {
        IRC bot = new IRC(channel, nickname);
        
        bot.setVerbose(true);
        bot.connect(server);
        bot.joinChannel(channel);
        
        return bot;
    }
    
    /**
     * Construct an IRC StoryHandlerIF implementation for the
     * specified channel, using the specified nickname.
     * @param channel The channel to publish stories to.
     * @param nickname The nickname to use to connect to IRC.
     */
    private IRC(String channel, String nickname)
    {
    	this.channel = channel;
    	
        setName(nickname);
    }

    /**
     * Publish an Item via IRC.
     * @param item The item to publish.
     */
    public void publishStory(ItemIF item)
    {
        sendMessage(channel, ItemFormatter.format(ItemFormat.IRC, item));
    }
}