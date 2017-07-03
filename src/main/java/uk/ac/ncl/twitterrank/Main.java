package uk.ac.ncl.twitterrank;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main {
	
	@Autowired
	private TwitterRank ranking;
	
	public void run() {
		ranking.computeForAllTopics();
	}
	
	protected static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("ranking-app-config.xml");
			Main main = (Main)context.getBean("main");
			main.run();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
