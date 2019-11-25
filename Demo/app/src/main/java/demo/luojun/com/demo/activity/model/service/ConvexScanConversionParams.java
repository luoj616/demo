package demo.luojun.com.demo.activity.model.service;



public class ConvexScanConversionParams extends ScanConversionParams implements Cloneable {
  private float[] angle_table = null;
  
  private float depthInCM;
  
  private float[] distance_table = null;
  
  private float halfDisplayAngleInDegree;
  
  private float halfScanAngleInDegree;
  
  private int imageHeight = 0;
  
  private float imageHeightInCM;
  
  private int imageWidth;
  
  private float imageWidthInCM;
  
  private float line_pixels_with_probe;
  
  private int lines;
  
  private float lines_per_angle;
  
  private float pixels_per_sample;
  
  private float probeImageDeadZoneInCM;
  
  private float probeRadiusInCM;
  
  private float probe_pixels;
  
  private int rf_coordinate_table_size;
  
  private int samplesPerLine;
  
  private float samples_in_probe;
  
  private float samples_per_pixel;
  
  private float samples_with_probe;
  
  private ConvexScanConversionParams(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.probeRadiusInCM = paramFloat1;
    this.depthInCM = paramFloat2;
    this.halfScanAngleInDegree = paramFloat3;
    this.halfDisplayAngleInDegree = paramFloat4;
    this.samplesPerLine = paramInt1;
    this.lines = paramInt2;
    this.imageWidth = paramInt3;
    this.imageHeight = paramInt4;
    calculateDerivedParams();
  }
  
  protected void calculateDerivedParams() {
    float f = this.probeRadiusInCM;
    int i = this.samplesPerLine;
    this.samples_in_probe = f * i / this.depthInCM;
    this.samples_with_probe = i + this.samples_in_probe;
    this.lines_per_angle = this.lines / this.halfScanAngleInDegree * 2.0F;
    calculateImageSize(true);
    f = this.pixels_per_sample;
    this.probe_pixels = this.samples_in_probe * f;
    this.line_pixels_with_probe = this.samples_with_probe * f;
    this.rf_coordinate_table_size = this.imageWidth / 2 * this.imageHeight;
    i = this.rf_coordinate_table_size;
    this.angle_table = new float[i];
    this.distance_table = new float[i];
  }
  
  protected void calculateImageSize(boolean paramBoolean) {
    LogUtils.LOGI("params", toString());
    if (paramBoolean) {
      float f1 = this.imageWidth;
      double d4 = Math.sin(degreeToRad(this.halfDisplayAngleInDegree));
      float f2 = this.samples_with_probe;
      double d5 = f2;
      Double.isNaN(d5);
      this.pixels_per_sample = f1 / (float)(d4 * d5 * 2.0D + 1.0D);
      d4 = f2;
      d5 = Math.cos(degreeToRad(this.halfDisplayAngleInDegree));
      double d6 = this.samples_in_probe;
      Double.isNaN(d6);
      Double.isNaN(d4);
      double d7 = this.pixels_per_sample;
      Double.isNaN(d7);
      this.imageHeight = (int)((d4 - d5 * d6) * d7 + 1.0D);
    } else {
      float f1 = this.imageHeight;
      double d4 = this.samples_with_probe;
      double d5 = Math.cos(degreeToRad(this.halfDisplayAngleInDegree));
      double d6 = this.samples_in_probe;
      Double.isNaN(d6);
      Double.isNaN(d4);
      this.pixels_per_sample = f1 / (float)(d4 - d5 * d6 + 1.0D);
      d4 = Math.sin(degreeToRad(this.halfDisplayAngleInDegree));
      d5 = this.samples_with_probe;
      Double.isNaN(d5);
      d6 = this.pixels_per_sample;
      Double.isNaN(d6);
      this.imageWidth = (int)(d4 * d5 * 2.0D * d6 + 1.0D);
    } 
    this.samples_per_pixel = 1.0F / this.pixels_per_sample;
    LogUtils.LOGI("params", toString());
    double d1 = Math.sin(degreeToRad(this.halfDisplayAngleInDegree));
    float f = this.depthInCM;
    double d2 = (this.probeRadiusInCM + f);
    Double.isNaN(d2);
    this.imageWidthInCM = (float)(d1 * 2.0D * d2);
    d1 = f;
    d2 = Math.cos(degreeToRad(this.halfDisplayAngleInDegree));
    f = this.probeRadiusInCM;
    double d3 = f;
    Double.isNaN(d3);
    Double.isNaN(d1);
    this.imageHeightInCM = (float)(d1 + d2 * d3);
    d1 = f;
    d2 = Math.cos(degreeToRad(this.halfDisplayAngleInDegree));
    d3 = this.probeRadiusInCM;
    Double.isNaN(d3);
    Double.isNaN(d1);
    this.probeImageDeadZoneInCM = (float)(d1 - d2 * d3);
    int i = this.imageWidth;
    if (i % 2 != 0)
      this.imageWidth = i + 1; 
    i = this.imageHeight;
    if (i % 2 != 0)
      this.imageHeight = i + 1; 
  }
  
  public Object clone() {
    return (ConvexScanConversionParams)superClone();
  }
  
  protected float degreeToRad(float paramFloat) {
    double d = paramFloat;
    Double.isNaN(d);
    return (float)(d * Math.PI / 180.0D);
  }
  
  public double getCmPerPixel() { return (this.imageWidthInCM / this.imageWidth); }
  
  public float getDepthInCM() { return this.depthInCM; }
  
  public float getHalfDisplayAngleInDegree() { return this.halfDisplayAngleInDegree; }
  
  public float getHalfScanAngleInDegree() { return this.halfScanAngleInDegree; }
  
  public int getImageHeight() { return this.imageHeight; }
  
  public float getImageHeightInCM() { return this.imageHeightInCM; }
  
  public float getImageTotalDepthInCM() { return this.depthInCM + this.probeImageDeadZoneInCM; }
  
  public int getImageWidth() { return this.imageWidth; }
  
  public float getImageWidthInCM() { return this.imageWidthInCM; }
  
  public int getLines() { return this.lines; }
  
  public float getProbeImageDeadZoneInCM() { return this.probeImageDeadZoneInCM; }
  
  public float getProbeRadiusInCM() { return this.probeRadiusInCM; }
  
  public int getRFCoordinateTableSize() { return this.rf_coordinate_table_size; }
  
  public int getSamplesPerLine() { return this.samplesPerLine; }
  
  public boolean isRFCoordinateTableInitialized() { return (this.angle_table != null && this.distance_table != null); }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("=========ConvexScanConversionParams========");
    stringBuilder.append("\n");
    stringBuilder.append("probeRadiusInCM:");
    stringBuilder.append(this.probeRadiusInCM);
    stringBuilder.append("\n");
    stringBuilder.append("probeImageDeadZoneInCM:");
    stringBuilder.append(this.probeImageDeadZoneInCM);
    stringBuilder.append("\n");
    stringBuilder.append("depthInCM:");
    stringBuilder.append(this.depthInCM);
    stringBuilder.append("\n");
    stringBuilder.append("halfScanAngleInDegree:");
    stringBuilder.append(this.halfScanAngleInDegree);
    stringBuilder.append("\n");
    stringBuilder.append("halfDisplayAngleInDegree:");
    stringBuilder.append(this.halfDisplayAngleInDegree);
    stringBuilder.append("\n");
    stringBuilder.append("samplesPerLine:");
    stringBuilder.append(this.samplesPerLine);
    stringBuilder.append("\n");
    stringBuilder.append("lines:");
    stringBuilder.append(this.lines);
    stringBuilder.append("\n");
    stringBuilder.append("imageWidth:");
    stringBuilder.append(this.imageWidth);
    stringBuilder.append("\n");
    stringBuilder.append("=======derived params================");
    stringBuilder.append("\n");
    stringBuilder.append("imageHeight:");
    stringBuilder.append(this.imageHeight);
    stringBuilder.append("\n");
    stringBuilder.append("samples_in_probe:");
    stringBuilder.append(this.samples_in_probe);
    stringBuilder.append("\n");
    stringBuilder.append("samples_with_probe:");
    stringBuilder.append(this.samples_with_probe);
    stringBuilder.append("\n");
    stringBuilder.append("probe_pixels:");
    stringBuilder.append(this.probe_pixels);
    stringBuilder.append("\n");
    stringBuilder.append("line_pixels_with_probe:");
    stringBuilder.append(this.line_pixels_with_probe);
    stringBuilder.append("\n");
    stringBuilder.append("samples_per_pixel:");
    stringBuilder.append(this.samples_per_pixel);
    stringBuilder.append("\n");
    stringBuilder.append("pixels_per_sample:");
    stringBuilder.append(this.pixels_per_sample);
    stringBuilder.append("\n");
    stringBuilder.append("lines_per_angle:");
    stringBuilder.append(this.lines_per_angle);
    stringBuilder.append("\n");
    stringBuilder.append("rf_coordinate_table_size:");
    stringBuilder.append(this.rf_coordinate_table_size);
    stringBuilder.append("\n");
    return stringBuilder.toString();
  }
  
  public static class Builder {
    private float depthInCM = 16.0F;
    
    private float halfDisplayAngle = 35.0F;
    
    private float halfScanAngle = 35.0F;
    
    private int imageHeight = 0;
    
    private int imageWidth = 400;
    
    private int lines = 120;
    
    private float probeRadiusInCM = 5.114F;
    
    private int samplesPerLine = 512;
    
    public ConvexScanConversionParams build() {
//      ConvexScanConversionParams convexScanConversionParams =
//              new ConvexScanConversionParams(this.probeRadiusInCM,
//                      this.depthInCM, this.halfScanAngle, this.halfDisplayAngle,
//                      this.samplesPerLine, this.lines, this.imageWidth, this.imageHeight, null);
//      ScanConversionLibrary.initializeRFCoordinateTable(convexScanConversionParams);
      return/* convexScanConversionParams*/  null;
    }
    
    public Builder setDepthInCM(float param1Float) {
      this.depthInCM = param1Float;
      return this;
    }
    
    public Builder setHalfDisplayAngle(float param1Float) {
      this.halfDisplayAngle = param1Float;
      return this;
    }
    
    public Builder setHalfScanAngle(float param1Float) {
      this.halfScanAngle = param1Float;
      return this;
    }
    
    public Builder setImageHeight(int param1Int) {
      this.imageHeight = param1Int;
      return this;
    }
    
    public Builder setImageWidth(int param1Int) {
      this.imageWidth = param1Int;
      return this;
    }
    
    public Builder setLines(int param1Int) {
      this.lines = param1Int;
      return this;
    }
    
    public Builder setProbeRadiusInCM(float param1Float) {
      this.probeRadiusInCM = param1Float;
      return this;
    }
    
    public Builder setSamplesPerLine(int param1Int) {
      this.samplesPerLine = param1Int;
      return this;
    }
  }
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\library\ConvexScanConversionParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */