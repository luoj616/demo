package demo.luojun.com.demo.activity.model.service.net;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import demo.luojun.com.demo.activity.model.service.LogUtils;
import demo.luojun.com.demo.activity.model.service.packets.PacketHeader;

public class PacketInputStream {
  protected static final int HEADER_FLAG = -1;
  
  private BufferedInputStream inputStream;
  
  static  {
  
  }
  
  public PacketInputStream(InputStream paramInputStream) { this.inputStream = new BufferedInputStream(paramInputStream); }
  
  public void close() throws IOException { this.inputStream.close(); }
  
  public void read(byte[] paramArrayOfByte) throws IOException {
    if (paramArrayOfByte == null)
      return; 
    for (int i = 0; i < paramArrayOfByte.length; i += this.inputStream.read(paramArrayOfByte, i, paramArrayOfByte.length - i));
  }
  
  public byte readByte() throws IOException {
    int i = this.inputStream.read();
    if (i != -1)
      return (byte)i; 
    throw new RuntimeException("End of inputstream when readByte.");
  }
  
  public int readInt() throws IOException { return readByte() & 0xFF | readByte() << 8 & 0xFF00 | readByte() << 16 & 0xFF0000 | readByte() << 24 & 0xFF000000; }
  
  public PacketHeader readPacketHeader() throws IOException {
    if (readInt() == -1) {
      PacketHeader packetHeader = new PacketHeader(readInt(), readInt());
      LogUtils.LOGI("PacketInputStream", packetHeader.toString());
      return packetHeader;
    } 
    throw new IOException("unexpected flag when reading from inputstream.");
  }
  
  public short readShort() throws IOException { return (short)(readByte() & 0xFF | readByte() << 8 & 0xFF00); }
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\service\net\PacketInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */