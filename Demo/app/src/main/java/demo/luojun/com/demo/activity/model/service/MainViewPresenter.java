package demo.luojun.com.demo.activity.model.service;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import java.util.function.Consumer;

public class MainViewPresenter  /*IMainViewPresenter, *//*ProbeService.ProbeStateListener*/ {
  private static final int BATTERY_FILTER_SIZE = 5;
  
  private static final float BF_CONTRAST_ENHANCE_FACTOR = 1.2F;
  
  private static final float BF_CORRELATION_ENHANCE_FACTOR = 0.4F;
  
  private static final int BF_MODE = 2;
  
  private static final String DEVICE_ID_ENABLE_UPLOAD_AND_INPUT_ID_PREFIX = "MYJCN1";
  
  private static final int EMPTY_VOLTAGE = 350;
  
  private static final float FAT_DEPTH_LIMIT = 45.0F;
  
  private static final int FRAME_MESSAGE_ID = 1;
  
  private static final int FULL_VOLTAGE = 400;
  
  private static final String PROBE_AP_DEFAULT_NAME = "AscannerDAP";
  
  private static final int PROBE_AP_NAME_LENGTH = 12;
  
  private static final String PROBE_AP_NAME_PREFIX = "CZT";
  
  private static final String PROBE_DEFAUL_AP_PWD = "12345678";
  
  private static final float PT_CONTRAST_ENHANCE_FACTOR = 1.1F;
  
  private static final float PT_CORRELATION_ENHANCE_FACTOR = 0.4F;
  
  private static final int PT_MODE = 1;
  
  private static final String REPORT_DATETIME_FORMAT = "yyyy.MM.dd HH:mm";
  
  private static final String TAG = "MainViewPresenter";
  
  private static final short TGC_CHANGE_STEP = 40;
  
  private List<String> apNames = new ArrayList();
  
//  private GrayFrame currentDisplayFrame;
//
//  private DateTime currentScanTime = DateTime.now();
  
  private String earId = "";
  
  private List<String> earIds = Collections.emptyList();
  
  private boolean enableProbeButtonScan = true;
  
  private Handler frameProcessHandler;
  
  private Handler.Callback frameProcessHandlerCallback = new Handler.Callback() {
      public boolean handleMessage(Message param1Message) {
//        final GrayFrame grayFrame = (GrayFrame)param1Message.obj;
//        int i = grayFrame.getWidth();
//        int j = grayFrame.getHeight();
//        MainViewPresenter.this.updateDisplayBitmapSize(i, j);
//        if (MainViewPresenter.this.frameSequence.size() > 0) {
//          float f;
//          LogUtils.LOGI("enhance", "do frame enhance.");
//          long l = System.currentTimeMillis();
//          i = param1Message.arg1;
//          if (i == 1) {
//            f = 1.1F;
//          } else if (i == 2) {
//            f = 1.2F;
//          } else {
//            throw new IllegalStateException("unexpected ptbfMode value.");
//          }
//          FrameProcessUtil.frameCorrelationEnhance(MainViewPresenter.this.lastCorrelationEnhancedFrame, grayFrame, 0.4F);
//          MainViewPresenter.this.lastCorrelationEnhancedFrame.release();
//          MainViewPresenter.access$702(MainViewPresenter.this, grayFrame.copy());
//          FrameProcessUtil.frameContrastEnhance(grayFrame, f);
//          StringBuilder stringBuilder = new StringBuilder();
//          stringBuilder.append("do frame enhance cost:");
//          stringBuilder.append(System.currentTimeMillis() - l);
//          LogUtils.LOGI("enhance", stringBuilder.toString());
//        } else {
//          if (MainViewPresenter.this.lastCorrelationEnhancedFrame != null)
//            MainViewPresenter.this.lastCorrelationEnhancedFrame.release();
//          MainViewPresenter.access$702(MainViewPresenter.this, grayFrame.copy());
//        }
//        MainViewPresenter.this.frameSequence.add(grayFrame);
//        grayFrame.getPixels(MainViewPresenter.this.reusedDisplayFrameBitmap);
//        MainViewPresenter.this.mainHandler.post(new Runnable() {
//              public void run() {
//                MainViewPresenter.access$902(MainViewPresenter.null.this.this$0, grayFrame);
//                MainViewPresenter.null.this.this$0.view.showFrame(MainViewPresenter.null.this.this$0.reusedDisplayFrameBitmap);
//              }
//            });
//        LogUtils.LOGI("render", "frame rendered.");
        return true;
      }
    };
  
  private HandlerThread frameProcessThread = new HandlerThread("FrameEnhanceHandlerThread");
  
  private Handler frameScanConversionHandler;
  
  private Handler.Callback frameScanConversionHandlerCallback = new Handler.Callback() {
      public boolean handleMessage(Message param1Message) {
//        Object[] arrayOfObject = (Object[])param1Message.obj;
//        RFFrame rFFrame = (RFFrame)arrayOfObject[0];
//        ScanConversionParams scanConversionParams = (ScanConversionParams)arrayOfObject[1];
//        long l = System.currentTimeMillis();
//        GrayFrame grayFrame = ScanConversionLibrary.rfFrameToGrayFrame(rFFrame, scanConversionParams);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("do sc cost:");
//        stringBuilder.append(System.currentTimeMillis() - l);
//        LogUtils.LOGI("render", stringBuilder.toString());
//        Message message = MainViewPresenter.this.frameProcessHandler.obtainMessage();
//        message.obj = grayFrame;
//        message.what = param1Message.what;
//        message.arg1 = param1Message.arg1;
//        MainViewPresenter.this.frameProcessHandler.sendMessage(message);
        return true;
      }
    };
  
  private HandlerThread frameScanConversionThread = new HandlerThread("FrameHandlerThread");
  
 // private FrameSequence frameSequence;
  
  private boolean isConnected = false;
  
 // private GrayFrame lastCorrelationEnhancedFrame = null;
  
  private Handler mainHandler = new Handler();
  
 // private RFFrame prevRF;
  
  private ProbeService probeService;
  
  private Runnable replayFrameRunnable = new Runnable() {
      public void run() {
//        if (MainViewPresenter.this.isReplaying) {
//          MainViewPresenter mainViewPresenter;
//          mainViewPresenter.loadFrameAtProgress(mainViewPresenter.replayProgress);
//          MainViewPresenter.access$1204(MainViewPresenter.this);
//          if (MainViewPresenter.this.replayProgress >= MainViewPresenter.this.frameSequence.size())
//            MainViewPresenter.access$1202(MainViewPresenter.this, 0);
//          MainViewPresenter.this.mainHandler.postDelayed(this, 100);
//        }
      }
    };
  
  private int replayProgress = 0;
  
//  private final Repository repository;
  
  private Bitmap reusedDisplayFrameBitmap;
  
 // private CompositeDisposable subscriptions = new CompositeDisposable();
  
 // private IMainView view;
  
  private WifiHelper wifiHelper;
  
//  public MainViewPresenter(IMainView paramIMainView, ProbeService paramProbeService, WifiHelper paramWifiHelper) {
//    this.wifiHelper = paramWifiHelper;
//    this.view = paramIMainView;
//    this.probeService = paramProbeService;
//    this.probeService.setProbeStateListener(this);
//    this.frameSequence = new FrameSequence();
//    this.repository = Repository.instance();
//    this.frameScanConversionThread.start();
//    this.frameScanConversionHandler = new Handler(this.frameScanConversionThread.getLooper(), this.frameScanConversionHandlerCallback);
//    this.frameProcessThread.start();
//    this.frameProcessHandler = new Handler(this.frameProcessThread.getLooper(), this.frameProcessHandlerCallback);
//  }
  
  private int batteryDataToPercentage(short paramShort) { return (int)((paramShort * 0.01618F * 100.0F - 350.0F) / 50.0F * 100.0F); }
  
  private Bitmap cropAndResizeFatRoi(Bitmap paramBitmap, Rect paramRect, int paramInt1, int paramInt2) { return Bitmap.createScaledBitmap(Bitmap.createBitmap(paramBitmap, paramRect.left, paramRect.top, paramRect.width(), paramRect.height()), paramInt1, paramInt2, true); }
  
//  private void drawContourOnBitmap(Bitmap paramBitmap, Point[] paramArrayOfPoint) {
//    if (paramBitmap.isMutable()) {
//      if (paramArrayOfPoint == null)
//        return;
//      Paint paint = new Paint();
//      paint.setStrokeWidth(10.0F);
//      paint.setColor(-16776961);
//      paint.setStyle(Paint.Style.FILL_AND_STROKE);
//      Path path = new Path();
//      byte b = 0;
//      path.moveTo((float)(paramArrayOfPoint[0]).x, (float)(paramArrayOfPoint[0]).y);
//      int i = paramArrayOfPoint.length;
//      while (b < i) {
//        Point point = paramArrayOfPoint[b];
//        path.lineTo((float)point.x, (float)point.y);
//        b++;
//      }
//      path.close();
//      (new Canvas(paramBitmap)).drawPath(path, paint);
//      return;
//    }
//    throw new IllegalArgumentException("bitmap must be mutable!");
//  }
  
  private static int examTypeFromScanMode(int paramInt) {
    StringBuilder stringBuilder;
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unexpected scan mode:");
        stringBuilder.append(paramInt);
        throw new RuntimeException(stringBuilder.toString());
      case 2:
        return 2;
      case 1:
        break;
    } 
    return 1;
  }
  
  private double frameFatThicknessToPhysicalThicknessInMM(float paramFloat) {
   // ScanConversionParams scanConversionParams = this.probeService.makeScanConversionParams();
//    int i = scanConversionParams.getImageWidth();
//    int j = scanConversionParams.getImageHeight();
//    StringBuilder stringBuilder = new StringBuilder();
//    stringBuilder.append("scanConversionParam  image w:");
//    stringBuilder.append(i);
//    stringBuilder.append(" h:");
//    stringBuilder.append(j);
//    LogUtils.LOGI("fat", stringBuilder.toString());
//    float f = scanConversionParams.getDepthInCM();
    return /*(paramFloat * 10.0F / j * f)*/  8;
  }
  
//  private Rect getFatRoiRect(GrayFrame paramGrayFrame) {
//    ScanConversionParams scanConversionParams = this.probeService.makeScanConversionParams();
//    float f1 = scanConversionParams.getImageWidthInCM();
//    float f2 = scanConversionParams.getProbeImageDeadZoneInCM();
//    float f3 = paramGrayFrame.getWidth() / f1;
//    int i = (int)(2.5F * f3);
//    int j = (int)(5.5F * f3);
//    int k = (int)((f1 - 2.5F) * 0.5F * f3);
//    int m = (int)((f2 + 0.5F) * f3);
//    return new Rect(k, m, i + k, j + m);
//  }
  
//  private float getProbeMidDeadZoneDepthInGrayFrame(GrayFrame paramGrayFrame) {
//    ScanConversionParams scanConversionParams = this.probeService.makeScanConversionParams();
//    double d1 = scanConversionParams.getProbeImageDeadZoneInCM();
//    double d2 = scanConversionParams.getCmPerPixel();
//    Double.isNaN(d1);
//    return (float)(d1 / d2);
//  }
  
  private void initView() {
    //updateViewAndFatRefLineFromProbeState();
    setEnableUploadAndInputIDByDeviceId();
   // ScanConversionParams scanConversionParams = this.probeService.makeScanConversionParams();
//    LogUtils.LOGI("initView", scanConversionParams.toString());
//    Bitmap bitmap = Bitmap.createBitmap(scanConversionParams.getImageWidth(), scanConversionParams.getImageHeight(), Bitmap.Config.ARGB_8888);
////    this.view.showFrame(bitmap);
//    this.view.updateTgcDisplay((short)this.probeService.getGain());
//    this.view.showPtBfMode(this.ptbfMode);
  }
  
  private boolean isTargetAPActive() {
    String str = this.wifiHelper.getActiveApName();
    return (str.startsWith("CZT") || str.equals("AscannerDAP"));
  }
  
  private void loadEarIds() {
    LogUtils.LOGI("PigIds", "loadEarIds");
//    Disposable disposable = this.repository.getAllPigIds().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() {
//          public void accept(List<String> param1List) throws Exception {
//            MainViewPresenter.access$302(MainViewPresenter.this, param1List);
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append("comsumer size:");
//            stringBuilder.append(MainViewPresenter.this.earIds.size());
//            LogUtils.LOGI("PigIds", stringBuilder.toString());
//          }
//        });
//    this.subscriptions.add(disposable);
  }
  
//  private void scaledRoiToOriginCoordinate(Point paramPoint, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2) {
//    double d1 = paramPoint.x;
//    double d2 = paramFloat1;
//    Double.isNaN(d2);
//    paramPoint.x = d1 / d2;
//    d1 = paramPoint.y;
//    d2 = paramFloat2;
//    Double.isNaN(d2);
//    paramPoint.y = d1 / d2;
//    d1 = paramPoint.x;
//    d2 = paramInt1;
//    Double.isNaN(d2);
//    paramPoint.x = d1 + d2;
//    d1 = paramPoint.y;
//    d2 = paramInt2;
//    Double.isNaN(d2);
//    paramPoint.y = d1 + d2;
//  }
  
  private void setEnableUploadAndInputIDByDeviceId() {
//    boolean bool = this.repository.getDeviceId().startsWith("MYJCN1");
//    this.view.setEnableUpload(bool);
//    this.view.setEnableInputCustomID(bool ^ true);
  }
  
  private void updateDisplayBitmapSize(int paramInt1, int paramInt2) {
    Bitmap bitmap = this.reusedDisplayFrameBitmap;
    if (bitmap == null) {
      this.reusedDisplayFrameBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      return;
    } 
    if (bitmap.getWidth() != paramInt1 || this.reusedDisplayFrameBitmap.getHeight() != paramInt2) {
      this.reusedDisplayFrameBitmap.recycle();
      this.reusedDisplayFrameBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    } 
  }
  
//  private void updateScanTime() { this.currentScanTime = DateTime.now(); }
  
  private void updateStateByPtBfMode() {
//    boolean bool;
//    IMainView iMainView = this.view;
//    if (this.ptbfMode == 2) {
//      bool = true;
//    } else {
//      bool = false;
//    }
//    iMainView.setShowFatRefLine(bool);
//    if (this.ptbfMode == 1) {
//      this.probeService.switchToPtBfMode(ProbeState.PtBfMode.PT_MODE);
//    } else if (this.ptbfMode == 2) {
//      this.probeService.switchToPtBfMode(ProbeState.PtBfMode.BF_MODE);
//    } else {
//      throw new IllegalStateException("unexpected ptbfMode value.");
//    }
//    updateViewAndFatRefLineFromProbeState();
  }
  
//  private void updateViewAndFatRefLineFromProbeState() {
//    ScanConversionParams scanConversionParams = this.probeService.makeScanConversionParams();
//    LogUtils.LOGI("updateView", scanConversionParams.toString());
//    float f1 = scanConversionParams.getProbeImageDeadZoneInCM();
//    float f2 = scanConversionParams.getDepthInCM();
//    short s = (short)this.probeService.getGain();
////    this.view.updateTgcDisplay(s);
////    this.view.updateDepth(f1, f2);
////    this.view.setFrameViewDepthInMM(f1 * 10.0F, f2 * 10.0F);
//  }
//
//  public void changeFocus(float paramFloat) {
//    this.probeService.changeFocus((short)(int)(paramFloat + 0.5F));
//    updateViewAndFatRefLineFromProbeState();
//  }
//
//  public void changeTgc(short paramShort) {
//    paramShort = (short)((short)this.probeService.getGain() + paramShort);
//    this.probeService.changeGain(paramShort);
//    updateViewAndFatRefLineFromProbeState();
//  }
  
//  public void decreaseDepth() {
//    if (!this.probeService.isScanning())
//      return;
//    this.probeService.changeToPrevScanDepthGrade();
//    updateViewAndFatRefLineFromProbeState();
//  }
//
//  public void detectFat() {
//    if (this.currentDisplayFrame != null) {
//      if (this.probeService.isScanning())
//        return;
//      Rect rect = getFatRoiRect(this.currentDisplayFrame);
//      Optional optional = FatDetectUtil2.detectFat(this.currentDisplayFrame, rect);
//      if (optional.isPresent()) {
//        float f = getProbeMidDeadZoneDepthInGrayFrame(this.currentDisplayFrame);
//        new PointF(this.currentDisplayFrame.getWidth() * 0.5F, f);
//        PointF pointF = (PointF)optional.get();
//        StringBuilder stringBuilder2 = new StringBuilder();
//        stringBuilder2.append("roi left:");
//        stringBuilder2.append(rect.left);
//        stringBuilder2.append("  top:");
//        stringBuilder2.append(rect.top);
//        LogUtils.LOGI("fat", stringBuilder2.toString());
//        StringBuilder stringBuilder1 = new StringBuilder();
//        stringBuilder1.append("fatPosition   x:");
//        stringBuilder1.append(pointF.x);
//        stringBuilder1.append("  y:");
//        stringBuilder1.append(pointF.y);
//        LogUtils.LOGI("fat", stringBuilder1.toString());
//        stringBuilder1 = new StringBuilder();
//        stringBuilder1.append("frame  w:");
//        stringBuilder1.append(this.currentDisplayFrame.getWidth());
//        stringBuilder1.append("  h:");
//        stringBuilder1.append(this.currentDisplayFrame.getHeight());
//        LogUtils.LOGI("fat", stringBuilder1.toString());
//        double d2 = frameFatThicknessToPhysicalThicknessInMM(pointF.y - f);
//        double d1 = d2;
//        if (d2 > 45.0D)
//          d1 = 45.0D;
//        this.view.showBackfatInfo(d1);
//        this.view.showFatGuideLine((float)d1);
//        this.view.showToast(2131689641, new Object[0]);
//        return;
//      }
//      this.view.showToast(2131689643, new Object[0]);
//      return;
//    }
//  }
//
//  public void disableProbeNetwork() { this.wifiHelper.disableProbeNetwork(); }
//
//  public void enableNetwork(String paramString1, String paramString2) {
//    StringBuilder stringBuilder = new StringBuilder();
//    stringBuilder.append("enableNetwork  apName:");
//    stringBuilder.append(paramString1);
//    stringBuilder.append("  pwd:");
//    stringBuilder.append(paramString2);
//    LogUtils.LOGI("MainViewPresenter", stringBuilder.toString());
//    String str = paramString2;
//    if (paramString2.equals("AscannerDAP")) {
//      str = "12345678";
//      StringBuilder stringBuilder1 = new StringBuilder();
//      stringBuilder1.append("enableNetwork is default AP, use default pwd:");
//      stringBuilder1.append("12345678");
//      LogUtils.LOGI("MainViewPresenter", stringBuilder1.toString());
//    }
//    this.wifiHelper.enableNetwork(paramString1, str);
//  }
//
//  public void enableProbeButtonScan(boolean paramBoolean) {
//    if (this.probeService.isScanning())
//      stopScan();
//    this.enableProbeButtonScan = paramBoolean;
//  }
//
//  public int getClampedTgc(int paramInt) { return this.probeService.getClampedGain(paramInt); }
//
//  public List<String> getEarIds() { return this.earIds; }
//
//  public String getHostBaseUrl() { return this.repository.getHostBaseUrl(); }
//
//  public String getLastDeviceId() { return this.repository.getDeviceId(); }
//
//  public DateTime getScanTime() { return this.currentScanTime; }
//
//  public short getTgc() { return (short)this.probeService.getGain(); }
//
//  public void increaseDepth() {
//    if (!this.probeService.isScanning())
//      return;
//    this.probeService.changeToNextScanDepthGrade();
//    updateViewAndFatRefLineFromProbeState();
//  }
//
//  public void loadFrameAtProgress(int paramInt) {
//    this.replayProgress = paramInt;
//    GrayFrame grayFrame = this.frameSequence.getFrame(paramInt);
//    if (grayFrame != null) {
//      updateDisplayBitmapSize(grayFrame.getWidth(), grayFrame.getHeight());
//      grayFrame.getPixels(this.reusedDisplayFrameBitmap);
//      this.currentDisplayFrame = grayFrame;
//      this.view.showFrame(this.reusedDisplayFrameBitmap);
//      this.view.updateReplayProgress(paramInt);
//    }
//  }
//
//  public void loadFrameFromFile(String paramString) {
//    try {
//      boolean bool;
//      Bitmap bitmap = BitmapFactory.decodeFile(this.repository.getImageFile(paramString).getAbsolutePath());
//      if (!bitmap.isRecycled()) {
//        bool = true;
//      } else {
//        bool = false;
//      }
//      Preconditions.checkArgument(bool);
//      this.currentDisplayFrame = null;
//      this.view.showLoadedScreenShot(bitmap);
//      return;
//    } catch (IOException paramString) {
//      this.view.showToast(2131689579, new Object[0]);
//      return;
//    }
//  }
//
//  public void nextFrame() {
//    if (!this.isReplaying && !this.probeService.isScanning()) {
//      int i;
//      this.replayProgress++;
//      if (this.replayProgress > this.frameSequence.size() - 1) {
//        i = 0;
//      } else {
//        i = this.replayProgress;
//      }
//      this.replayProgress = i;
//      loadFrameAtProgress(this.replayProgress);
//    }
//  }
//
//  public void onBattery(short paramShort) {
//    StringBuilder stringBuilder = new StringBuilder();
//    stringBuilder.append("batteryData:");
//    stringBuilder.append(paramShort);
//    LogUtils.LOGI("battery", stringBuilder.toString());
//    int i = batteryDataToPercentage(paramShort);
//    stringBuilder = new StringBuilder();
//    stringBuilder.append("batteryPercentage:");
//    stringBuilder.append(i);
//    LogUtils.LOGI("battery", stringBuilder.toString());
//    this.view.updateBattery(i, false);
//  }
//
//  public void onConnected(String paramString, ProbeScanType paramProbeScanType) {
//    this.repository.putDeviceId(paramString);
//    this.isConnected = true;
//    this.view.showConnected(true);
//    this.view.setEnableStatePanel(true);
//    if (this.isReplaying)
//      toggleReplay();
//    switch (paramProbeScanType) {
//      case null:
//        this.view.setMainFrameViewScaleType(ImageView.ScaleType.CENTER_CROP);
//        break;
//      case LINEAR:
//        this.view.setMainFrameViewScaleType(ImageView.ScaleType.FIT_CENTER);
//        break;
//    }
//    LogUtils.LOGI("CONNECTION", "show connected.");
//    setEnableUploadAndInputIDByDeviceId();
//    updateStateByPtBfMode();
//    initView();
//  }
//
//  public void onDepthUpdated() { updateViewAndFatRefLineFromProbeState(); }
//
//  public void onDestroy() {
//    this.frameScanConversionThread.quit();
//    this.frameProcessThread.quit();
//    this.probeService.setProbeStateListener(null);
//    this.view = null;
//    GrayFrame grayFrame = this.lastCorrelationEnhancedFrame;
//    if (grayFrame != null)
//      grayFrame.release();
//  }
//
//  public void onDisconnected() {
//    this.isConnected = false;
//    this.view.showConnected(false);
//    this.view.setEnableStatePanel(false);
//    this.view.setEnableButtonsByScanningState(false);
//    this.view.dismissSendFirmwareProgressDialog();
//    this.probeService.stopScanning();
//  }
//
//  public void onFirmwareUpdateAck(int paramInt) {
//    if (paramInt == 0) {
//      this.view.showToast(2131689654, new Object[0]);
//    } else {
//      this.view.showToast(2131689653, new Object[0]);
//    }
//    this.view.dismissSendFirmwareProgressDialog();
//  }
//
//  public void onFirmwareUpdateProgress(int paramInt) { this.view.updateSendFirmwareProgress(paramInt); }
//
//  public void onGetFrame(RFFrame paramRFFrame, ScanConversionParams paramScanConversionParams) {
//    this.prevRF = paramRFFrame;
//    LogUtils.LOGI("render", "send frame to render.");
//    Message message = this.frameScanConversionHandler.obtainMessage();
//    message.obj = new Object[] { paramRFFrame, paramScanConversionParams };
//    message.what = 1;
//    message.arg1 = this.ptbfMode;
//    this.frameScanConversionHandler.removeMessages(1);
//    this.frameScanConversionHandler.sendMessage(message);
//  }
//
//  public void onMoveFatEndPoint(float paramFloat) { this.view.showBackfatInfo(paramFloat); }
//
//  public void onPregMeasure(PointF paramPointF1, PointF paramPointF2) {
//    double d1 = this.probeService.makeScanConversionParams().getCmPerPixel();
//    double d2 = PointF.length(paramPointF1.x - paramPointF2.x, paramPointF1.y - paramPointF2.y);
//    Double.isNaN(d2);
//    this.view.showPregDays((int)(d2 * d1));
//  }
//
//  public void onProbeButtonClicked() {
//    if (!this.enableProbeButtonScan)
//      return;
//    if (this.probeService.isScanning()) {
//      stopScan();
//      return;
//    }
//    startScan();
//  }
//
//  public void onReset() {
//    initView();
//    updateViewAndFatRefLineFromProbeState();
//  }
//
//  public void onRfid(String paramString) {
//    updateEarId(paramString);
//    this.view.showID(paramString);
//  }
//
//  public void onScanId() {
//    if (!this.probeService.isScanning())
//      this.view.goToScanQrcode();
//  }
//
//  public void onStart() {
//    loadEarIds();
//    Disposable disposable = RxBus.observe(WifiStateBroadcastReceiver.WifiState.class).subscribe(new Consumer<WifiStateBroadcastReceiver.WifiState>() {
//          public void accept(WifiStateBroadcastReceiver.WifiState param1WifiState) throws Exception {
//            MainViewPresenter mainViewPresenter;
//            LogUtils.LOGI("wifistate", param1WifiState.name());
//            if (param1WifiState.equals(WifiStateBroadcastReceiver.WifiState.CONNECTED)) {
//              MainViewPresenter.this.view.dismissNoProbeFoundDialog();
//              mainViewPresenter = MainViewPresenter.this.wifiHelper.getConnectionInfo().getSSID().replace("\"", "");
//              StringBuilder stringBuilder = new StringBuilder();
//              stringBuilder.append("connected ");
//              stringBuilder.append(mainViewPresenter);
//              LogUtils.LOGI("wifistate", stringBuilder.toString());
//              return;
//            }
//            if (mainViewPresenter.equals(WifiStateBroadcastReceiver.WifiState.DISCONNECTED)) {
//              MainViewPresenter.this.wifiHelper.startScan();
//              return;
//            }
//            if (mainViewPresenter.equals(WifiStateBroadcastReceiver.WifiState.ENABLED)) {
//              MainViewPresenter.this.scanWifi();
//              return;
//            }
//            if (mainViewPresenter.equals(WifiStateBroadcastReceiver.WifiState.DISABLED))
//              return;
//            if (mainViewPresenter.equals(WifiStateBroadcastReceiver.WifiState.SCAN_FINISHED)) {
//              List list = MainViewPresenter.this.wifiHelper.getScanResults();
//              StringBuilder stringBuilder2 = new StringBuilder();
//              stringBuilder2.append("scanresults:");
//              stringBuilder2.append(list.size());
//              LogUtils.LOGI("wifistate", stringBuilder2.toString());
//              MainViewPresenter.this.apNames.clear();
//              for (ScanResult scanResult : list) {
//                StringBuilder stringBuilder = new StringBuilder();
//                stringBuilder.append("scanresult(ssid):");
//                stringBuilder.append(scanResult.SSID);
//                LogUtils.LOGI("wifistate", stringBuilder.toString());
//                if (scanResult.SSID.contains("CZT")) {
//                  MainViewPresenter.this.apNames.add(scanResult.SSID);
//                  continue;
//                }
//                if (scanResult.SSID.contains("AscannerDAP"))
//                  MainViewPresenter.this.apNames.add(scanResult.SSID);
//              }
//              if (MainViewPresenter.this.apNames.contains(MainViewPresenter.this.wifiHelper.getActiveApName()))
//                return;
//              if (MainViewPresenter.this.apNames.size() > 1) {
//                MainViewPresenter.this.view.showWifiListDialog((String[])MainViewPresenter.this.apNames.toArray(new String[MainViewPresenter.this.apNames.size()]));
//                return;
//              }
//              if (MainViewPresenter.this.apNames.size() < 1) {
//                MainViewPresenter.this.view.showNoProbeFoundDialogWithDelay(2131689548, 2131689531, 3000);
//                return;
//              }
//              StringBuilder stringBuilder1 = new StringBuilder();
//              stringBuilder1.append("enableNetwork  ap:");
//              stringBuilder1.append((String)MainViewPresenter.this.apNames.get(0));
//              stringBuilder1.append("  pwd:");
//              stringBuilder1.append((String)MainViewPresenter.this.apNames.get(0));
//              LogUtils.LOGI("wifistate", stringBuilder1.toString());
//              mainViewPresenter.enableNetwork((String)mainViewPresenter.apNames.get(0), (String)MainViewPresenter.this.apNames.get(0));
//              return;
//            }
//            mainViewPresenter.equals(WifiStateBroadcastReceiver.WifiState.WRONG_PWD);
//          }
//        });
//    this.subscriptions = new CompositeDisposable();
//    this.subscriptions.add(disposable);
//    if (!this.wifiHelper.isLocationServiceEnabled()) {
//      if (Build.VERSION.SDK_INT >= 21) {
//        this.view.showNeedLocationService();
//        return;
//      }
//    } else {
//      if (!this.wifiHelper.isWifiEnabled()) {
//        this.wifiHelper.openWifi();
//        return;
//      }
//      if (!isTargetAPActive())
//        scanWifi();
//    }
//  }
//
//  public void onStop() {
//    stopScan();
//    this.subscriptions.clear();
//  }
//
//  public void onUpdateTgc(short paramShort) { this.view.updateTgcDisplay(paramShort); }
//
//  public void onUpload() {
//    this.probeService.disconnect();
//    this.view.goToUpload();
//    disableProbeNetwork();
//  }
//
//  public void openFileDialog() {
//    stopReplay();
//    try {
//      File file = this.repository.getImageFileDir();
//      String str = this.repository.getImageFileExtension();
//      this.view.showOpenFileDialog(file.getAbsolutePath(), str);
//      return;
//    } catch (IOException iOException) {
//      this.view.showToast(2131689517, new Object[0]);
//      return;
//    }
//  }
//
//  public void openSettings() {
//    stopReplay();
//    this.view.showUserSettingsDialog();
//  }
//
//  public void prevFrame() {
//    if (!this.isReplaying && !this.probeService.isScanning()) {
//      int j = --this.replayProgress;
//      int i = j;
//      if (j < 0)
//        i = this.frameSequence.size() - 1;
//      this.replayProgress = i;
//      loadFrameAtProgress(this.replayProgress);
//    }
//  }
//
//  public File saveCurrentFrame() {
//    stopReplay();
//    if (this.frameSequence.size() < 1) {
//      this.view.showToast(2131689644, new Object[0]);
//      return null;
//    }
//    bitmap = this.view.captureCurrentFrame();
//    String str = this.earId;
//    int i = examTypeFromScanMode(this.ptbfMode);
//    try {
//      this.repository.saveImageFile(bitmap, i, str, this.currentScanTime);
//      return this.repository.getImageFile(this.currentScanTime);
//    } catch (IOException bitmap) {
//      this.view.showToast(2131689647, new Object[0]);
//      bitmap.printStackTrace();
//      throw new RuntimeException(bitmap);
//    }
//  }
//
//  public void saveCurrentFramesToVideoFile() {
//    stopReplay();
//    if (this.frameSequence.size() == 0) {
//      this.view.showToast(2131689644, new Object[0]);
//      return;
//    }
//    int i = examTypeFromScanMode(this.ptbfMode);
//    this.repository.saveVideoFile(this.frameSequence, i, this.currentScanTime, this.earId).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
//          public void onComplete() {
//            MainViewPresenter.this.view.showToast(2131689650, new Object[0]);
//            MainViewPresenter.this.view.dismissSavingVideoDialog();
//          }
//
//          public void onError(Throwable param1Throwable) { MainViewPresenter.this.view.showToast(2131689649, new Object[0]); }
//
//          public void onNext(Integer param1Integer) { MainViewPresenter.this.view.updateSavingVideoDialogProgress(param1Integer.intValue()); }
//
//          public void onSubscribe(Disposable param1Disposable) { MainViewPresenter.this.view.showSavingVideoDialog(MainViewPresenter.this.frameSequence.size()); }
//        });
//  }
//
//  public boolean savePDFReportAndExportRecord(DateTime paramDateTime, String paramString1, float paramFloat, boolean paramBoolean, String paramString2, File paramFile) {
//    String str2;
//    stopReplay();
//    try {
//      str2 = this.repository.getDeviceId();
//      if (this.ptbfMode == 1) {
//        String str = "孕检";
//        ExamRecord examRecord1 = new ExamRecord(paramDateTime.getMillis(), str2, paramString1, "", str, paramFloat, paramBoolean, paramString2);
//        this.repository.insertExamRecord(examRecord1);
//        this.repository.savePDFReportFile(examRecord1, paramFile);
//        this.repository.exportExcelRecord(examRecord1);
//        return true;
//      }
//    } catch (IOException paramDateTime) {
//      paramDateTime.printStackTrace();
//      return false;
//    }
//    String str1 = "背膘";
//    ExamRecord examRecord = new ExamRecord(paramDateTime.getMillis(), str2, paramString1, "", str1, paramFloat, paramBoolean, paramString2);
//    this.repository.insertExamRecord(examRecord);
//    this.repository.savePDFReportFile(examRecord, paramFile);
//    this.repository.exportExcelRecord(examRecord);
//    return true;
//  }
//
//  public void scanWifi() {
//    LogUtils.LOGI("MainViewPresenter", "start wifi scan");
//    Preconditions.checkState(this.wifiHelper.startScan());
//  }
//
//  public void setHostBaseUrl(String paramString) {
//    this.repository.putHostBaseUrl(paramString);
//    ApiService.setApiBaseUrl(paramString);
//  }
//
//  public void setProbeWifi(String paramString1, String paramString2) { this.probeService.setProbeWifi(paramString1, paramString2); }
//
//  public void setTgc(short paramShort) {
//    StringBuilder stringBuilder = new StringBuilder();
//    stringBuilder.append("setTgc isScanning:");
//    stringBuilder.append(this.probeService.isScanning());
//    LogUtils.LOGI("TgcSeekBar", stringBuilder.toString());
//    if (!this.probeService.isScanning())
//      return;
//    this.probeService.changeGain(paramShort);
//    updateViewAndFatRefLineFromProbeState();
//  }
//
//  protected void setTxCycles(short paramShort) { this.probeService.setTxCycles(paramShort); }
//
//  public void startScan() {
//    if (this.isReplaying)
//      toggleReplay();
//    this.currentDisplayFrame = null;
//    this.frameSequence.clear();
//    this.view.showFreeze(false);
//    this.view.setEnableButtonsByScanningState(true);
//    this.view.updateReplayProgress(0);
//    this.view.setReplayMax(this.frameSequence.size() - 1);
//    this.view.setScanningState(true);
//    this.probeService.startScanning();
//  }
//
//  public void stopReplay() {
//    if (this.isReplaying)
//      toggleReplay();
//  }
//
//  public void stopScan() {
//    this.probeService.stopScanning();
//    this.frameScanConversionHandler.removeMessages(1);
//    this.frameProcessHandler.removeMessages(1);
//    updateScanTime();
//    this.replayProgress = 0;
//    this.view.setReplayMax(this.frameSequence.size() - 1);
//    this.view.setEnableButtonsByScanningState(false);
//    this.view.setScanningState(false);
//    this.view.showFreeze(true);
//  }
//
//  public void togglePtBfMode() {
//    if (!this.probeService.isScanning())
//      return;
//    int i = this.ptbfMode;
//    byte b = 2;
//    if (i == 2)
//      b = 1;
//    this.ptbfMode = b;
//    this.view.showPtBfMode(this.ptbfMode);
//    updateStateByPtBfMode();
//  }
//
//  public boolean toggleReplay() {
//    this.isReplaying ^= true;
//    enableProbeButtonScan(this.isReplaying ^ true);
//    this.view.updateReplayState(this.isReplaying);
//    this.mainHandler.post(this.replayFrameRunnable);
//    return this.isReplaying;
//  }
  
  public void updateEarId(String paramString) { this.earId = paramString; }
  
  public void updateFirmware() { // Byte code:
      //   0: aload_0
      //   1: getfield isReplaying : Z
      //   4: ifeq -> 12
      //   7: aload_0
      //   8: invokevirtual toggleReplay : ()Z
      //   11: pop
      //   12: aload_0
      //   13: getfield isConnected : Z
      //   16: ifne -> 36
      //   19: aload_0
      //   20: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   23: ldc_w 2131689645
      //   26: iconst_0
      //   27: anewarray java/lang/Object
      //   30: invokeinterface showToast : (I[Ljava/lang/Object;)V
      //   35: return
      //   36: aload_0
      //   37: getfield probeService : Lcom/lee/arrayscan2/service/ProbeService;
      //   40: invokevirtual isScanning : ()Z
      //   43: ifeq -> 63
      //   46: aload_0
      //   47: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   50: ldc_w 2131689567
      //   53: iconst_0
      //   54: anewarray java/lang/Object
      //   57: invokeinterface showToast : (I[Ljava/lang/Object;)V
      //   62: return
      //   63: invokestatic getFirmwareFile : ()Ljava/io/File;
      //   66: astore #4
      //   68: aload #4
      //   70: invokevirtual isFile : ()Z
      //   73: ifne -> 99
      //   76: aload_0
      //   77: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   80: ldc_w 2131689568
      //   83: iconst_1
      //   84: anewarray java/lang/Object
      //   87: dup
      //   88: iconst_0
      //   89: invokestatic getFirmwareFilePathInExternalStorage : ()Ljava/lang/String;
      //   92: aastore
      //   93: invokeinterface showToast : (I[Ljava/lang/Object;)V
      //   98: return
      //   99: aload #4
      //   101: invokevirtual length : ()J
      //   104: ldc2_w 1048576
      //   107: lcmp
      //   108: ifle -> 128
      //   111: aload_0
      //   112: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   115: ldc_w 2131689569
      //   118: iconst_0
      //   119: anewarray java/lang/Object
      //   122: invokeinterface showToast : (I[Ljava/lang/Object;)V
      //   127: return
      //   128: aconst_null
      //   129: astore_3
      //   130: aconst_null
      //   131: astore_1
      //   132: new java/io/FileInputStream
      //   135: dup
      //   136: aload #4
      //   138: invokespecial <init> : (Ljava/io/File;)V
      //   141: astore_2
      //   142: aload #4
      //   144: invokevirtual length : ()J
      //   147: l2i
      //   148: newarray byte
      //   150: astore_1
      //   151: aload_2
      //   152: aload_1
      //   153: invokevirtual read : ([B)I
      //   156: pop
      //   157: aload_0
      //   158: getfield probeService : Lcom/lee/arrayscan2/service/ProbeService;
      //   161: aload_1
      //   162: invokevirtual updateFirmware : ([B)V
      //   165: aload_0
      //   166: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   169: invokeinterface showSendFirmwareProgressDialog : ()V
      //   174: aload_2
      //   175: invokevirtual close : ()V
      //   178: return
      //   179: astore_1
      //   180: goto -> 232
      //   183: goto -> 194
      //   186: astore_3
      //   187: aload_1
      //   188: astore_2
      //   189: aload_3
      //   190: astore_1
      //   191: goto -> 232
      //   194: aload_2
      //   195: astore_1
      //   196: aload_0
      //   197: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   200: invokeinterface dismissSendFirmwareProgressDialog : ()V
      //   205: aload_2
      //   206: astore_1
      //   207: aload_0
      //   208: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   211: ldc_w 2131689622
      //   214: iconst_0
      //   215: anewarray java/lang/Object
      //   218: invokeinterface showToast : (I[Ljava/lang/Object;)V
      //   223: aload_2
      //   224: ifnull -> 231
      //   227: aload_2
      //   228: invokevirtual close : ()V
      //   231: return
      //   232: aload_2
      //   233: ifnull -> 240
      //   236: aload_2
      //   237: invokevirtual close : ()V
      //   240: aload_1
      //   241: athrow
      //   242: aload_0
      //   243: getfield view : Lcom/lee/arrayscan2/ui/IMainView;
      //   246: ldc_w 2131689568
      //   249: iconst_1
      //   250: anewarray java/lang/Object
      //   253: dup
      //   254: iconst_0
      //   255: invokestatic getFirmwareFilePathInExternalStorage : ()Ljava/lang/String;
      //   258: aastore
      //   259: invokeinterface showToast : (I[Ljava/lang/Object;)V
      //   264: return
      //   265: astore_1
      //   266: goto -> 242
      //   269: astore_1
      //   270: aload_3
      //   271: astore_2
      //   272: goto -> 194
      //   275: astore_1
      //   276: goto -> 183
      //   279: astore_1
      //   280: return
      //   281: astore_2
      //   282: goto -> 240
      // Exception table:
      //   from	to	target	type
      //   63	68	265	java/io/IOException
      //   132	142	269	java/io/IOException
      //   132	142	186	finally
      //   142	174	275	java/io/IOException
      //   142	174	179	finally
      //   174	178	279	java/io/IOException
      //   196	205	186	finally
      //   207	223	186	finally
      //   227	231	279	java/io/IOException
      //   236	240	281	java/io/IOException }

      // public void updateFrameViewSize(int paramInt1, int paramInt2) { this.probeService.setScanConversionImageSize(paramInt1, paramInt2); }

//  public void updatePrefParams() {}
//}


/* Location:              D:\androidWord\apkedit\dex2jar-2.0\classes-dex2jar.jar!\com\lee\arrayscan\\ui\MainViewPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
  }}