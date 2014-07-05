package com.mygdx.game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class MyGdxGame extends ApplicationAdapter implements ApplicationListener  {
	SpriteBatch batch;
	long FPS =1000/20;
	long start,dx;
	private static final float skale =0.5f;
	static byte W =40;
	static byte H =30;
	public static volatile byte[] map =new byte[W*H];
//	byte[][] tempMap=new byte[15][20];
//					{	{0x00,0x01,0x02},
//						{0x03,0x04,0x02},
//						{0x04,0x00,0x01},
//						{0x01,0x00,0x01}};
	Texture img;
	int textureSize;
	TextureRegion[] textures;
	ArrayList<Pixel> pixels;
	@Override
	public void create () {
		img = new Texture(Gdx.files.internal("a.png"));
		
		try {
			pixels=Pixel.readPixels("test.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textureSize=pixels.size();
		textures=new TextureRegion[textureSize];
		for(Pixel p:pixels){
			textures[p.id]=new TextureRegion(img, p.regX, p.regY, p.size, p.size);
		}
		Thread thread = new Thread(new Server("127.0.0.1", 8888));
		thread.start();
		//System.out.println(pixels);
		
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				if(keycode==Input.Keys.W||keycode==Input.Keys.UP){
					
				}else if(keycode==Input.Keys.D||keycode==Input.Keys.RIGHT){
					
				}else if(keycode==Input.Keys.A||keycode==Input.Keys.LEFT){
					
				}else if(keycode==Input.Keys.S||keycode==Input.Keys.DOWN){
					
				}
				return false;
			}
			
			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	}

	@Override
	public void render () {
		start=System.currentTimeMillis();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		

		for(int i = 0; i<map.length;i++){
			batch.draw(textures[map[i]],i%W*pixels.get(map[i]).size*skale ,
					(H-(int)(i/W)-1)*pixels.get(map[i]).size*skale,
					pixels.get(map[i]).size*skale*0.5f,pixels.get(map[i]).size*skale*0.5f,
					pixels.get(map[i]).size*skale,pixels.get(map[i]).size*skale,
					1,1,pixels.get(map[i]).angle);
		}
		
		batch.end();
		
		dx=start-System.currentTimeMillis();;
		if(dx<FPS){
			try {
				Thread.sleep(FPS-dx);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
