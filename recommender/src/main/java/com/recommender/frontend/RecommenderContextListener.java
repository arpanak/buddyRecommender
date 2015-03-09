package com.recommender.frontend;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.BasicConfigurator;
import com.recommender.core.LuceneHelper;
import com.recommender.core.Recommender;

public class RecommenderContextListener implements ServletContextListener
{

	public void contextDestroyed(ServletContextEvent arg0)
	{
		LuceneHelper.close();
	}

	public void contextInitialized(ServletContextEvent arg0)
	{
		BasicConfigurator.configure();
		Recommender.initializeRecommender();
	}

}
