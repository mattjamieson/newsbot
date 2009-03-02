package uk.co.newsbot.test;

import java.io.PrintWriter;
import java.net.URL;

import de.nava.informa.core.ChannelExporterIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.exporters.RSS_0_91_Exporter;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.FeedParser;

public class InformaTest
{
	public static void main(String[] args)
	{
		try
		{
			URL url = new URL("http://news.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss091.xml");
			ChannelIF channel = FeedParser.parse(new ChannelBuilder(), url);
			
			ChannelExporterIF exporter = new RSS_0_91_Exporter(new PrintWriter(System.out), "");
			//ChannelExporterIF exporter = new RSS_1_0_Exporter(new PrintWriter(System.out), "");
			//ChannelExporterIF exporter = new RSS_2_0_Exporter(new PrintWriter(System.out), "");
			exporter.write(channel);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}