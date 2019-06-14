package com.yumi.android;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.yumi.android.demo.BannerActivity;
import com.yumi.android.demo.InterstitialActivity;
import com.yumi.android.demo.MainActivity;
import com.yumi.android.demo.MediaActivity;
import com.yumi.android.demo.NativeActivity;
import com.yumi.android.demo.SplashTestActivity;
import com.yumi.android.sdk.ads.publish.YumiSettings;
import com.yumimobi.ads.demo.R;

public class MainActivity extends MActivity implements OnClickListener, OnCheckedChangeListener {

    private Button btnBanner;
    private Button btnInterstitial;
    private Button btnMedia;
    private Button btnSplash;
    private Button btnNative;
    private Button btnStartDebugging;
    private EditText channel;
    private EditText version;
    private EditText bannerSlotID, interstitialSlotID, mdiaSlotID, splashSlotID, nativeSlotID;
    private CheckBox cb_isMatchWindowWidth;
    private boolean isMatchWindowWidth;

    private Button anaylsis;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        btnBanner = (Button) findViewById(R.id.btn_banner_a);
        btnInterstitial = (Button) findViewById(R.id.btn_interstitial_a);
        btnMedia = (Button) findViewById(R.id.btn_media_a);
        btnSplash = (Button) findViewById(R.id.btn_splash);
        btnNative = (Button) findViewById(R.id.btn_native);
        btnStartDebugging = (Button) findViewById(R.id.btn_startDebugging);
        anaylsis = (Button) findViewById(R.id.btn_anaylsis);

        bannerSlotID = (EditText) findViewById(R.id.banner_slotID);
        bannerSlotID.clearFocus();
        interstitialSlotID = (EditText) findViewById(R.id.interstitial_slotID);
        interstitialSlotID.clearFocus();
        mdiaSlotID = (EditText) findViewById(R.id.mdia_slotID);
        mdiaSlotID.clearFocus();
        splashSlotID = (EditText) findViewById(R.id.splash_slotID);
        splashSlotID.clearFocus();
        nativeSlotID = (EditText) findViewById(R.id.native_slotID);
        nativeSlotID.clearFocus();

        channel = (EditText) findViewById(R.id.channel);
        channel.clearFocus();
        version = (EditText) findViewById(R.id.version);
        version.clearFocus();

        cb_isMatchWindowWidth = (CheckBox) findViewById(R.id.cb_isMatchWindowWidth);

        channel.setText(getStringConfig("channel"));
        version.setText(getStringConfig("version"));
        isMatchWindowWidth = getBooleanConfig("isMatchWindowWidth");
        cb_isMatchWindowWidth.setChecked(isMatchWindowWidth);
        YumiSettings.runInCheckPermission(true);

        bannerSlotID.setText(getStringConfig("banner_slotID", "uz852t89"));
        interstitialSlotID.setText(getStringConfig("interstitial_slotID", "56ubk22h"));
        mdiaSlotID.setText(getStringConfig("mdia_slotID", "ew9hyvl4"));
        splashSlotID.setText(getStringConfig("splash_slotID", "vv7snvc5"));
        nativeSlotID.setText(getStringConfig("native_slotID", "dt62rndy"));
    }

    @Override
    public void setListener() {
        btnBanner.setOnClickListener(this);
        btnInterstitial.setOnClickListener(this);
        btnMedia.setOnClickListener(this);
        btnSplash.setOnClickListener(this);
        btnNative.setOnClickListener(this);
        btnStartDebugging.setOnClickListener(this);
        cb_isMatchWindowWidth.setOnCheckedChangeListener(this);

        anaylsis.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton b, boolean flag) {
        if (b == cb_isMatchWindowWidth) {
            sp.edit().putBoolean("isMatchWindowWidth", flag).commit();
            isMatchWindowWidth = flag;
        }
    }

    @Override
    public void onClick(View v) {
        String cha = channel.getText().toString();
        String ver = version.getText().toString();
        String bslotID = bannerSlotID.getText().toString();
        String islotID = interstitialSlotID.getText().toString();
        String mslotID = mdiaSlotID.getText().toString();
        String sslotID = splashSlotID.getText().toString();
        String nslotID = nativeSlotID.getText().toString();

        sp.edit().putString("channel", cha).commit();
        sp.edit().putString("version", ver).commit();
        sp.edit().putString("banner_slotID", bslotID).commit();
        sp.edit().putString("interstitial_slotID", islotID).commit();
        sp.edit().putString("mdia_slotID", mslotID).commit();
        sp.edit().putString("splash_slotID", sslotID).commit();
        sp.edit().putString("native_slotID", nslotID).commit();

        if (v.getId() == R.id.btn_startDebugging) {
            YumiSettings.startDebugging(MainActivity.this, bslotID, islotID, mslotID, nslotID,cha, ver);
            return;
        }
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_banner_a:
                intent.setClass(MainActivity.this, BannerActivity.class);
                intent.putExtra("isMatchWindowWidth", isMatchWindowWidth);
                break;
            case R.id.btn_interstitial_a:
                intent.setClass(MainActivity.this, InterstitialActivity.class);
                break;
            case R.id.btn_media_a:
                intent.setClass(MainActivity.this, MediaActivity.class);
                break;
            case R.id.btn_splash:
                intent.setClass(MainActivity.this, SplashTestActivity.class);
                break;
            case R.id.btn_native:
                intent.setClass(MainActivity.this, NativeActivity.class);
                break;
            default:
                break;
        }
        intent.putExtra("channel", cha);
        intent.putExtra("version", ver);
        startActivity(intent);
    }

    @Override
    public void onActivityCreate() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
