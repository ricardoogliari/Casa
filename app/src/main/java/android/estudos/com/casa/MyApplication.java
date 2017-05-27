package android.estudos.com.casa;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ricardoogliari on 12/6/16.
 */


public class MyApplication extends Application {

    public static DatabaseReference myRef;

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }
}
