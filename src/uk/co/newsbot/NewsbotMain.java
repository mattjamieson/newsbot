package uk.co.newsbot;

import java.net.URL;

import uk.co.newsbot.handlers.StoryHandler;
import uk.co.newsbot.handlers.impl.IRC;
import uk.co.newsbot.handlers.impl.Stdout;
import uk.co.newsbot.handlers.impl.StoryHandlerCollection;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.impl.basic.ChannelGroup;
import de.nava.informa.parsers.FeedParser;

/**
 * Main class for NewsBot. Builds a ChannelGroup of
 * Channels to monitor, and a StoryHandlerCollection
 * containing the IRC and Stdout StoryHandlers.
 */
public class NewsbotMain
{
	public static void main(String[] args)
	{
	    try
	    {
	        ChannelIF channel = FeedParser.parse(new ChannelBuilder(),
	        									 new URL("http://news.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss091.xml"));
	        
	        ChannelGroup channels = new ChannelGroup();
	        channels.add(channel);
	        
	        StoryHandler handler = new StoryHandlerCollection(IRC.startUp("#newsbot-test", "Newsbot", "localhost"),
	        											      new Stdout());
		    
		    new Newsbot(channels, handler);
	    }
	    catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
}