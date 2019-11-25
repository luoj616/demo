package demo.luojun.com.demo.activity.model.service.packets;

public class PacketHeader {
  public final int id;
  
  public final int length;
  
  public PacketHeader(int paramInt1, int paramInt2) {
    this.id = paramInt1;
    this.length = paramInt2;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PacketHeader Id:");
    stringBuilder.append(this.id);
    stringBuilder.append("  length:");
    stringBuilder.append(this.length);
    return stringBuilder.toString();
  }
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\service\packets\PacketHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */