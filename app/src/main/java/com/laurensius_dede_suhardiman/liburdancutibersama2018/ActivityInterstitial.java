package com.laurensius_dede_suhardiman.liburdancutibersama2018;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;



public class ActivityInterstitial extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (pDialog.isShowing())
                    pDialog.dismiss();
                showInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d("AdMob", "Gagal Load Interstitital Ad");
                if (pDialog.isShowing())
                    pDialog.dismiss();
                finish();
            }

            @Override
            public void onAdClosed() {
                Log.d("AdMob", "Klik close ad, closing...");
                finish();
            }
        });
        mInterstitialAd.loadAd(adRequest);
        pDialog = new ProgressDialog(ActivityInterstitial.this);
        pDialog.setMessage("Loading....");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
