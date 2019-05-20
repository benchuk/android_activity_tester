package lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.Tag;
import android.service.notification.NotificationListenerService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo.R;

public class Activity_A extends BaseActivity{

    private static final String TAG=Activity_A.class.getSimpleName();
    private static int instanceCounter=0;
    private int currentInstanceValue;

    private Button buttonStartActivityA,buttonStartActivityB,buttonStartActivityC,buttonStartActivityD,buttonStartActivityE;
    private TextView textViewTaskInfo,textViewInstanceValue;

    public Activity_A(){
        super();
        instanceCounter++;
        currentInstanceValue=instanceCounter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        buttonStartActivityA=(Button)findViewById(R.id.buttonStartActivityA);
        buttonStartActivityB=(Button)findViewById(R.id.buttonStartActivityB);
        buttonStartActivityC=(Button)findViewById(R.id.buttonStartActivityC);
        buttonStartActivityD=(Button)findViewById(R.id.buttonStartActivityD);
        buttonStartActivityE=(Button)findViewById(R.id.buttonStartActivityE);

        textViewTaskInfo=(TextView)findViewById(R.id.textViewTaskInfo);
        textViewInstanceValue=(TextView)findViewById(R.id.textViewInstanceValue);


        buttonStartActivityA.setOnClickListener(this);
        buttonStartActivityB.setOnClickListener(this);
        buttonStartActivityC.setOnClickListener(this);
        buttonStartActivityD.setOnClickListener(this);
        buttonStartActivityE.setOnClickListener(this);

        Intent notificationIntent = new Intent(this, Activity_C.class);
        //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification repliedNotification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .setContentText("test")
                .setContentIntent(contentIntent)
                .build();

        ((NotificationManager) this.getSystemService(NOTIFICATION_SERVICE)).notify(1, repliedNotification);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonStartActivityA: startActivity(this,Activity_A.class);break;
            case R.id.buttonStartActivityB: startActivity(this,Activity_B.class);break;
            case R.id.buttonStartActivityC: startActivity(this,Activity_C.class);break;
            case R.id.buttonStartActivityD: startActivity(this, Activity_D.class);break;
            case R.id.buttonStartActivityE:
            {
                Intent e = new Intent(this,Activity_E.class);
                e.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(e);
                break;
            }
            default:break;
        }
    }


    @Override
    protected void onUpdate() {
        super.onUpdate();
        updateText();
    }

    private void updateText() {
        textViewInstanceValue.setText("APP 1, Activity A, id: " + currentInstanceValue);
        textViewInstanceValue.append(", T: " + instanceCounter);
        textViewTaskInfo.setText(getAppTaskState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instanceCounter--;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateText();

        textViewTaskInfo.setText(getAppTaskState());
    }
}
