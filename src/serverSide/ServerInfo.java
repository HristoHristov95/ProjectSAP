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
		this.server=new ServerSocket(1913);
	}
	public synchronized void getNextClientIfAvailable() throws IOException
	{
		this.socket=this.server.accept();
		this.input=new Scanner(this.socket.getInputStream());
		this.output=new PrintStream(this.socket.getOutputStream());
	}
}