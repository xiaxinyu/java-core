package org.learning.nio.channel.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferChannelDemo {
	public static void main(String args[]) throws Exception {  
        String infile = "c:\\123.txt";  
        String outfile = "c:\\124.txt";  
  
        FileInputStream fin = new FileInputStream(infile);  
        FileOutputStream fout = new FileOutputStream(outfile);
        
        FileChannel fcin = fin.getChannel();  
        FileChannel fcout = fout.getChannel();  
        
        ByteBuffer buffer = ByteBuffer.allocate(1024);  
        while (true) {  
            buffer.clear();  
        
            int r = fcin.read(buffer);  
            if (r == -1) {  
                break;  
            } 
            
            buffer.flip();  //将buffer指针指向头部  
            fcout.write(buffer);  
        }
        
        fout.close();
        fin.close();
    }  
}
