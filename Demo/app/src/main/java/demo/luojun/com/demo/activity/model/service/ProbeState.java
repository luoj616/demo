package demo.luojun.com.demo.activity.model.service;

public interface ProbeState {
  void changeFocus(float paramFloat);
  
  void changeGain(int paramInt);
  
  void changeToNextScanDepthGrade();
  
  void changeToPrevScanDepthGrade();
  
  int getClampedGain(int paramInt);
  
  float getCurrentFocusDepthMM();
  
  float getCurrentScanDepthMM();
  
  int getGain();
  
  int getMaxGainValue();
  
  ProbeScanType getProbeScanType();
  
  PtBfMode getPtBfMode();
  
  void loadArrayScanCmdCtrlParamsFromConfigFile();
  
  void loadDefaultTableParams();
  
 // ArrayScanCmdCtrlParams makeArrayScanCmdCtrlParams();
  
  ScanConversionParams makeScanConversionParams();
  
  void reset();
  
//  void setArrayScanCmdCtrlParams(ArrayScanCmdCtrlParams paramArrayScanCmdCtrlParams);
  
  void setMaxGainValue(int paramInt);
  
  void switchToPtBfMode(PtBfMode paramPtBfMode);
  
  void updateOutputImageSize(int paramInt1, int paramInt2);
  
  public enum FreqMode {
    FREQ_3_5MHZ(3.5F),
    FREQ_5_0MHZ(5.0F),
    FREQ_8_0MHZ(8.0F);
    
    private final float value;
    
    static  {
      FreqMode[] s = new FreqMode[] { FREQ_3_5MHZ, FREQ_5_0MHZ, FREQ_8_0MHZ };
    }
    
    FreqMode(float param1Float1) { this.value = param1Float1; }
    
    public float value() { return this.value; }
  }
  
  public enum PtBfMode {
//    PT_MODE,
//    BF_MODE(8.0F);
//
//    static  {
//      PtBfMode s = new PtBfMode("BF_MODE");
//      PtBfMode[] t = new PtBfMode[] { PT_MODE, BF_MODE };
//    }
  }
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\library\ProbeState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */