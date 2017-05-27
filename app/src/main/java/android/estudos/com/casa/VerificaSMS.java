package android.estudos.com.casa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by ricardoogliari on 5/12/16.
 */
public class VerificaSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    if (message.startsWith("[gio]")){
                        Intent in = new Intent(MainActivity.ACTION_HOME);
                        if (message.length() == 7){

                        }
                        in.putExtra("acao", message.substring(5));
                        context.sendBroadcast(in);

                        //in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //context.startActivity(in);
                    }

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
        }
    }
}