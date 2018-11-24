package com.example.amatullah.applock1;

/**
 * Created by amatullah on 6/4/17.
 */
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vinayak on 6/4/17.
 */

public class applist extends Activity {

    private ArrayList<String> appnames;
    private ArrayList<String> pkgnames;
    ListView apps;
    PackageManager packageManager;
    ArrayList <String> checkedValue;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.button1);
        apps = (ListView) findViewById(R.id.listView1);
        appnames = new ArrayList<>();
        pkgnames = new ArrayList<>();
        packageManager = getPackageManager();
        final List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_META_DATA); // all apps in the phone
        final List <PackageInfo> packageList1 = packageManager
                .getInstalledPackages(0);

        try {
            packageList1.clear();
            for (int n = 0; n < packageList.size(); n++)
            {

                PackageInfo PackInfo = packageList.get(n);
                if (((PackInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) != true)
                //check weather it is system app or user installed app
                {
                    try
                    {

                        packageList1.add(packageList.get(n)); // add in 2nd list if it is user installed app
                        Collections.sort(packageList1,new Comparator<PackageInfo>()
                                // this will sort App list on the basis of app name
                        {
                            public int compare(PackageInfo o1,PackageInfo o2)
                            {
                                return o1.applicationInfo.loadLabel(getPackageManager()).toString()
                                        .compareToIgnoreCase(o2.applicationInfo.loadLabel(getPackageManager())
                                                .toString());// compare and return sorted packagelist.
                            }
                        });


                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*ListAdapter Adapter = new ListAdapter(MainActivity.this,packageList1, packageManager);
        apps.setAdapter(Adapter);
        apps.setOnItemClickListener(this);*/
        final ListAdapter Adapter = new ListAdapter(applist.this,packageList1, packageManager);
        apps.setAdapter(Adapter);
        // apps.setOnItemClickListener(this);
        apps.setClickable(false);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(MainActivity.this,"" + checkedValue.toString(),Toast.LENGTH_LONG).show();
                appnames = Adapter.getSelectedItem();
                pkgnames = Adapter.getSelectedPackage();
            }
        });
        startService();
            /*Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);*/
    }

    /*@Override
    //public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    public void onItemClick(AdapterView arg0, View v, int position, long arg3) {
        // TODO Auto-generated method stub
        CheckBox cb = (CheckBox) v.findViewById(R.id.checkBox1);
        TextView tv = (TextView) v.findViewById(R.id.textView1);
        cb.performClick();
        if (cb.isChecked()) {

            checkedValue.add(tv.getText().toString());
            //Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        } else if (!cb.isChecked()) {
            checkedValue.remove(tv.getText().toString());
        }

     }
    }*/

    private static Timer timer = new Timer();
    private Context ctx;
    public String pActivity = "";

    public IBinder onBind(Intent arg0) {
        return null;
    }

    /*public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        startService();
    }*/

    private void startService() {
        timer.scheduleAtFixedRate(new mainTask(), 0, 500);
    }

    private class mainTask extends TimerTask {
        public void run() {
            toastHandler.sendEmptyMessage(0);
        }
    }

    public void onDestroy() {
        //super.onDestroy();
        Toast.makeText(this, "Service Stopped...", Toast.LENGTH_SHORT).show();
        if(timer!= null) {
            timer.cancel();
        }
        super.onDestroy();
    }
    private final Handler toastHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String activityOnTop;
            ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> RunningTask = mActivityManager.getRunningAppProcesses();

            activityOnTop = RunningTask.get(0).processName;

            if(!pkgnames.contains(activityOnTop)) {
                //Toast.makeText(applist.this, "hi", Toast.LENGTH_SHORT).show();
            }
            /*if(Arrays.asList(appnames).contains("com.muffins.mobile")) {
                Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
            }*/
            /*if (!activityOnTop.equals("com.muffins.mobile")) {
            }*/

            else {
                if (activityOnTop.equals(pActivity) || activityOnTop.equals("com.example.vinayak.service.lockscreen")) {
                }
                else {
                    Intent i = new Intent(applist.this, lockscreen.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //timer.cancel();
                    startActivity(i);
                    //timer.cancel();
                }
            }
        }
    };
}


