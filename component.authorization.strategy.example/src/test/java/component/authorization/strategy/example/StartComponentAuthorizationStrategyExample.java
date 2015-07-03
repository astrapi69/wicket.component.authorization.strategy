package component.authorization.strategy.example;

import java.io.File;

import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.util.time.Duration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jetty9.runner.Jetty9Runner;
import de.alpharogroup.jetty9.runner.config.FilterHolderConfiguration;
import de.alpharogroup.jetty9.runner.config.Jetty9RunConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletContextHandlerConfiguration;
import de.alpharogroup.jetty9.runner.config.ServletHolderConfiguration;

//import org.eclipse.jetty.server.Connector;
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.server.nio.SelectChannelConnector;
//import org.eclipse.jetty.webapp.WebAppContext;

public class StartComponentAuthorizationStrategyExample {
    public static void main(String[] args) throws Exception {

		int sessionTimeout = (int)Duration.minutes(1).seconds();// set timeout to 30min(60sec *
																	// 30min=1800sec)...
		System.setProperty("wicket.configuration", "development");
		String projectname = "component.authorization.strategy.example";
		File projectDirectory = PathFinder.getProjectDirectory();
		File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src", "main",
			"webapp");
		String filterPath = "/*";
		// Add a file appender to the logger programatically
		// Logger logger = org.apache.log4j.LogManager.getLogger("org.eclipse.jetty");
//		Logger.getRootLogger().addFileAppender(newFileAppender("./application.log"));
		ServletContextHandler servletContextHandler = Jetty9Runner
			.getNewServletContextHandler(ServletContextHandlerConfiguration
				.builder()
				.filterHolderConfiguration(
					FilterHolderConfiguration
						.builder()
						.filterClass(WicketFilter.class)
						.filterPath(filterPath)
						.initParameter(WicketFilter.FILTER_MAPPING_PARAM, filterPath)
						.initParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
							WicketApplication.class.getName()).build())
				.servletHolderConfiguration(
					ServletHolderConfiguration.builder().servletClass(DefaultServlet.class)
						.pathSpec(filterPath).build()).contextPath("/").webapp(webapp)
				.maxInactiveInterval(sessionTimeout).filterPath(filterPath).build());

		Jetty9RunConfiguration config = Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(WicketApplication.DEFAULT_HTTP_PORT)
			.httpsPort(WicketApplication.DEFAULT_HTTPS_PORT).keyStorePassword("wicket")
			.keyStorePathResource("/keystore").build();
		Server server = new Server();
		Jetty9Runner.run(server, config);
    	
//    	final Server server = new Server();
//		final SelectChannelConnector connector = new SelectChannelConnector();
//		// Set some timeout options to make debugging easier.
//		connector.setMaxIdleTime(1000 * 60 * 60);
//		connector.setSoLingerTime(-1);
//		connector.setPort(9090);
//		server.setConnectors(new Connector[] { connector });
//
//		final WebAppContext bb = new WebAppContext();
//		bb.setServer(server);
//		bb.setContextPath("/");
//		bb.setWar("src/main/webapp");
//
//		server.setHandler(bb);
//
//		try {
//			System.out
//					.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
//			server.start();
//			while (System.in.available() == 0)
//				Thread.sleep(5000);
//			server.stop();
//			server.join();
//		} catch (final Exception e) {
//			e.printStackTrace();
//			System.exit(100);
//		}
    }
}
