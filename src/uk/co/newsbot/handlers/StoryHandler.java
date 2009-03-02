package uk.co.newsbot.handlers;

import de.nava.informa.core.ItemIF;

/**
 * Interface implemented by all StoryHandlers.
 */
public interface StoryHandler
{
	/**
	 * Publish an item via whatever method the
	 * implementing StoryHandler is written to use.
	 * @param item The item to publish.
	 */
    void publishStory(ItemIF item);
}