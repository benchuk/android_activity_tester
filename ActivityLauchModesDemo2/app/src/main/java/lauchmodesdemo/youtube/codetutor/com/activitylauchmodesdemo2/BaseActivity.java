package lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo2;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by anildeshpande on 12/18/16.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{

    protected static ActivityManager activityManager;
    protected IntentFilter intentFilter = new IntentFilter("update.static");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(activityManager==null){
            activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        }
        this.getApplicationContext().registerReceiver(b,intentFilter);

    }

    private BroadcastReceiver b = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onUpdate();
        }
    };

    protected void onUpdate()
    {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.getApplicationContext().unregisterReceiver(b);
        this.getApplicationContext().sendBroadcast(new Intent("update.static"));
    }

    protected void startActivity(Activity activity, Class targetActivityClass){
        Intent intent=new Intent(activity,targetActivityClass);
        startActivity(intent);
    }

    protected static int getNumberOfTasks(){
        return activityManager.getAppTasks().size();
    }

    protected static String getAppTaskState(){

        StringBuilder stringBuilder=new StringBuilder();
        int totalNumberOfTasks=activityManager.getRunningTasks(10).size();//Returns total number of tasks - stacks
        stringBuilder.append("\nTotal Number of Tasks: "+totalNumberOfTasks+"\n\n");

        List<ActivityManager.RunningTaskInfo> taskInfo =activityManager.getRunningTasks(10);//returns List of RunningTaskInfo - corresponding to tasks - stacks

        for(ActivityManager.RunningTaskInfo info:taskInfo){
            stringBuilder.append("Task Id: "+info.id+", Number of Activities : "+info.numActivities+"\n");//Number of Activities in task - stack

            // Display the root Activity of task-stack
            stringBuilder.append("TopActivity: "+info.topActivity.getClassName().
                    toString().replace("lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo.","")+"\n");

            // Display the top Activity of task-stack
            stringBuilder.append("BaseActivity:"+info.baseActivity.getClassName().
                    toString().replace("lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo.","")+"\n");
            stringBuilder.append("\n\n");
            //stringBuilder.append(new Date().getTime());
        }
        return stringBuilder.toString();
    }


}
