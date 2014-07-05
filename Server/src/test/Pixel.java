package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import com.thoughtworks.xstream.XStream;


public class Pixel {
public byte id;
public static final byte size=32;
public short regX;
public short regY;
public short angle=0;
public String name ;
public Pixel(byte id, short regX, short regY, short angle, String name) {
	super();
	this.id = id;
	this.regX = regX;
	this.regY = regY;
	this.angle = angle;
	this.name = name;
}
public byte getId() {
	return id;
}
public void setId(byte id) {
	this.id = id;
}
public short getRegX() {
	return regX;
}
public void setRegX(short regX) {
	this.regX = regX;
}
public short getRegY() {
	return regY;
}
public void setRegY(short regY) {
	this.regY = regY;
}
public short getAndle() {
	return angle;
}
public void setAndle(short angle) {
	this.angle = angle;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Box [id=" + id + ", regX=" + regX + ", regY=" + regY + ", angle="
			+ angle + ", name=" + name + "]";
}

public static ArrayList<Pixel> readPixels(String name)throws Exception{
	 ArrayList<Pixel> tmp;
	XStream xstream = new XStream();
    xstream.alias("person", Pixel.class);
	
//      FileHandle[] files=  Gdx.files.internal(".").list();
//      for(FileHandle file:files){
//    	  System.out.println(file.name());
//      }
      //System.out.println(Gdx.files.internal("*").)
       tmp=(ArrayList<Pixel>) xstream.fromXML(new File(name));
		
   
	return tmp;
}
public static void writePixels(String name,ArrayList<Pixel> pixels){
	XStream xstream = new XStream();
    xstream.alias("person", Pixel.class);
    
    String xml = xstream.toXML(pixels);
     try {
    	 FileOutputStream fileOutputStream = new FileOutputStream(name);
    	 fileOutputStream.write("<?xml version=\"1.0\"?>\n".getBytes("UTF-8"));
    	 fileOutputStream.write(xml.getBytes("UTF-8"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
