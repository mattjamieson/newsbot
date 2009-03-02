package uk.co.newsbot;

import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.newsbot.handlers.StoryHandler;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.impl.basic.ChannelGroup;
import de.nava.informa.parsers.FeedParser;

/**
 * Core NewsBot class.  Constructed with a ChannelGroup
 * of channels (RSS feeds) and a StoryHandler, it will
 * check the channels every 60 seconds - which is probably
 * too frequently - for new stories. Any it finds are
 * passed to the publishStory method of the StoryHandler. 
 */
public class Newsbot
{
	private ChannelGroup channels;
	private StoryHandler handler;
	
	/**
	 * Construct a Newsbot object.
	 * @param channels The channels to monitor for new stories.
	 * @param handler An implementation of StoryHandlerIF to
	 * 			publish new items to.
	 */
	public Newsbot(ChannelGroup channels, StoryHandler handler)
	{
	    this.channels = channels;  // Should probably take a copy of the ChannelGroup - it isn't immutable
	    this.handler = handler;
	    
	    TimerTask tt = new TimerTask()
        {
            public void run()
            {
                harvestNews();
            }
        };
        
        new Timer().schedule(tt, 60*1000, 60*1000);
    }
	
	/**
	 * Method called every 60 seconds by the Timer, to check
	 * for new stories.
	 */
	private void harvestNews()
	{
	    ChannelGroup newChannels = new ChannelGroup();
	    
	    for (Object object : channels.getAll())
        {
            ChannelIF oldChannel = (ChannelIF)object;
            Set<ItemIF> oldChannelItems = oldChannel.getItems();
            
            try
            {
                ChannelIF newChannel = FeedParser.parse(new ChannelBuilder(),
                										oldChannel.getLocation());
                
                Set<ItemIF> newChannelItems = newChannel.getItems();
                
                for (ItemIF item : newChannelItems)
                {
                    if (! oldChannelItems.contains(item))
                        handler.publishStory(item);
                }
                
                newChannels.add(newChannel);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        channels = newChannels;
	}
}