package sample_app.initialization;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener for initializing Sample App initial configuration
 *
 */
public class ConfigurationInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		initializeConfiguration();
	}

	private void initializeConfiguration() {
		new DBInitializer().initData();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// empty block
	}

}
