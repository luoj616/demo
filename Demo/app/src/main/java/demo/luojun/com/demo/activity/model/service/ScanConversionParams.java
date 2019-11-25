package demo.luojun.com.demo.activity.model.service;

public abstract class ScanConversionParams implements Cloneable {
  public abstract Object clone();
  
  public abstract double getCmPerPixel();
  
  public abstract float getDepthInCM();
  
  public abstract int getImageHeight();
  
  public abstract float getImageHeightInCM();
  
  public abstract float getImageTotalDepthInCM();
  
  public abstract int getImageWidth();
  
  public abstract float getImageWidthInCM();
  
  public abstract int getLines();
  
  public abstract float getProbeImageDeadZoneInCM();
  
  public abstract int getSamplesPerLine();
  
public Object superClone() {
  try {
    return super.clone();
  } catch (CloneNotSupportedException e) {
    e.printStackTrace();
    return null;
  }
}
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\library\ScanConversionParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */