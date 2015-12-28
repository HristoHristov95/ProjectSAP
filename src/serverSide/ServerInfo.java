package serverSide;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerInfo{
	public ServerSocket server;
	public Socket socket;
	public Scanner input;
	public PrintStream output;
	public ServerSocket getServer()
	{
		return this.server;
	}
	public Socket getSocket()
	{
		return this.socket;
	}
	public Scanner getScannerInput()
	{
		return this.input;
	}
	public PrintStream getOutputStream()
	{
		return this.output;
	}
	public void createServerLink() throws IOException
	{
		this.server=new ServerSocket(1909);
		this.socket=server.accept();
		this.input=new Scanner(socket.getInputStream());
		this.output=new PrintStream(socket.getOutputStream());
	}
}