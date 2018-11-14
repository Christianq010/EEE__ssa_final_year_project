package com.typeiii.fieldtest;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SignalController {
    private static final SignalController instance = new SignalController();

    //private constructor to avoid client applications to use constructor
    private SignalController(){}

    public static SignalController getInstance(){
        return instance;
    }

    TelephonyManager mTelephonyManager;
    //MPhoneStateListener mPhoneStatelistener;
    int mSignalStrength = 0;

    public int getSignal(Context context) {
        //mPhoneStatelistener = new MPhoneStateListener();
        mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        assert mTelephonyManager != null;
        mTelephonyManager.listen(new PhoneStateListener(){
            @Override
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);
                mSignalStrength = signalStrength.getGsmSignalStrength();
                mSignalStrength = (2 * mSignalStrength) - 113; // -> dBm
                Log.v("TAG", "Signal: " + mSignalStrength);
                //textView.setText("Signal: " + mSignalStrength);
            }
        }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        return 0;
    }

}
