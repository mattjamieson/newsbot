package uk.co.newsbot.handlers.impl;

import uk.co.newsbot.formatters.ItemFormat;
import uk.co.newsbot.formatters.ItemFormatter;
import uk.co.newsbot.handlers.StoryHandler;
import de.nava.informa.core.ItemIF;

/**
 * StoryHandler implementation to publish stories to
 * Stdout.
 */
public class Stdout implements StoryHandler
{
    /**
     * Publish an Item to Stdout.
     * @param item The item to publish.
     */
    public void publishStory(ItemIF item)
    {
        System.out.println(ItemFormatter.format(ItemFormat.DEFAULT, item));
    }
}