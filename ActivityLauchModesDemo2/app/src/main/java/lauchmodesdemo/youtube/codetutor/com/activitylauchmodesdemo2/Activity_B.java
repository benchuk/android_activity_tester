package lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo2.R;

public class Activity_B extends BaseActivity {

    private static final String TAG=Activity_B.class.getSimpleName();
    private static int instanceCounter=0;
    private int currentInstanceValue;

    private Button buttonStartActivityA,buttonStartActivityB,buttonStartActivityC,buttonStartActivityD,buttonStartActivityE;
    private TextView textViewTaskInfo, textViewInstanceValue;

    public Activity_B(){
        super();
        instanceCounter++;
        currentInstanceValue=instanceCounter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instanceCounter--;
    }

    @Override
    protected void onUpdate() {
        super.onUpdate();
        updateText();
    }

    private void updateText() {
        textViewInstanceValue.setText("APP 2, Activity B, id: " + currentInstanceValue);
        textViewInstanceValue.append(", T: " + instanceCounter);
        textViewTaskInfo.setText(getAppTaskState());
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
    protected void onResume() {
        super.onResume();
        updateText();
        textViewTaskInfo.setText(getAppTaskState());


    }

}
