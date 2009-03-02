package uk.co.newsbot.handlers.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import uk.co.newsbot.handlers.StoryHandler;
import de.nava.informa.core.ItemIF;

/**
 * StoryHandlerIF implementation to publish stories to
 * multiple places (via other StoryHandlers).
 */
public class StoryHandlerCollection implements StoryHandler
{
    private Collection<StoryHandler> handlers;
    
    /**
     * Construct a StoryHandlerCollection containing the
     * specified StoryHandlers.
     * @param handlers A collection of handlers to use.
     */
    public StoryHandlerCollection(Collection<StoryHandler> handlers)
    {
        this.handlers = Collections.unmodifiableCollection(handlers);
    }
    
    public StoryHandlerCollection(StoryHandler... handlers)
    {
    	this(Arrays.asList(handlers));
    }
    
    /**
     * Publish an Item via each of the StoryHandlers in
     * the collection.
     * @param item The item to publish.
     */
    public void publishStory(ItemIF item)
    {
        for (StoryHandler handler : handlers)
        {
            handler.publishStory(item);
        }
    }
}