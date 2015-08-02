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
import de.alpharogroup.jetty9.runner.factories.ServletContextHandlerFactory;

public class StartComponentAuthorizationStrategyExample
{
	public static void main(final String[] args) throws Exception
	{

		final int sessionTimeout = (int)Duration.minutes(1).seconds();// set timeout to 30min(60sec
																		// *
																		// 30min=1800sec)...
		System.setProperty("wicket.configuration", "development");
		final String projectname = "component.authorization.strategy.example";
		final File projectDirectory = PathFinder.getProjectDirectory();
		final File webapp = PathFinder.getRelativePath(projectDirectory, projectname, "src",
			"main", "webapp");
		final String filterPath = "/*";
		// Add a file appender to the logger programatically
		// Logger logger = org.apache.log4j.LogManager.getLogger("org.eclipse.jetty");
		// Logger.getRootLogger().addFileAppender(newFileAppender("./application.log"));

		final ServletContextHandler servletContextHandler = ServletContextHandlerFactory
			.getNewServletContextHandler(ServletContextHandlerConfiguration
				.builder()
				.filterHolderConfiguration(
					FilterHolderConfiguration
						.builder()
						.filterClass(WicketFilter.class)
						.filterPath(filterPath)
						.initParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*")
						.initParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
							WicketApplication.class.getName()).build())
				.servletHolderConfiguration(
					ServletHolderConfiguration.builder().servletClass(DefaultServlet.class)
						.pathSpec(filterPath).build()).contextPath("/").webapp(webapp)
				.maxInactiveInterval(sessionTimeout).filterPath(filterPath).build());

		final Jetty9RunConfiguration config = Jetty9RunConfiguration.builder()
			.servletContextHandler(servletContextHandler)
			.httpPort(WicketApplication.DEFAULT_HTTP_PORT)
			.httpsPort(WicketApplication.DEFAULT_HTTPS_PORT).keyStorePassword("wicket")
			.keyStorePathResource("/keystore").build();
		final Server server = new Server();
		Jetty9Runner.runServletContextHandler(server, config);

	}
}
