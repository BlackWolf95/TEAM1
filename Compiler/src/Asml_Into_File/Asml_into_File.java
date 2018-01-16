package Asml_Into_File;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Asml_into_File extends PrintStream{

	private final PrintStream ps;
	
	public Asml_into_File(OutputStream main,PrintStream ps){
		super(main);
		this.ps=ps;
	}
	
	public void close(){
		super.close();
	}
	
	public void flush(){
		super.flush();
		ps.flush();
	}

	public void write(byte[] buf,int off,int len)
	{
		super.write(buf, off, len);
		ps.write(buf, off, len);
	}
	
	public void write(int b)
	{
		super.write(b);
		ps.write(b);
	}
	
	public void write(byte[] b) throws IOException{
		super.write(b);
		ps.write(b);
	}
}

