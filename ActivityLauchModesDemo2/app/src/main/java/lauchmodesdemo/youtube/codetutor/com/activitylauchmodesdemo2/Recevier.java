package lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Recevier extends BroadcastReceiver
{
    private static final String TAG = "TASK";



    @Override
    public void onReceive(Context context, Intent intent)
    {

            Intent i = new Intent(context, Activity_E.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);

    }
}