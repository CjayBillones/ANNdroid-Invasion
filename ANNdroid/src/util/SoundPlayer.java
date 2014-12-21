package ANNdroid.src.util;

import javazoom.jl.decoder.*;
import javazoom.jl.player.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;

public class SoundPlayer{

	private static enum State{NOTSTARTED, PLAYING, PAUSED, FINISHED};

	private final String filename;
	private final Object lock = new Object();
	
	private Player p;
	private boolean loop;
	private State playerState = State.NOTSTARTED;

	public SoundPlayer(String filename, boolean loop){
		this.filename = filename;
		this.loop = loop;
	}

	public void play(){
		synchronized(lock){
			if(playerState == State.NOTSTARTED || playerState == State.FINISHED){				
				Thread t = new Thread(){
					public void run(){
						try{
							p = new Player(new BufferedInputStream(new FileInputStream(filename)));
							playSound();
						}catch(FileNotFoundException e){	e.printStackTrace();
						}catch(JavaLayerException e){	e.printStackTrace();	}
					}
				};
				t.start();
				playerState = State.PLAYING;
			}
			else if(playerState == State.PAUSED)
				resume();
		}
	}

	public boolean pause(){
		synchronized(lock){
			if(playerState == State.PLAYING)
				playerState = State.PAUSED;
			return playerState == State.PAUSED;
		}
	}

	public boolean resume(){
		synchronized(lock){
			if(playerState == State.PAUSED){
				playerState = State.PLAYING;
				lock.notifyAll();
			}
			return playerState == State.PLAYING;
		}
	}

	public void stop(){
		synchronized(lock){
			playerState = State.FINISHED;
			lock.notifyAll();
		}
	}

	public void playSound(){
		while(playerState != State.FINISHED){
			try{
				if(!p.play(1))
					break;
			}catch(JavaLayerException e){
				e.printStackTrace();
				break;
			}
			synchronized(lock){
				while(playerState == State.PAUSED){
					try{
						lock.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
						break;
					}
				}
			}
		}
		if(loop){
			playerState = State.NOTSTARTED;
			play();
		}
		else
			close();
	}

	public void close(){
		synchronized(lock){	playerState = State.FINISHED;	}
		try{
			p.close();
		}catch(Exception e){	e.printStackTrace();	}
	}
}