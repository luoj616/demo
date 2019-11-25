package demo.luojun.com.demo.activity.model.service.net;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PacketOutputStream {
  private BufferedOutputStream outputStream;
  
  public PacketOutputStream(OutputStream paramOutputStream) { this.outputStream = new BufferedOutputStream(paramOutputStream); }
  
  public void close() throws IOException { this.outputStream.close(); }
  
  public void flush() throws IOException { this.outputStream.flush(); }
  
  public void write(byte paramByte) throws IOException { this.outputStream.write(paramByte); }
  
  public void write(int paramInt) throws IOException {
    write((byte)(paramInt & 0xFF));
    write((byte)((0xFF00 & paramInt) >> 8));
    write((byte)((0xFF0000 & paramInt) >> 16));
    write((byte)((paramInt & 0xFF000000) >> 24));
  }
  
  public void write(short paramShort) throws IOException {
    write((byte)(paramShort & 0xFF));
    write((byte)((paramShort & 0xFF00) >> 8));
  }
  
  public void write(byte[] paramArrayOfByte) throws IOException {
    if (paramArrayOfByte != null)
      this.outputStream.write(paramArrayOfByte); 
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws IOException {
    if (paramArrayOfByte != null)
      this.outputStream.write(paramArrayOfByte, paramInt1, paramInt2); 
  }
  
  public void write(short[] paramArrayOfShort) throws IOException {
    if (paramArrayOfShort == null)
      return; 
    int i = paramArrayOfShort.length;
    for (byte b = 0; b < i; b++)
      write(paramArrayOfShort[b]); 
  }
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\service\net\PacketOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */