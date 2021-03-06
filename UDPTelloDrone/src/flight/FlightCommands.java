package flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import udp.client.EchoClient;

/**
 * Flight commands to Tello drone using Java UDP client/server architecture
 * - @author Lachlan Blennerhassett
 * - Credit and thanks to Baeldung tutorials for the client/server code:
 * - https://www.baeldung.com/udp-in-java
 */

public class FlightCommands
{
	String echo = null;
	EchoClient client;
	
	public FlightCommands(EchoClient client)
	{
		this.client = client;
	}
	
	public String getEcho()
	{
		return echo;
	}
	
	public EchoClient getClient()
	{
		return client;
	}
	
	public void sendCommand(String command) throws IOException, InterruptedException
	{
		echo = client.sendEcho(command);
	}
	
	public void checkBattery() throws IOException, InterruptedException
	{
		Thread.sleep(1000);
		this.sendCommand("battery?");
		System.out.printf("Battery level: " + getEcho());
	}
	
	public void commenceFlightCountdown() throws InterruptedException {
    	System.out.println("Tello drone in start up.");
    	Thread.sleep(2000);
    	System.out.println("Flight countdown commenced.");
    	Thread.sleep(2000);
    	for(int i = 10; i > 1; i --)
    	{
    		System.out.println(i + "...");
    		Thread.sleep(1000);
    	}
    	System.out.println("LIFT OFF!");
    }
    
	public void commenceFlight() throws IOException, InterruptedException {
   	
		sendCommand("command");
		assertEquals("ok", echo);
		assertFalse(echo.equals("command"));
		System.out.println("Tello comms established.");
		checkBattery();
		Thread.sleep(3000);
		
		commenceFlightCountdown();
		sendCommand("takeoff");
		Thread.sleep(2000);
		
		// Flight commands here
		//flightSequence();
		new FlightUserInput(client, this);
		
		checkBattery();
   }
	
	public void flightSequence() throws IOException, InterruptedException
	{
		System.out.println("Forward heading 0.5 m");
		sendCommand("forward 50");
		System.out.println("Flip forward");
		sendCommand("flip f");
		System.out.println("Forward heading 0.5 m");
		sendCommand("forward 50");
		
		Thread.sleep(1000);
		
		System.out.println("Rotate 180 degrees clockwise");
		sendCommand("cw 180");
		Thread.sleep(1000);
		
		System.out.println("Forward heading 0.5 m");
		sendCommand("forward 50");
		System.out.println("Flip forward");
		sendCommand("flip f");
		System.out.println("Forward heading 0.5 m");
		sendCommand("forward 50");
		
		System.out.println("Rotate 180 degrees clockwise");
		sendCommand("cw 180");
		Thread.sleep(1000);
		
		System.out.println("Commence landing protocol");
		sendCommand("land");
	}
}


