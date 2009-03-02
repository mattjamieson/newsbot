package uk.co.newsbot.test;

import java.net.URL;

import uk.co.newsbot.handlers.StoryHandler;
import uk.co.newsbot.handlers.impl.Stdout;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.FeedParser;

public class StoryHandlerTest
{
    public static void main(String[] args)
    {
        StoryHandler handler = new Stdout();
        
        try {
            URL url = new URL("http://news.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss091.xml");
            ChannelIF channel = FeedParser.parse(new ChannelBuilder(), url);
            
            for (ItemIF item : channel.getItems())
            {
                handler.publishStory(item);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}