package uk.co.newsbot.formatters;

import de.nava.informa.core.ItemIF;

/**
 * Class to format an ItemIF object as text, before it is
 * sent to a StoryHandler.
 */
public class ItemFormatter
{
    /**
     * Format an ItemIF according to the specified format.
     * @param format The (integer) ID of the format to use.
     * 					These are specified as static fields
     * 					towards the top of this file. 
     * @param item The Item to format (implements ItemIF).
     * @return The formatted String.
     */
    public static String format(ItemFormat format, ItemIF item)
    {
        switch (format)
        {
            case IRC:
                return "[" + item.getChannel().getTitle() + "] " + item.getTitle() + " (" + item.getLink() + ")";
            case DEFAULT:
            default:
                return item.toString();
        }
    }
}