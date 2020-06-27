package flight;

import java.io.IOException;
import java.util.Scanner;

import udp.client.EchoClient;

public class FlightUserInput
{
	EchoClient echoClient;
	FlightCommands flightCommands;
	
	public FlightUserInput(EchoClient echoClient, FlightCommands flightCommands) throws IOException, InterruptedException
	{
		this.echoClient = echoClient;
		this.flightCommands = flightCommands;
		boolean flying = true;
		Scanner input = new Scanner(System.in);
		while (flying)
		{
			String command = input.nextLine();
			flightCommands.sendCommand(command);
			if(command == "land")
			{
				flying = false;
				System.exit(0);
			}
		}
	}
}
