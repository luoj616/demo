package demo.luojun.com.demo.activity.testactivity;

/*import com.google.common.base.Preconditions;
import com.lee.arrayscan2.library.ArrayScanCmdCtrlParams;
import com.lee.arrayscan2.library.ArrayScanTableParams;
import com.lee.arrayscan2.library.ConvexProbeState;
import com.lee.arrayscan2.library.LinearProbeState;
import com.lee.arrayscan2.library.ProbeScanType;
import com.lee.arrayscan2.library.ProbeState;
import com.lee.arrayscan2.library.ScanConversionParams;
import com.lee.arrayscan2.model.RFFrame;
import com.lee.arrayscan2.service.net.PacketInputStream;
import com.lee.arrayscan2.service.net.PacketOutputStream;
import com.lee.arrayscan2.service.net.WifiHelper;
import com.lee.arrayscan2.service.packets.BatteryAckPacket;
import com.lee.arrayscan2.service.packets.BatteryPacket;
import com.lee.arrayscan2.service.packets.CmdRfidPacket;
import com.lee.arrayscan2.service.packets.ConnectAckPacket;
import com.lee.arrayscan2.service.packets.ConnectPacket;
import com.lee.arrayscan2.service.packets.DisconnectedPacket;
import com.lee.arrayscan2.service.packets.Packet;
import com.lee.arrayscan2.service.packets.ScanGetFrameAckPacket;
import com.lee.arrayscan2.service.packets.ScanGetFramePacket;
import com.lee.arrayscan2.service.packets.ScanStartPacket;
import com.lee.arrayscan2.service.packets.ScanStopPacket;
import com.lee.arrayscan2.service.packets.SendProgressPacket;
import com.lee.arrayscan2.service.packets.Set2DRxTgcProfilePacket;
import com.lee.arrayscan2.service.packets.Set2DTxCyclesPacket;
import com.lee.arrayscan2.service.packets.UpdateFirmwareAckPacket;
import com.lee.arrayscan2.service.packets.UpdateFirmwarePacket;
import com.lee.arrayscan2.service.packets.arrayscan.CmdBmodeRawData;
import com.lee.arrayscan2.service.packets.arrayscan.CmdCtrlRegsPacket;
import com.lee.arrayscan2.service.packets.arrayscan.CmdGainCurve;
import com.lee.arrayscan2.service.packets.arrayscan.CmdLogTable;
import com.lee.arrayscan2.service.packets.arrayscan.CmdPackages;
import com.lee.arrayscan2.service.packets.arrayscan.CmdRxDelayCurve;
import com.lee.arrayscan2.service.packets.arrayscan.CmdStartStopScan;
import com.lee.arrayscan2.service.packets.arrayscan.CmdTxDelayCurve;
import com.lee.arrayscan2.utils.LogUtils;*/

class ProbeService /*extends Service implements ProbeState*/ {
  /*  private static final String PROBE_IP = "192.168.0.8";

    private static final int PROBE_PORT = 8888;

    private final IBinder binder = new ProbeServiceBinder();

    private PacketHandler packetHandler;

    private ProbeSocketThread probeSocketThread;

    private ProbeState probeState;

    private ProbeStateListener probeStateListener;

    private AtomicBoolean skipFrame = new AtomicBoolean(false);

    private Runnable timeoutRunnable = new Runnable() {
        public void run() { ProbeService.this.probeSocketThread.disconnectProbe(); }
    };

    private WifiHelper wifiHelper;

    private String getDeviceId() { return this.wifiHelper.getActiveApName(); }

    private boolean isFrameDataSizeFitScanConversionParams(int paramInt1, int paramInt2, int paramInt3) { return (paramInt1 == paramInt2 * paramInt3); }

    private boolean isProbeIdle() {
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        return (probeSocketThread1 != null && probeSocketThread1.isAlive() && !this.packetHandler.isScanning);
    }

    private void sendChangeStatePacket(Packet paramPacket) {
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null && probeSocketThread1.isAlive()) {
            if (this.packetHandler.isScanning) {
                this.probeSocketThread.sendPacket(new ScanStopPacket());
                this.probeSocketThread.sendPacket(paramPacket);
                this.probeSocketThread.sendPacket(new ScanStartPacket((short)0));
                return;
            }
            this.probeSocketThread.sendPacket(paramPacket);
        }
    }

    private void sendChangeStatePackets(Packet[] paramArrayOfPacket, int paramInt) {
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null && probeSocketThread1.isAlive()) {
            boolean bool = this.packetHandler.isScanning;
            byte b = 0;
            if (bool) {
                this.probeSocketThread.sendPacket(new ScanStopPacket());
                int j = paramArrayOfPacket.length;
                b = 0;
                while (true) {
                    if (b < j) {
                        packet = paramArrayOfPacket[b];
                        this.probeSocketThread.sendPacket(packet);
                        long l = paramInt;
                        try {
                            Thread.sleep(l);
                        } catch (InterruptedException packet) {}
                        b++;
                        continue;
                    }
                    this.probeSocketThread.sendPacket(new ScanStartPacket((short)0));
                    return;
                }
            }
            int i = paramArrayOfPacket.length;
            while (true) {
                if (b < i) {
                    packet = paramArrayOfPacket[b];
                    this.probeSocketThread.sendPacket(packet);
                    long l = paramInt;
                    try {
                        Thread.sleep(l);
                    } catch (InterruptedException packet) {}
                    b++;
                    continue;
                }
                return;
            }
        }
    }

    private void sendPackets(Packet[] paramArrayOfPacket, int paramInt) {
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null && probeSocketThread1.isAlive()) {
            int i = paramArrayOfPacket.length;
            byte b = 0;
            while (true) {
                if (b < i) {
                    packet = paramArrayOfPacket[b];
                    this.probeSocketThread.sendPacket(packet);
                    long l = paramInt;
                    try {
                        Thread.sleep(l);
                    } catch (InterruptedException packet) {}
                    b++;
                    continue;
                }
                return;
            }
        }
    }

    private void setProbeStateByScanType(ProbeScanType paramProbeScanType) {
        switch (paramProbeScanType) {
            default:
                throw new RuntimeException("Unknown probeScanType at setProbeStateByScanType.");
            case null:
                this.probeState = new ConvexProbeState();
                break;
            case LINEAR:
                this.probeState = new LinearProbeState();
                break;
        }
        reset();
        this.probeState.loadDefaultTableParams();
    }

    private void updateProbeTypeByAPName() {
        String str = this.wifiHelper.getActiveApName();
        ProbeScanType probeScanType = ProbeScanType.CONVEX;
        if (str.startsWith("CZTC1")) {
            probeScanType = ProbeScanType.CONVEX;
        } else if (str.startsWith("CZTL1")) {
            probeScanType = ProbeScanType.LINEAR;
        }
        setProbeStateByScanType(probeScanType);
    }

    public void changeFocus(float paramFloat) {
        this.probeState.changeFocus(paramFloat);
        sendChangeStatePacket(new CmdCtrlRegsPacket(this.probeState.makeArrayScanCmdCtrlParams()));
    }

    public void changeGain(int paramInt) {
        this.probeState.changeGain(paramInt);
        sendChangeStatePacket(new CmdCtrlRegsPacket(this.probeState.makeArrayScanCmdCtrlParams()));
    }

    public void changeToNextScanDepthGrade() {
        this.probeState.changeToNextScanDepthGrade();
        sendChangeStatePacket(new CmdCtrlRegsPacket(this.probeState.makeArrayScanCmdCtrlParams()));
    }

    public void changeToPrevScanDepthGrade() {
        this.probeState.changeToPrevScanDepthGrade();
        sendChangeStatePacket(new CmdCtrlRegsPacket(this.probeState.makeArrayScanCmdCtrlParams()));
    }

    public void disconnect() {
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null)
            probeSocketThread1.disconnectProbe();
    }

    public int getClampedGain(int paramInt) { return this.probeState.getClampedGain(paramInt); }

    public float getCurrentFocusDepthMM() { return this.probeState.getCurrentFocusDepthMM(); }

    public float getCurrentScanDepthMM() { return this.probeState.getCurrentScanDepthMM(); }

    public int getGain() { return this.probeState.getGain(); }

    public int getMaxGainValue() { return this.probeState.getMaxGainValue(); }

    public ProbeScanType getProbeScanType() { return this.probeState.getProbeScanType(); }

    public ProbeState.PtBfMode getPtBfMode() { return this.probeState.getPtBfMode(); }

    public boolean isScanning() { return this.packetHandler.isScanning(); }

    public void loadArrayScanCmdCtrlParamsFromConfigFile() { this.probeState.loadArrayScanCmdCtrlParamsFromConfigFile(); }

    public void loadDefaultTableParams() { this.probeState.loadDefaultTableParams(); }

    public ArrayScanCmdCtrlParams makeArrayScanCmdCtrlParams() { return this.probeState.makeArrayScanCmdCtrlParams(); }

    public ScanConversionParams makeScanConversionParams() { return this.probeState.makeScanConversionParams(); }

    public IBinder onBind(Intent paramIntent) { return this.binder; }

    public void onCreate() {
        super.onCreate();
        this.wifiHelper = new WifiHelper(this);
        this.packetHandler = new PacketHandler(getMainLooper());
        this.probeSocketThread = new ProbeSocketThread(null);
        this.probeSocketThread.start();
        LogUtils.LOGI("probe", "probe service onCreate");
    }

    public void onDestroy() {
        probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null)
            try {
                probeSocketThread1.stopRunning();
            } catch (InterruptedException probeSocketThread1) {}
        LogUtils.LOGI("probe", "probe service onDestroy");
        super.onDestroy();
    }

    public boolean onUnbind(Intent paramIntent) { return false; }

    public void reset() {
        this.probeState.reset();
        this.probeStateListener.onReset();
    }

    protected void sendArrayScanParams() {
        updateProbeTypeByAPName();
        ArrayScanCmdCtrlParams arrayScanCmdCtrlParams = this.probeState.makeArrayScanCmdCtrlParams();
        sendPackets(new Packet[] { new CmdPackages(1024, 61440, 1024, 1024), new CmdCtrlRegsPacket(arrayScanCmdCtrlParams), new CmdLogTable(ArrayScanTableParams.getLogTable()), new CmdGainCurve(ArrayScanTableParams.getTgcTable()), new CmdRxDelayCurve(ArrayScanTableParams.getRxDelayTable()), new CmdTxDelayCurve(ArrayScanTableParams.getTxDelayTable()) }1000);
    }

    public void setArrayScanCmdCtrlParams(ArrayScanCmdCtrlParams paramArrayScanCmdCtrlParams) { this.probeState.setArrayScanCmdCtrlParams(paramArrayScanCmdCtrlParams); }

    public void setMaxGainValue(int paramInt) { this.probeState.setMaxGainValue(paramInt); }

    public void setProbeStateListener(ProbeStateListener paramProbeStateListener) { this.probeStateListener = paramProbeStateListener; }

    public void setProbeWifi(String paramString1, String paramString2) {}

    public void setScanConversionImageSize(int paramInt1, int paramInt2) { this.probeState.updateOutputImageSize(paramInt1, paramInt2); }

    public void setTxCycles(short paramShort) {
        boolean bool = true;
        if (paramShort < 1 || paramShort > 2)
            bool = false;
        Preconditions.checkArgument(bool, "cycles must be 1 or 2.");
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null && probeSocketThread1.isAlive()) {
            if (this.packetHandler.isScanning()) {
                this.probeSocketThread.sendPacket(new ScanStopPacket());
                this.probeSocketThread.sendPacket(new Set2DTxCyclesPacket(paramShort));
                this.probeSocketThread.sendPacket(new ScanStartPacket((short)0));
                return;
            }
            this.probeSocketThread.sendPacket(new Set2DTxCyclesPacket(paramShort));
        }
    }

    public void startScanning() {
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null) {
            if (!probeSocketThread1.isAlive())
                return;
            LogUtils.LOGI("packet", "start scanning.");
            this.packetHandler.setScanning(true);
            return;
        }
    }

    public void stopScanning() {
        ProbeSocketThread probeSocketThread1 = this.probeSocketThread;
        if (probeSocketThread1 != null && probeSocketThread1.isAlive()) {
            LogUtils.LOGI("packet", "stop scanning.");
            this.packetHandler.setScanning(false);
        }
    }

    public void switchToPtBfMode(ProbeState.PtBfMode paramPtBfMode) {
        this.probeState.switchToPtBfMode(paramPtBfMode);
        sendChangeStatePacket(new CmdCtrlRegsPacket(this.probeState.makeArrayScanCmdCtrlParams()));
    }

    public void updateFirmware(byte[] paramArrayOfByte) {
        if (!isProbeIdle())
            return;
        this.probeSocketThread.sendPacket(new UpdateFirmwarePacket(paramArrayOfByte));
    }

    public void updateOutputImageSize(int paramInt1, int paramInt2) { this.probeState.updateOutputImageSize(paramInt1, paramInt2); }

    private class PacketHandler extends Handler {
        public static final int BATTERY_INTERVAL_MS = 2000;

        private static final short IDLE_FRAMES = 0;

        public static final int TIMEOUT_MS = 5000;

        public PacketHandler(Looper param1Looper) { super(param1Looper); }

        public void handleMessage(Message param1Message) {
            StringBuilder stringBuilder1;
            byte[] arrayOfByte;
            ScanConversionParams scanConversionParams1;
            ScanConversionParams scanConversionParams2;
            SendProgressPacket sendProgressPacket;
            String str;
            int j;
            int i;
            LogUtils.LOGI("CONNECTION", "start of handle message.");
            removeCallbacks(ProbeService.this.timeoutRunnable);
            postDelayed(ProbeService.this.timeoutRunnable, 5000L);
            Packet packet = (Packet)param1Message.obj;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("handle msg:");
            stringBuilder2.append(packet.getClass().getSimpleName());
            LogUtils.LOGI("HANDLE_MESSAGE", stringBuilder2.toString());
            switch (packet.getId()) {
                case 32776:
                    arrayOfByte = ((CmdBmodeRawData)packet).getBmodeFrameData();
                    scanConversionParams2 = ProbeService.this.probeState.makeScanConversionParams();
                    i = scanConversionParams2.getLines();
                    j = scanConversionParams2.getSamplesPerLine();
                    if (ProbeService.this.isFrameDataSizeFitScanConversionParams(arrayOfByte.length, i, j)) {
                        RFFrame rFFrame = new RFFrame(i, j, arrayOfByte);
                        if (ProbeService.this.probeStateListener != null && isScanning() && !ProbeService.this.skipFrame.getAndSet(false))
                            ProbeService.this.probeStateListener.onGetFrame(rFFrame, scanConversionParams2);
                    }
                    break;
                case 28675:
                    scanConversionParams1 = (UpdateFirmwareAckPacket)scanConversionParams2;
                    if (ProbeService.this.probeStateListener != null)
                        ProbeService.this.probeStateListener.onFirmwareUpdateAck(scanConversionParams1.getResultCode());
                    break;
                case 8195:
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("frame ack. isScanning:");
                    stringBuilder1.append(this.isScanning);
                    LogUtils.LOGI("changeTgc", stringBuilder1.toString());
                    if (this.isScanning) {
                        stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("send get frame. isScanning:");
                        stringBuilder1.append(this.isScanning);
                        LogUtils.LOGI("changeTgc", stringBuilder1.toString());
                        ProbeService.this.probeSocketThread.sendPacket(new ScanGetFramePacket((short)0));
                        if (!ProbeService.this.skipFrame.getAndSet(false)) {
                            byte[] arrayOfByte1 = ((ScanGetFrameAckPacket)scanConversionParams1).getFrameData().getData();
                            scanConversionParams1 = ProbeService.this.probeState.makeScanConversionParams();
                            i = scanConversionParams1.getLines();
                            j = scanConversionParams1.getSamplesPerLine();
                            if (ProbeService.this.isFrameDataSizeFitScanConversionParams(arrayOfByte1.length, i, j)) {
                                RFFrame rFFrame = new RFFrame(i, j, arrayOfByte1);
                                if (ProbeService.this.probeStateListener != null)
                                    ProbeService.this.probeStateListener.onGetFrame(rFFrame, scanConversionParams1);
                            }
                        }
                    }
                    break;
                case 255:
                    sendProgressPacket = (SendProgressPacket)scanConversionParams1;
                    if (sendProgressPacket.getSendPacketId() == 28674) {
                        stringBuilder1 = new StringBuilder();
                        stringBuilder1.append("handle firmware progress:");
                        stringBuilder1.append(sendProgressPacket.getSendProgressInPercentage());
                        LogUtils.LOGI("CONNECTION", stringBuilder1.toString());
                        if (ProbeService.this.probeStateListener != null)
                            ProbeService.this.probeStateListener.onFirmwareUpdateProgress(sendProgressPacket.getSendProgressInPercentage());
                    }
                    break;
                case 14:
                    str = ((CmdRfidPacket)sendProgressPacket).getRfid();
                    if (ProbeService.this.probeStateListener != null)
                        ProbeService.this.probeStateListener.onRfid(str);
                    break;
                case 13:
                    if (ProbeService.this.probeStateListener != null)
                        ProbeService.this.probeStateListener.onProbeButtonClicked();
                    break;
                case 3:
                    if (ProbeService.this.probeStateListener != null)
                        ProbeService.this.probeStateListener.onBattery(((BatteryAckPacket)str).getVoltageData());
                    postBatteryPacket(2000);
                    break;
                case 2:
                    if (isScanning()) {
                        postBatteryPacket(2000);
                        break;
                    }
                    ProbeService.this.probeSocketThread.sendPacket(str);
                    break;
                case 0:
                    ProbeService.this.sendArrayScanParams();
                    LogUtils.LOGE("CONNECTION", "after send setting params.");
                    if (ProbeService.this.probeStateListener != null)
                        ProbeService.this.probeStateListener.onConnected(ProbeService.this.getDeviceId(), ProbeService.this.getProbeScanType());
                    break;
                case -1:
                    if (ProbeService.this.probeStateListener != null) {
                        ProbeService.this.packetHandler.setScanning(false);
                        ProbeService.this.probeStateListener.onDisconnected();
                    }
                    break;
            }
            LogUtils.LOGI("CONNECTION", "end of handle message.");
            super.handleMessage(param1Message);
        }

        public boolean isScanning() { return this.isScanning; }

        public void postBatteryPacket(int param1Int) {
            Message message = obtainMessage();
            message.obj = new BatteryPacket();
            sendMessageDelayed(message, param1Int);
        }

        public void postDisconnected() {
            Message message = obtainMessage();
            message.obj = new DisconnectedPacket();
            sendMessageAtFrontOfQueue(message);
        }

        public void setScanning(boolean param1Boolean) {
            if (param1Boolean) {
                ProbeService.this.probeSocketThread.sendPacket(new CmdStartStopScan((short)-1));
            } else {
                ProbeService.this.probeSocketThread.sendPacket(new CmdStartStopScan((short)0));
            }
            this.isScanning = param1Boolean;
        }
    }

    public class ProbeServiceBinder extends Binder {
        public ProbeService getService() { return ProbeService.this; }
    }

    private class ProbeSocketThread extends Thread {
        PacketInputStream inputStream;

        PacketOutputStream outputStream;

        private Socket probeSocket;

        private ReceivePacketThread receivePacketThread;

        private final Handler sendPacketHandler;

        private final HandlerThread sendPacketThread = new HandlerThread("SendPacketHandlerThread");

        private ProbeSocketThread() {
            this.sendPacketThread.start();
            this.sendPacketHandler = new Handler(this.sendPacketThread.getLooper());
        }

        public void disconnectProbe() {
            ConnectPacket connectPacket;
            try {
                LogUtils.LOGI("CONNECTION", "before receive packet thread stopRunning.");
                if (this.receivePacketThread != null)
                    this.receivePacketThread.stopRunning();
                LogUtils.LOGI("CONNECTION", "after receive packet thread stopRunning.");
                return;
            } catch (InterruptedException null) {
                connectPacket.printStackTrace();
                return;
            } finally {}
            throw connectPacket;
        }

        public void run() {
            while (true) {
                if (this.isRunning) {
                    try {
                        Thread.sleep(1500L);
                    } catch (InterruptedException interruptedException) {}
                    this.probeSocket = new Socket();
                    try {
                        this.probeSocket.connect(new InetSocketAddress("192.168.0.8", 8888));
                        LogUtils.LOGI("CONNECTION", "probe socket connected.");
                        try {
                            this.inputStream = new PacketInputStream(this.probeSocket.getInputStream());
                            this.outputStream = new PacketOutputStream(this.probeSocket.getOutputStream());
                            connectPacket = new ConnectPacket(this.inputStream.readPacketHeader());
                            connectPacket.readPacketData(this.inputStream);
                            (new ConnectAckPacket()).writePacket(this.outputStream);
                            Message message = ProbeService.this.packetHandler.obtainMessage();
                            message.obj = connectPacket;
                            ProbeService.this.packetHandler.sendMessage(message);
                            this.receivePacketThread = new ReceivePacketThread(this.inputStream, ProbeService.this.packetHandler);
                            this.receivePacketThread.start();
                            ProbeService.this.packetHandler.postDelayed(ProbeService.this.timeoutRunnable, 5000L);
                            ProbeService.this.packetHandler.postBatteryPacket(2000);
                            try {
                                LogUtils.LOGE("CONNECTION", "before receive packet thread join.");
                                this.receivePacketThread.join();
                                if (this.probeSocket != null) {
                                    this.probeSocket.close();
                                    LogUtils.LOGE("CONNECTION", "probe socket closed.");
                                }
                            } catch (InterruptedException connectPacket) {
                                connectPacket.printStackTrace();
                            } catch (IOException connectPacket) {
                                connectPacket.printStackTrace();
                            } finally {}
                            LogUtils.LOGE("CONNECTION", "post disconnected LINE 527.");
                            ProbeService.this.packetHandler.postDisconnected();
                            continue;
                        } catch (IOException iOException) {
                            iOException.printStackTrace();
                            LogUtils.LOGE("CONNECTION", "post disconnected LINE507.");
                            ProbeService.this.packetHandler.postDisconnected();
                            continue;
                        }
                    } catch (IOException iOException) {
                        try {
                            this.probeSocket.close();
                        } catch (IOException iOException1) {}
                        iOException.printStackTrace();
                        continue;
                    }
                }
                LogUtils.LOGI("Service Thread", "ConnectingThread stopped.");
                return;
            }
        }

        public void sendPacket(final Packet packet) {
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                if (param1Packet.getId() == 16390) {
                    Set2DRxTgcProfilePacket set2DRxTgcProfilePacket = (Set2DRxTgcProfilePacket)param1Packet;
                    ProbeService.this.probeStateListener.onUpdateTgc(set2DRxTgcProfilePacket.getOffset());
                }
                this.sendPacketHandler.post(new Runnable() {
                    public void run() {
                        if (ProbeService.ProbeSocketThread.this.outputStream != null)
                            try {
                                while (!packet.writePacket(ProbeService.ProbeSocketThread.this.outputStream)) {
                                    if (packet.getId() == 28674) {
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("send firmware:");
                                        stringBuilder.append(packet.getSendProgressInPercentage());
                                        LogUtils.LOGE("CONNECTION", stringBuilder.toString());
                                        SendProgressPacket sendProgressPacket = new SendProgressPacket(packet.getId(), packet.getSendProgressInPercentage());
                                        Message message = ProbeService.ProbeSocketThread.this.this$0.packetHandler.obtainMessage();
                                        message.obj = sendProgressPacket;
                                        ProbeService.ProbeSocketThread.this.this$0.packetHandler.sendMessage(message);
                                    }
                                    Thread.sleep(100L);
                                }
                            } catch (IOException iOException) {
                                LogUtils.LOGE("CONNECTION", "send packet failed.");
                                iOException.printStackTrace();
                                try {
                                    if (ProbeService.ProbeSocketThread.this.receivePacketThread != null)
                                        ProbeService.ProbeSocketThread.this.receivePacketThread.stopRunning();
                                    return;
                                } catch (InterruptedException iOException) {
                                    return;
                                }
                            } catch (InterruptedException interruptedException) {
                                throw new RuntimeException(interruptedException);
                            }
                    }
                });
                return;
            }
            throw new RuntimeException("can only sendPacket in mainthread.");
        }

        public void stopRunning() {
            if (currentThread().getId() != getId()) {
                this.isRunning = false;
                ReceivePacketThread receivePacketThread1 = this.receivePacketThread;
                if (receivePacketThread1 != null && receivePacketThread1.isAlive())
                    this.receivePacketThread.stopRunning();
                join();
                return;
            }
            throw new RuntimeException("can not call stopRunning in ProbeSocketThread.");
        }
    }

    class null implements Runnable {
        public void run() {
            if (this.this$1.outputStream != null)
                try {
                    while (!packet.writePacket(this.this$1.outputStream)) {
                        if (packet.getId() == 28674) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("send firmware:");
                            stringBuilder.append(packet.getSendProgressInPercentage());
                            LogUtils.LOGE("CONNECTION", stringBuilder.toString());
                            SendProgressPacket sendProgressPacket = new SendProgressPacket(packet.getId(), packet.getSendProgressInPercentage());
                            Message message = this.this$1.this$0.packetHandler.obtainMessage();
                            message.obj = sendProgressPacket;
                            this.this$1.this$0.packetHandler.sendMessage(message);
                        }
                        Thread.sleep(100L);
                    }
                } catch (IOException iOException) {
                    LogUtils.LOGE("CONNECTION", "send packet failed.");
                    iOException.printStackTrace();
                    try {
                        if (this.this$1.receivePacketThread != null)
                            this.this$1.receivePacketThread.stopRunning();
                        return;
                    } catch (InterruptedException iOException) {
                        return;
                    }
                } catch (InterruptedException interruptedException) {
                    throw new RuntimeException(interruptedException);
                }
        }
    }

    public static interface ProbeStateListener {
        public static final int FIRMWARE_UPDATE_ACK_RESULT_FAILED = 1;

        public static final int FIRMWARE_UPDATE_ACK_RESULT_OK = 0;

        void onBattery(short param1Short);

        void onConnected(String param1String, ProbeScanType param1ProbeScanType);

        void onDepthUpdated();

        void onDisconnected();

        void onFirmwareUpdateAck(int param1Int);

        void onFirmwareUpdateProgress(int param1Int);

        void onGetFrame(RFFrame param1RFFrame, ScanConversionParams param1ScanConversionParams);

        void onProbeButtonClicked();

        void onReset();

        void onRfid(String param1String);

        void onUpdateTgc(short param1Short);
    }*/
}