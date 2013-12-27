package component.authorization.strategy.example;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartComponentAuthorizationStrategyExample {
    public static void main(String[] args) throws Exception {
    	final Server server = new Server();
		final SelectChannelConnector connector = new SelectChannelConnector();
		// Set some timeout options to make debugging easier.
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(9090);
		server.setConnectors(new Connector[] { connector });

		final WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");

		server.setHandler(bb);

		try {
			System.out
					.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			while (System.in.available() == 0)
				Thread.sleep(5000);
			server.stop();
			server.join();
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
    }
}
