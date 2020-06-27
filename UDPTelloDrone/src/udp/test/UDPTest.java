package udp.test;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import flight.FlightCommands;
import flight.FlightUserInput;
import udp.client.EchoClient;
import udp.server.EchoServer;

/**
 * Test client for Java UDP client/server communication with RyzeRobotics Tello drone
 * @author Lachlan Blennerhassett
 * - Credit and thanks to Baeldung tutorials for the client/server code:
 * - https://www.baeldung.com/udp-in-java
 */

public class UDPTest {
	static EchoClient client;

	public static void setup() throws SocketException, UnknownHostException{
		new EchoServer().start();
		client = new EchoClient();
	}

	public static void tearDown() throws IOException {
		client.close();
	}


	public static void main(String[] args) throws IOException, InterruptedException
	{
		setup();
		System.out.println("Client/Server setup complete.");
		FlightCommands command = new FlightCommands(client);
		command.commenceFlight();
		System.out.println("Flight complete.");
		tearDown();	
		System.out.println("Client/Server closed.");
		System.exit(0);
	}
}