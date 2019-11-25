package demo.luojun.com.demo.activity.model.service;



public class ScanConversionLibrary {
//  protected static native void fillRFCoordinateTables(ConvexScanConversionParams paramConvexScanConversionParams);
//
//  public static native String getVersionName();
  
  public static ScanConversionParams initializeRFCoordinateTable(ScanConversionParams paramScanConversionParams) {
   // if (paramScanConversionParams instanceof ConvexScanConversionParams)
   //   fillRFCoordinateTables((ConvexScanConversionParams)paramScanConversionParams);
    return paramScanConversionParams;
  }
  
//  protected static native void nativeConvexScanConversion(byte[] paramArrayOfByte, ConvexScanConversionParams paramConvexScanConversionParams, long paramLong);
//
//  protected static native void nativeLinearScanConversion(byte[] paramArrayOfByte, LinearScanConversionParams paramLinearScanConversionParams, long paramLong);
//
//  public static GrayFrame rfFrameToGrayFrame(RFFrame paramRFFrame, ScanConversionParams paramScanConversionParams) {
//    boolean bool = paramScanConversionParams instanceof ConvexScanConversionParams;
//    if (!bool || ((ConvexScanConversionParams)paramScanConversionParams).isRFCoordinateTableInitialized()) {
//      int i = paramScanConversionParams.getImageWidth();
//      int j = paramScanConversionParams.getImageHeight();
//      GrayFrame grayFrame = new GrayFrame(i, j);
//      StringBuilder stringBuilder = new StringBuilder();
//      stringBuilder.append("width:");
//      stringBuilder.append(i);
//      stringBuilder.append(" height:");
//      stringBuilder.append(j);
//      LogUtils.LOGI("rfFrameToGrayFrame", stringBuilder.toString());
//      if (bool) {
//        LogUtils.LOGI("rfFrameToGrayFrame", "run nativeConvexScanConversion");
//        nativeConvexScanConversion(paramRFFrame.getEnvelopData(), (ConvexScanConversionParams)paramScanConversionParams, grayFrame.getNativeHandler());
//      } else {
//        if (paramScanConversionParams instanceof LinearScanConversionParams) {
//          LogUtils.LOGI("rfFrameToGrayFrame", "run nativeLinearScanConversion");
//          nativeLinearScanConversion(paramRFFrame.getEnvelopData(), (LinearScanConversionParams)paramScanConversionParams, grayFrame.getNativeHandler());
//          LogUtils.LOGI("rfFrameToGrayFrame", "end of rfFrameToGrayFrame");
//          return grayFrame;
//        }
//        throw new IllegalArgumentException("Unknown ScanConversionParam type in rfFrameToGrayFrame!");
//      }
//      LogUtils.LOGI("rfFrameToGrayFrame", "end of rfFrameToGrayFrame");
//      return grayFrame;
//    }
//    throw new IllegalArgumentException("ConvexScanConversionParams must call fillRFCoordinate before use.");
//  }
}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan2\library\ScanConversionLibrary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */