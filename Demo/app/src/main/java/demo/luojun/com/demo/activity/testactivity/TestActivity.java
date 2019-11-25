package demo.luojun.com.demo.activity.testactivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

class MainActivity extends AppCompatActivity /*implements IMainView, ChooseFileDialogFragment.ChooseFileDialogListener*/ {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /*private static final String CLOUD_BTN_VISIBLE_METADATA_KEY = "cloud_btn_visible";

    private static final String KEY_ENABLE_INPUT_ID = "KEY_ENABLE_INPUT_ID";

    private static final String KEY_ENABLE_UPLOAD = "KEY_ENABLE_UPLOAD";

    public static final String KEY_USE_LEFT_HAND_LAYOUT = "KEY_USE_LEFT_HANDL_LAYOUT";

    private static final int REQUEST_SCAN_EAR_ID = 1;

    private static final String RESTRICT_METADATA_KEY = "restrict";

    public static final int SHOW_BF_MODE = 2;

    public static final int SHOW_PT_MODE = 1;

    private static final int SMALL_DIALOG_WIDTH_DP = 400;

    private static boolean disableBackfat = false;

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault());

    @BindView(2131230916)
    Button backfatButton;

    @BindView(2131230790)
    TextView backfatTextView;

    @BindView(2131230917)
    ImageView bannerImageView;

    @BindView(2131230792)
    ImageView batteryImageView;

    @BindView(2131230914)
    ColorRampView colorRampView;

    private double currentBackfat = 0.0D;

    private String currentEarId = "";

    @BindView(2131230919)
    Button depthDownButton;

    @BindView(2131230922)
    TextView depthTextView;

    @BindView(2131230920)
    Button depthUpButton;

    private boolean enableInputCustomID = false;

    private boolean enableUpload = false;

    @BindView(2131230747)
    FrameImageView frameImageView;

    @BindView(2131230748)
    ViewGroup framePanelLayout;

    @BindView(2131230907)
    TextView frameTextView;

    @BindView(2131230883)
    TextView freezeHintTextView;

    @BindView(2131230924)
    TextView freqTextView;

    private final Handler handler = new Handler(Looper.getMainLooper());

    @BindView(2131230897)
    TextView idTextView;

    private boolean isServiceBounded = false;

    @BindView(2131230751)
    ImageView loadedScreenshotImageView;

    private IMainViewPresenter mainViewPresenter;

    @BindView(2131230908)
    Button openFileButton;

    @BindView(2131230752)
    ViewGroup playerPanelLayout;

    private View[] playerPanelViews;

    @BindView(2131230912)
    SeekBar playerSeekBar;

    @BindView(2131230927)
    Button pregButton;

    @BindView(2131230970)
    TextView pregDaysTextView;

    @BindView(2131230753)
    PregMeasureView pregMeasureView;

    @BindView(2131230928)
    Button ptbfButton;

    @BindView(2131230974)
    TextView ptbfStateTextView;

    @BindView(2131230909)
    ImageView replayButton;

    @BindView(2131230749)
    View roiTouchView;

    @BindView(2131230910)
    Button saveReportButton;

    @BindView(2131230911)
    Button saveVideoButton;

    private ProgressDialog savingVideoProgressDialog;

    @BindView(2131230915)
    ScaleplateView scaleplateView;

    @BindView(2131230929)
    ToggleButton scanFreezeToggleButton;

    @BindView(2131230985)
    ImageView scanIdImageView;

    private ProgressDialog sendFirmwareProgressDialog;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        private ProbeService probeService;

        public ProbeService getProbeService() { return this.probeService; }

        public void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
            LogUtils.LOGI("probe", "onServiceConnected");
            this.probeService = ((ProbeService.ProbeServiceBinder)param1IBinder).getService();
            MainActivity mainActivity;
            (mainActivity = MainActivity.this).access$002(mainActivity, new MainViewPresenter(mainActivity, this.probeService, new WifiHelper(mainActivity.getApplicationContext())));
            MainActivity.this.mainViewPresenter.onStart();
            MainActivity.this.initStatus();
            MainActivity.access$102(MainActivity.this, true);
        }

        public void onServiceDisconnected(ComponentName param1ComponentName) {
            MainActivity.access$102(MainActivity.this, false);
            this.probeService = null;
        }
    };

    @BindView(2131230913)
    Button settingButton;

    private Dialog simpleErrorDialog;

    @BindView(2131230755)
    ViewGroup statePanelLayout;

    private View[] statePanelViews;

    @BindView(2131230926)
    TextView tgcTextView;

    @BindView(2131231048)
    TextView timeTextView;

    private Runnable updateTimeTextView = new Runnable() {
        public void run() {
            Calendar calendar = Calendar.getInstance();
            String str = timeFormat.format(calendar.getTime());
            MainActivity.this.timeTextView.setText(str);
            MainActivity.this.handler.postDelayed(MainActivity.this.updateTimeTextView, 1000L);
        }
    };

    @BindView(2131230918)
    Button uploadButton;

    private ProgressDialog waitingDetectFatDialog;

    private Dialog wifiListDialog;

    private WifiStateBroadcastReceiver wifiStateBroadcastReceiver = new WifiStateBroadcastReceiver();

    private String getLogoFilePath() {
        File file = Environment.getExternalStoragePublicDirectory("Documents");
        if (file == null)
            return "";
        file = new File(file, "smartscan");
        if (!file.isDirectory()) {
            file.mkdirs();
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getPath());
        stringBuilder.append(File.separator);
        stringBuilder.append("logo.png");
        return stringBuilder.toString();
    }

    private void hidePregDayViewsAndBackFatTextView() {
        this.pregMeasureView.setVisibility(4);
        this.pregDaysTextView.setVisibility(4);
        this.backfatTextView.setVisibility(4);
    }

    private void loadBanner() {
        String str = getLogoFilePath();
        if (!TextUtils.isEmpty(str) && FileUtils.isFileExists(str)) {
            Bitmap bitmap = BitmapFactory.decodeFile(str);
            this.bannerImageView.setImageBitmap(bitmap);
        }
    }

    private void showInputIDDialog() {
        View view = LayoutInflater.from(this).inflate(2131361839, null);
        final List earIds = this.mainViewPresenter.getEarIds();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size:");
        stringBuilder.append(list.size());
        LogUtils.LOGI("EarIds", stringBuilder.toString());
        AutoCompleteIDArrayAdapter autoCompleteIDArrayAdapter = new AutoCompleteIDArrayAdapter(this, 2131361843, list);
        final InstantAutoCompleteTextView idInputTextView = (InstantAutoCompleteTextView)view.findViewById(2131230844);
        instantAutoCompleteTextView.setThreshold(1);
        instantAutoCompleteTextView.setAdapter(autoCompleteIDArrayAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setPositiveButton(2131689525, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                String str = idInputTextView.getText().toString().trim();
                if (!TextUtils.isEmpty(str)) {
                    MainActivity.this.mainViewPresenter.updateEarId(str);
                    MainActivity.this.showID(str);
                    param1DialogInterface.dismiss();
                    MainActivity.this.showSaveReportDialog();
                }
            }
        });
        builder.setNegativeButton(2131689523, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        instantAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
                String str = param1Editable.toString();
                if (TextUtils.isEmpty(str)) {
                    idInputTextView.setError(MainActivity.this.getString(2131689573));
                    okButton.setEnabled(false);
                } else {
                    idInputTextView.setError(null);
                    okButton.setEnabled(true);
                }
                if (!MainActivity.this.enableInputCustomID)
                    okButton.setEnabled(earIds.contains(str.trim()));
            }

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
        });
        instantAutoCompleteTextView.setText("");
        instantAutoCompleteTextView.append(this.currentEarId);
    }

    private void showSaveReportDialog() {
        this.mainViewPresenter.stopReplay();
        final File imageFile = this.mainViewPresenter.saveCurrentFrame();
        if (file == null)
            return;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(2131361841, null);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        ((ImageView)view.findViewById(2131230848)).setImageBitmap(bitmap);
        TextView textView2 = (TextView)view.findViewById(2131230852);
        final EditText remarksEditText = (EditText)view.findViewById(2131230851);
        final TextView idTextView = (TextView)view.findViewById(2131230847);
        final CheckBox isPregnantCheckbox = (CheckBox)view.findViewById(2131230849);
        final DateTime scanTime = this.mainViewPresenter.getScanTime();
        textView2.setText(dateTime.toString("yyyy.MM.dd HHH:mm"));
        builder.setView(view);
        builder.setPositiveButton(2131689525, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                String str1 = idTextView.getText().toString();
                String str2 = remarksEditText.getText().toString();
                float f = (float)MainActivity.this.currentBackfat;
                boolean bool = isPregnantCheckbox.isChecked();
                if (MainActivity.this.mainViewPresenter.savePDFReportAndExportRecord(scanTime, str1, f, bool, str2, imageFile)) {
                    ToastUtils.showLong(MainActivity.this, 2131689646);
                    return;
                }
                ToastUtils.showLong(MainActivity.this, 2131689648);
            }
        });
        builder.setNegativeButton(2131689523, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setSoftInputMode(2);
        alertDialog.show();
        this.mainViewPresenter.enableProbeButtonScan(false);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface param1DialogInterface) {
                bitmap.recycle();
                MainActivity.this.mainViewPresenter.enableProbeButtonScan(true);
            }
        });
        textView1.setText(this.currentEarId);
    }

    public Bitmap captureCurrentFrame() {
        this.framePanelLayout.setDrawingCacheEnabled(false);
        this.framePanelLayout.setDrawingCacheEnabled(true);
        return this.framePanelLayout.getDrawingCache();
    }

    public void dismissNoProbeFoundDialog() {
        Dialog dialog = this.simpleErrorDialog;
        if (dialog != null && dialog.isShowing())
            this.simpleErrorDialog.dismiss();
    }

    public void dismissSavingVideoDialog() {
        this.savingVideoProgressDialog.dismiss();
        this.mainViewPresenter.enableProbeButtonScan(true);
    }

    public void dismissSendFirmwareProgressDialog() {
        this.sendFirmwareProgressDialog.dismiss();
        this.mainViewPresenter.enableProbeButtonScan(true);
    }

    public void dismissWaitingDetectFatDialog() {
        this.waitingDetectFatDialog.dismiss();
        this.mainViewPresenter.enableProbeButtonScan(true);
    }

    public void goToScanQrcode() { startActivityForResult(new Intent(this, com.uuzuche.lib_zxing.activity.CaptureActivity.class), 1); }

    public void goToUpload() { UploadActivity.startActivity(this); }

    protected void initStatus() { this.mainViewPresenter.setTgc((short)200); }

    protected void initViews() {
        loadBanner();
        this.frameImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.frameImageView.setShowFatRefLine(false);
        ToggleButton toggleButton = this.scanFreezeToggleButton;
        Button button2 = this.depthUpButton;
        Button button3 = this.depthDownButton;
        Button button4 = this.backfatButton;
        Button button5 = this.pregButton;
        byte b = 4;
        this.statePanelViews = new View[] { toggleButton, button2, button3, button4, button5 };
        this.playerPanelViews = new View[] { this.settingButton, this.replayButton, this.saveReportButton, this.saveVideoButton, this.openFileButton, this.playerSeekBar };
        setEnableButtonsByScanningState(false);
        setEnableStatePanel(false);
        this.scaleplateView.setStartValue(0.0F);
        this.scaleplateView.setEndValue(ProbeParams.DEFAULT_DEPTH_TYPE.getValueInCM());
        this.scaleplateView.setValueChangedListener(new ScaleplateView.ValueChangedListener() {
            public void onValueChanged(float param1Float) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("value:");
                stringBuilder.append(param1Float);
                LogUtils.LOGI("FOCUS", stringBuilder.toString());
                MainActivity.this.mainViewPresenter.changeFocus(param1Float);
            }
        });
        this.settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.openSettings(); }
        });
        this.backfatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.detectFat(); }
        });
        this.pregButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                MainActivity.this.showPregDays(0);
                MainActivity.this.pregDaysTextView.setVisibility(0);
                MainActivity.this.pregMeasureView.setVisibility(0);
            }
        });
        this.uploadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (TextUtils.isEmpty(MainActivity.this.mainViewPresenter.getLastDeviceId())) {
                    MainActivity.this.showToast(2131689614, new Object[0]);
                    return;
                }
                MainActivity.this.mainViewPresenter.onUpload();
            }
        });
        boolean bool = PackageUtils.getMetadataBoolean(this, "cloud_btn_visible");
        Button button1 = this.uploadButton;
        if (bool)
            b = 0;
        button1.setVisibility(b);
        this.depthUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.increaseDepth(); }
        });
        this.depthDownButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.decreaseDepth(); }
        });
        this.scanFreezeToggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                if (MainActivity.this.scanFreezeToggleButton.isChecked()) {
                    MainActivity.this.mainViewPresenter.startScan();
                    return;
                }
                MainActivity.this.mainViewPresenter.stopScan();
            }
        });
        this.ptbfButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.togglePtBfMode(); }
        });
        this.frameImageView.setMoveGuidePointListener(new FrameImageView.GuidePointChangedListener() {
            public void onGuideLinePointClear() { MainActivity.this.roiTouchView.setVisibility(0); }

            public void onMoveEndPoint(float param1Float) { MainActivity.this.mainViewPresenter.onMoveFatEndPoint(param1Float); }
        });
        this.frameImageView.setOnTouchListener(new View.OnTouchListener() {
            private boolean isChangingDepth = true;

            private boolean isChangingTgc = true;

            private float startX = 0.0F;

            private float startY = 0.0F;

            public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
                short s;
                float f2 = param1MotionEvent.getX();
                float f1 = param1MotionEvent.getY();
                if (MainActivity.this.frameImageView.handleTouchEndPoint(param1MotionEvent))
                    return true;
                int i = MainActivity.this.mainViewPresenter.getTgc();
                boolean bool = MainActivity.this.scanFreezeToggleButton.isChecked();
                switch (param1MotionEvent.getAction()) {
                    default:
                        return true;
                    case 2:
                        f2 = this.startX;
                        i = (short)(i + (short)(int)((f1 - this.startY) * -0.5F));
                        if (this.isChangingTgc) {
                            i = MainActivity.this.mainViewPresenter.getClampedTgc(i);
                            MainActivity.this.updateTgcDisplay(i);
                            return true;
                        }
                        return true;
                    case 1:
                        f2 -= this.startX;
                        f1 -= this.startY;
                        s = (short)(i + (short)(int)(-0.5F * f1));
                        if (this.isChangingTgc) {
                            MainActivity.this.mainViewPresenter.setTgc(s);
                            return true;
                        }
                        if (this.isChangingDepth) {
                            if (Math.abs(f1) > 10.0F) {
                                if (f1 > 0.0F) {
                                    MainActivity.this.mainViewPresenter.decreaseDepth();
                                    return true;
                                }
                                MainActivity.this.mainViewPresenter.increaseDepth();
                                return true;
                            }
                        } else if (Math.abs(f2) > 10.0F) {
                            if (f2 > 0.0F) {
                                MainActivity.this.mainViewPresenter.nextFrame();
                                return true;
                            }
                            MainActivity.this.mainViewPresenter.prevFrame();
                            return true;
                        }
                        return true;
                    case 0:
                        break;
                }
                this.startX = f2;
                this.startY = f1;
                i = param1View.getWidth() / 2;
                f1 = this.startX;
                f2 = i;
                byte b2 = 0;
                if (f1 > f2 && bool) {
                    b1 = 1;
                } else {
                    b1 = 0;
                }
                this.isChangingTgc = b1;
                byte b1 = b2;
                if (this.startX <= f2) {
                    b1 = b2;
                    if (bool)
                        b1 = 1;
                }
                this.isChangingDepth = b1;
                return true;
            }
        });
        if (!disableBackfat) {
            this.ptbfButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) { MainActivity.this.mainViewPresenter.togglePtBfMode(); }
            });
            this.roiTouchView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View param1View) {
                    MainActivity.this.mainViewPresenter.detectFat();
                    return true;
                }
            });
        }
        setReplayMax(0);
        this.playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                if (param1Boolean)
                    MainActivity.this.mainViewPresenter.loadFrameAtProgress(param1Int);
            }

            public void onStartTrackingTouch(SeekBar param1SeekBar) {}

            public void onStopTrackingTouch(SeekBar param1SeekBar) {}
        });
        this.saveReportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.showInputIDDialog(); }
        });
        this.saveVideoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.showConfirmSaveVideoDialog(); }
        });
        this.openFileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.openFileDialog(); }
        });
        this.replayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.toggleReplay(); }
        });
        this.colorRampView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                MainActivity mainActivity = MainActivity.this;
                (new ColorPickerDialog(mainActivity, mainActivity.colorRampView.getStartColor(), new ColorPickerDialog.OnColorSelectedListener(this) {
                    public void colorSelected(Integer param2Integer) { MainActivity.null.this.this$0.updateColor(param2Integer.intValue()); }
                })).show();
            }
        });
        this.pregMeasureView.setOnDragListener(new PregMeasureView.onDragListener() {
            public void onDragEnd(PointF param1PointF1, PointF param1PointF2, boolean param1Boolean) {
                if (!param1Boolean)
                    MainActivity.this.mainViewPresenter.onPregMeasure(param1PointF1, param1PointF2);
            }
        });
        hidePregDayViewsAndBackFatTextView();
        showID(getString(2131689560));
        this.scanIdImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) { MainActivity.this.mainViewPresenter.onScanId(); }
        });
        this.handler.post(this.updateTimeTextView);
    }

    public Bitmap loadTestBitmap() { return BitmapFactory.decodeResource(getResources(), 2131165293); }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        if (paramInt1 == 1) {
            if (paramIntent != null && paramIntent.getExtras() != null) {
                Bundle bundle = paramIntent.getExtras();
                paramInt1 = bundle.getInt("result_type");
                if (paramInt1 == 1) {
                    String str = bundle.getString("result_string", "");
                    Toast.makeText(this, str, 1).show();
                    this.mainViewPresenter.updateEarId(this.currentEarId);
                    showID(str);
                    return;
                }
                if (paramInt1 == 2) {
                    showToast(2131689631, new Object[0]);
                    return;
                }
            }
        } else {
            this.mainViewPresenter.updatePrefParams();
        }
    }

    public void onBackPressed() { finish(); }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        disableBackfat = PackageUtils.getMetadataBoolean(this, "restrict");
        if (paramBundle != null) {
            this.enableUpload = paramBundle.getBoolean("KEY_ENABLE_UPLOAD", false);
            this.enableInputCustomID = paramBundle.getBoolean("KEY_ENABLE_INPUT_ID", true);
        }
        if (PreferenceUtils.getBoolean(this, "KEY_USE_LEFT_HANDL_LAYOUT", true)) {
            setContentView(2131361819);
        } else {
            setContentView(2131361820);
        }
        ButterKnife.setDebug(false);
        ButterKnife.bind(this);
        setTitle(2131689560);
        getWindow().setFlags(1024, 1024);
        ScreenUtils.changeWindowBrightness(this, 1.0F);
        bindService(new Intent(this, ProbeService.class), this.serviceConnection, 1);
        initViews();
        this.savingVideoProgressDialog = DialogFactory.createProgressDialog(this, 2131689550, 2131689534);
        this.savingVideoProgressDialog.setProgressStyle(1);
        this.sendFirmwareProgressDialog = DialogFactory.createProgressDialog(this, 2131689549, 2131689560);
        this.sendFirmwareProgressDialog.setProgressStyle(1);
        this.waitingDetectFatDialog = DialogFactory.createProgressDialog(this, 2131689550, 2131689532);
        WifiStateBroadcastReceiver wifiStateBroadcastReceiver1 = this.wifiStateBroadcastReceiver;
        registerReceiver(wifiStateBroadcastReceiver1, wifiStateBroadcastReceiver1.getIntentFilter());
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131427329, paramMenu);
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
        ScreenUtils.fixInputMethodManagerLeak(this);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        paramMenuItem.getItemId();
        return super.onOptionsItemSelected(paramMenuItem);
    }

    protected void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        paramBundle.putBoolean("KEY_ENABLE_UPLOAD", this.enableUpload);
        paramBundle.putBoolean("KEY_ENABLE_INPUT_ID", this.enableInputCustomID);
    }

    public void onSelectFile(String paramString) { this.mainViewPresenter.loadFrameFromFile(paramString); }

    public void onStart() {
        super.onStart();
        IMainViewPresenter iMainViewPresenter = this.mainViewPresenter;
        if (iMainViewPresenter != null)
            iMainViewPresenter.onStart();
    }

    public void onStop() {
        super.onStop();
        IMainViewPresenter iMainViewPresenter = this.mainViewPresenter;
        if (iMainViewPresenter != null)
            iMainViewPresenter.onStop();
        this.scanFreezeToggleButton.setChecked(false);
        if (isFinishing()) {
            unregisterReceiver(this.wifiStateBroadcastReceiver);
            iMainViewPresenter = this.mainViewPresenter;
            if (iMainViewPresenter != null)
                iMainViewPresenter.onDestroy();
            unbindService(this.serviceConnection);
        }
    }

    public void openSettingsView() { startActivityForResult(new Intent(this, ParamSettingsActivity.class), 1); }

    public void setEnableButtonsByScanningState(boolean paramBoolean) {
        boolean bool;
        Button button = this.ptbfButton;
        byte b = 0;
        if (paramBoolean && !disableBackfat) {
            bool = true;
        } else {
            bool = false;
        }
        button.setEnabled(bool);
        if (this.enableUpload)
            this.uploadButton.setEnabled(paramBoolean ^ true);
        this.backfatButton.setEnabled(paramBoolean ^ true);
        this.pregButton.setEnabled(paramBoolean ^ true);
        View[] arrayOfView = this.playerPanelViews;
        int i = arrayOfView.length;
        while (b < i) {
            arrayOfView[b].setEnabled(paramBoolean ^ true);
            b++;
        }
    }

    public void setEnableInputCustomID(boolean paramBoolean) { this.enableInputCustomID = paramBoolean; }

    public void setEnableStatePanel(boolean paramBoolean) {
        View[] arrayOfView = this.statePanelViews;
        int i = arrayOfView.length;
        for (byte b = 0; b < i; b++)
            arrayOfView[b].setEnabled(paramBoolean);
    }

    public void setEnableUpload(boolean paramBoolean) {
        this.uploadButton.setEnabled(paramBoolean);
        this.enableUpload = paramBoolean;
    }

    public void setFatRefLinePosInMM(float paramFloat) { this.frameImageView.setFatRefLinePosInMM(paramFloat); }

    public void setFrameViewDepthInMM(float paramFloat1, float paramFloat2) { this.frameImageView.setFrameTotalDepthInMM(paramFloat1, paramFloat2); }

    public void setMainFrameViewScaleType(ImageView.ScaleType paramScaleType) { this.frameImageView.setScaleType(paramScaleType); }

    public void setReplayMax(int paramInt) {
        this.playerSeekBar.setMax(paramInt);
        this.playerSeekBar.setProgress(0);
        TextView textView = this.frameTextView;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.playerSeekBar.getProgress());
        stringBuilder.append("/");
        stringBuilder.append(this.playerSeekBar.getMax());
        textView.setText(stringBuilder.toString());
    }

    public void setScanningState(boolean paramBoolean) {
        if (paramBoolean)
            hidePregDayViewsAndBackFatTextView();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("update freezeButton state:");
        stringBuilder.append(paramBoolean);
        LogUtils.LOGI("UI", stringBuilder.toString());
        this.scanFreezeToggleButton.setChecked(paramBoolean);
    }

    public void setShowFatRefLine(boolean paramBoolean) { this.frameImageView.setShowFatRefLine(paramBoolean); }

    public void showBackfatInfo(double paramDouble) {
        this.currentBackfat = paramDouble;
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        this.backfatTextView.setText(getString(2131689507, new Object[] { decimalFormat.format(paramDouble) }));
        this.backfatTextView.setVisibility(0);
    }

    public void showConfirmSaveVideoDialog() {
        this.mainViewPresenter.enableProbeButtonScan(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(2131689547);
        builder.setMessage(2131689533);
        builder.setPositiveButton(2131689525, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                param1DialogInterface.dismiss();
                MainActivity.this.mainViewPresenter.saveCurrentFramesToVideoFile();
            }
        });
        builder.setNegativeButton(2131689523, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface param1DialogInterface) { MainActivity.this.mainViewPresenter.enableProbeButtonScan(true); }
        });
        builder.create().show();
    }

    public void showConfirmUpdateFirmwareDialog() {
        this.mainViewPresenter.enableProbeButtonScan(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(2131689652);
        builder.setMessage(getString(2131689651, new Object[] { FileUtils.getFirmwareFilePathInExternalStorage() }));
        builder.setPositiveButton(2131689524, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) { MainActivity.this.mainViewPresenter.updateFirmware(); }
        });
        builder.setNegativeButton(2131689523, null);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface param1DialogInterface) { MainActivity.this.mainViewPresenter.enableProbeButtonScan(true); }
        });
        builder.create().show();
    }

    public void showConnected(boolean paramBoolean) {
        String str;
        this.scanFreezeToggleButton.setChecked(false);
        if (paramBoolean) {
            str = getString(2131689639);
            this.batteryImageView.setVisibility(0);
        } else {
            str = getString(2131689640);
            this.batteryImageView.setVisibility(4);
            showFreeze(false);
        }
        ToastUtils.showLong(this, str);
    }

    public void showFatGuideLine(float paramFloat) {
        this.roiTouchView.setVisibility(8);
        this.frameImageView.setFatGuideLine(paramFloat);
    }

    public void showFrame(Bitmap paramBitmap) {
        if (this.loadedScreenshotImageView.isShown())
            this.loadedScreenshotImageView.setVisibility(8);
        this.frameImageView.setImageBitmap(paramBitmap);
    }

    public void showFreeze(boolean paramBoolean) {
        byte b;
        TextView textView = this.freezeHintTextView;
        if (paramBoolean) {
            b = 0;
        } else {
            b = 8;
        }
        textView.setVisibility(b);
    }

    public void showID(String paramString) {
        this.currentEarId = paramString.substring(0, Math.min(16, paramString.length()));
        this.idTextView.setText(getString(2131689509, new Object[] { this.currentEarId }));
    }

    public void showLoadedScreenShot(Bitmap paramBitmap) {
        this.loadedScreenshotImageView.setImageBitmap(paramBitmap);
        this.loadedScreenshotImageView.setVisibility(0);
    }

    public void showNeedLocationService() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(2131689611);
        builder.setMessage(2131689610);
        builder.setPositiveButton(2131689525, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                MainActivity.this.startActivity(intent);
            }
        });
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public void showNoProbeFoundDialogWithDelay(int paramInt1, int paramInt2, int paramInt3) {
        Dialog dialog = this.simpleErrorDialog;
        if (dialog != null && dialog.isShowing())
            return;
        this.simpleErrorDialog = DialogFactory.createSimpleErrorDialog(this, getString(paramInt1), getString(paramInt2), getString(2131689521), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) { MainActivity.this.finish(); }
        },  getString(2131689522), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) { MainActivity.this.mainViewPresenter.scanWifi(); }
        });
        this.statePanelLayout.postDelayed(new Runnable() {
            public void run() { MainActivity.this.simpleErrorDialog.show(); }
        },  paramInt3);
    }

    public void showOpenFileDialog(String paramString1, String paramString2) {
        this.mainViewPresenter.enableProbeButtonScan(false);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChooseFileDialogFragment chooseFileDialogFragment = ChooseFileDialogFragment.newInstance(paramString1, paramString2);
        chooseFileDialogFragment.show(fragmentManager, "ChooseFileDialogFragment");
        fragmentManager.executePendingTransactions();
        chooseFileDialogFragment.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface param1DialogInterface) { MainActivity.this.mainViewPresenter.enableProbeButtonScan(true); }
        });
    }

    public void showPregDays(int paramInt) {
        this.pregDaysTextView.setText(getString(2131689510, new Object[] { Integer.valueOf(paramInt) }));
        this.pregDaysTextView.setVisibility(0);
    }

    public void showPregMeasureView(boolean paramBoolean) {
        byte b;
        PregMeasureView pregMeasureView1 = this.pregMeasureView;
        if (paramBoolean) {
            b = 0;
        } else {
            b = 8;
        }
        pregMeasureView1.setVisibility(b);
    }

    @SuppressLint({"SetTextI18n"})
    public void showPtBfMode(int paramInt) {
        if (paramInt == 2) {
            this.ptbfStateTextView.setText("BF");
            return;
        }
        if (paramInt == 1) {
            this.ptbfStateTextView.setText("PT");
            return;
        }
        throw new IllegalArgumentException("unexpected mode value for showPtBfMode.");
    }

    public void showSavingVideoDialog(int paramInt) {
        this.mainViewPresenter.enableProbeButtonScan(false);
        this.savingVideoProgressDialog.setMax(paramInt);
        this.savingVideoProgressDialog.show();
    }

    public void showSendFirmwareProgressDialog() {
        this.mainViewPresenter.enableProbeButtonScan(false);
        this.sendFirmwareProgressDialog.setMax(100);
        this.sendFirmwareProgressDialog.show();
    }

    public void showToast(int paramInt, Object... paramVarArgs) { ToastUtils.showLong(this, getString(paramInt, paramVarArgs)); }

    public void showToast(String paramString) { ToastUtils.showLong(this, paramString); }

    public void showUserSettingsDialog() {
        boolean bool = PreferenceUtils.getBoolean(this, "KEY_USE_LEFT_HANDL_LAYOUT", true);
        int i = PreferenceUtils.getInt(this, "POWER_OFF_MINUTES_KEY", 8);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getInt:");
        stringBuilder.append(i);
        LogUtils.LOGI("sleeptime", stringBuilder.toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(2131361842, null);
        final EditText hostUrlEditText = (EditText)view.findViewById(2131230853);
        final Button updateHostButton = (Button)view.findViewById(2131230858);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                String str2 = hostUrlEditText.getText().toString();
                String str1 = str2;
                if (!str2.endsWith("/"))
                    str1 = str2.concat("/");
                MainActivity.this.mainViewPresenter.setHostBaseUrl(str1);
                hostUrlEditText.setText(str1);
            }
        });
        editText.setText(this.mainViewPresenter.getHostBaseUrl());
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {
                if (HttpUrl.parse(param1Editable.toString()) != null) {
                    hostUrlEditText.setError(null);
                    updateHostButton.setEnabled(true);
                    return;
                }
                hostUrlEditText.setError(MainActivity.this.getString(2131689554));
                updateHostButton.setEnabled(false);
            }

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {}
        });
        ToggleButton toggleButton = (ToggleButton)view.findViewById(2131230855);
        final SwipeNumberPicker sleepTimeNumberPicker = (SwipeNumberPicker)view.findViewById(2131230856);
        Button button1 = (Button)view.findViewById(2131230859);
        swipeNumberPicker.setShowNumberPickerDialog(false);
        swipeNumberPicker.setMaxValue(20);
        swipeNumberPicker.setMinValue(1);
        swipeNumberPicker.setValue(i, true);
        swipeNumberPicker.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
                int i = param1MotionEvent.getAction();
                if (i == 1) {
                    float f = param1MotionEvent.getX();
                    i = sleepTimeNumberPicker.getValue();
                    if (f > (param1View.getWidth() / 2)) {
                        if (i < sleepTimeNumberPicker.getMaxValue()) {
                            sleepTimeNumberPicker.setValue(i + 1, false);
                            return true;
                        }
                    } else if (i > sleepTimeNumberPicker.getMinValue()) {
                        sleepTimeNumberPicker.setValue(i - 1, false);
                    }
                    return true;
                }
                return (i == 2);
            }
        });
        toggleButton.setChecked(bool);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                PreferenceUtils.putBoolean(MainActivity.this, "KEY_USE_LEFT_HANDL_LAYOUT", param1Boolean);
                ToastUtils.showShort(MainActivity.this, 2131689642);
            }
        });
        builder.setTitle(2131689555);
        builder.setView(view);
        builder.setPositiveButton(2131689525, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                PreferenceUtils.putInt(MainActivity.this, "POWER_OFF_MINUTES_KEY", sleepTimeNumberPicker.getValue());
                MainActivity.this.mainViewPresenter.updatePrefParams();
            }
        });
        builder.setNegativeButton(2131689523, null);
        final AlertDialog dialog = builder.create();
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                MainActivity.this.showConfirmUpdateFirmwareDialog();
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        alertDialog.getWindow().setLayout(ScreenUtils.dpToPxSize(this, 400), layoutParams.height);
        alertDialog.show();
        this.mainViewPresenter.enableProbeButtonScan(false);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface param1DialogInterface) { MainActivity.this.mainViewPresenter.enableProbeButtonScan(true); }
        });
    }

    public void showWaitingDetectFatDialog() {
        this.mainViewPresenter.enableProbeButtonScan(false);
        this.waitingDetectFatDialog.show();
    }

    public void showWifiListDialog(final String[] apNames) {
        Dialog dialog = this.wifiListDialog;
        if (dialog != null && dialog.isShowing())
            return;
        this.wifiListDialog = DialogFactory.createItemsDialog(this, paramArrayOfString, getString(2131689537), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                String str = apNames[param1Int];
                MainActivity.this.mainViewPresenter.enableNetwork(str, str);
            }
        }getString(2131689523), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) { MainActivity.this.finish(); }
        });
        this.wifiListDialog.show();
    }

    public void updateBattery(int paramInt, boolean paramBoolean) {
        if (paramInt > 80) {
            ImageView imageView1 = this.batteryImageView;
            if (paramBoolean) {
                paramInt = 2131165318;
            } else {
                paramInt = 2131165320;
            }
            imageView1.setImageResource(paramInt);
            return;
        }
        if (paramInt > 60) {
            ImageView imageView1 = this.batteryImageView;
            if (paramBoolean) {
                paramInt = 2131165315;
            } else {
                paramInt = 2131165309;
            }
            imageView1.setImageResource(paramInt);
            return;
        }
        if (paramInt > 40) {
            ImageView imageView1 = this.batteryImageView;
            if (paramBoolean) {
                paramInt = 2131165313;
            } else {
                paramInt = 2131165307;
            }
            imageView1.setImageResource(paramInt);
            return;
        }
        if (paramInt > 20) {
            ImageView imageView1 = this.batteryImageView;
            if (paramBoolean) {
                paramInt = 2131165312;
            } else {
                paramInt = 2131165306;
            }
            imageView1.setImageResource(paramInt);
            return;
        }
        ImageView imageView = this.batteryImageView;
        if (paramBoolean) {
            paramInt = 2131165317;
        } else {
            paramInt = 2131165319;
        }
        imageView.setImageResource(paramInt);
    }

    public void updateColor(int paramInt) {
        this.colorRampView.setStartColor(paramInt);
        this.frameImageView.setColorFilter(paramInt, PorterDuff.Mode.MULTIPLY);
    }

    public void updateDepth(float paramFloat1, float paramFloat2) {
        int i = (int)(10.0F * paramFloat2 + 0.5F);
        this.depthTextView.setText(String.format(getString(2131689502), new Object[] { Integer.valueOf(i) }));
        hidePregDayViewsAndBackFatTextView();
        RectF rectF = this.frameImageView.getImageBounds();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("imageBounds top:");
        stringBuilder2.append(rectF.top);
        stringBuilder2.append("  bottom:");
        stringBuilder2.append(rectF.bottom);
        LogUtils.LOGI("Depth", stringBuilder2.toString());
        this.scaleplateView.setStartPixelPos(0.0F);
        ScaleplateView scaleplateView1 = this.scaleplateView;
        scaleplateView1.setEndPixelPos(scaleplateView1.getHeight());
        float f = paramFloat2 + paramFloat1;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("frameDeadZoneInCM:");
        stringBuilder1.append(paramFloat1);
        stringBuilder1.append("   depthInCM:");
        stringBuilder1.append(paramFloat2);
        LogUtils.LOGI("Depth", stringBuilder1.toString());
        this.scaleplateView.setStartValue(paramFloat1);
        this.scaleplateView.setEndValue(f);
        this.scaleplateView.setTotalValue(f);
    }

    public void updateFreq(float paramFloat) { this.freqTextView.setText(String.format(getString(2131689505), new Object[] { Float.valueOf(paramFloat) })); }

    public void updateReplayProgress(int paramInt) {
        this.roiTouchView.setVisibility(0);
        this.playerSeekBar.setProgress(paramInt);
        TextView textView = this.frameTextView;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.playerSeekBar.getProgress());
        stringBuilder.append("/");
        stringBuilder.append(this.playerSeekBar.getMax());
        textView.setText(stringBuilder.toString());
    }

    public void updateReplayState(boolean paramBoolean) {
        if (paramBoolean) {
            hidePregDayViewsAndBackFatTextView();
            this.replayButton.setImageResource(2131165412);
            return;
        }
        this.replayButton.setImageResource(2131165413);
    }

    public void updateSavingVideoDialogProgress(int paramInt) { this.savingVideoProgressDialog.setProgress(paramInt); }

    public void updateSendFirmwareProgress(int paramInt) { this.sendFirmwareProgressDialog.setProgress(paramInt); }

    public void updateTgcDisplay(int paramInt) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("updateTgcDisplay:");
        stringBuilder.append(paramInt);
        LogUtils.LOGI("TGC", stringBuilder.toString());
        this.tgcTextView.setText(String.format(getString(2131689511), new Object[] { Integer.valueOf(paramInt + 90) }));
    }
}





*/



}
