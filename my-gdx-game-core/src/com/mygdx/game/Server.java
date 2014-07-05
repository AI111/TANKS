package com.mygdx.game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jdk.internal.util.xml.impl.Input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class Server implements Runnable {
	InputStream inputStream;
	OutputStream outputStream;
	public boolean wait=true;
	Socket socket;
	String host;
	int port;
	byte buf[] = new byte[40*30];
	public Server(String host,int port) {
		// TODO Auto-generated constructor stub
		this.host=host;
		this.port=port;
		SocketHints hints = new SocketHints();
		socket = Gdx.net.newClientSocket(Protocol.TCP, host, port, hints);
		inputStream = socket.getInputStream();
		outputStream = socket.getOutputStream();
	}

	@Override
	public void run() {
		
//		try {
//			outputStream.write("aaaa".getBytes());
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		while (wait){
			try {
				inputStream.read(MyGdxGame.map);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(new String(buf));
			
//			wait=false;
//			
//			try {
//				inputStream.close();
//				outputStream.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			finally{
//			socket.dispose();
//			}
		}
		
	}

}
