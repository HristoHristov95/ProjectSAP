package serverSide;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try{
			ServerInfo newServer=new ServerInfo();
			newServer.createServerLink();
			while(!newServer.getServer().isClosed())
			{
				if(AdminMainOptions.ServerStatusCheck != true)
				{
					System.exit(1);
					break;
				}
				newServer.getNextClientIfAvailable();
				ClassThreadStartUpHelper helper=new ClassThreadStartUpHelper(newServer);
				Thread thread=new Thread(helper);
				thread.start();
			}
		}catch(IOException e){
			//System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}catch(Exception e){
			//System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
