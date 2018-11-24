package com.example.amatullah.applock1;

/**
 * Created by amatullah on 6/4/17.
 */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends BaseAdapter{
    List <PackageInfo> packageList;
    Activity context;
    PackageManager packageManager;
    ArrayList<String> checkedAppNames;
    ArrayList<String> checkedPkgNames;
    boolean[] itemChecked;

    public ListAdapter(Activity context, List <PackageInfo> packageList,
                       PackageManager packageManager) {
        super();
        this.context = context;
        this.packageList = packageList;
        this.packageManager = packageManager;
        this.checkedAppNames = new ArrayList<>();
        this.checkedPkgNames = new ArrayList<>();
        itemChecked = new boolean[packageList.size()];
    }

    private class ViewHolder {
        TextView apkName;
        TextView pkgName;
        CheckBox ck1;
    }



    public ArrayList<String> getSelectedItem() {
        return this.checkedAppNames;
    }

    public ArrayList<String> getSelectedPackage() {
        return this.checkedPkgNames;
    }

    public int getCount() {
        return packageList.size();
    }

    public Object getItem(int position) {
        return packageList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.apkName = (TextView) convertView
                    .findViewById(R.id.textView1);
            holder.pkgName = (TextView) convertView.findViewById(R.id.textView2);
            holder.ck1 = (CheckBox) convertView
                    .findViewById(R.id.checkBox1);

            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        PackageInfo packageInfo = (PackageInfo) getItem(position);

        Drawable appIcon = packageManager
                .getApplicationIcon(packageInfo.applicationInfo);
        String appName = packageManager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
        String packageName = (packageInfo.applicationInfo.packageName);
        appIcon.setBounds(0, 0, 40, 40);
        holder.apkName.setCompoundDrawables(appIcon, null, null, null);
        holder.apkName.setCompoundDrawablePadding(15);
        holder.apkName.setText(appName);
        holder.pkgName.setText(packageName);
        holder.ck1.setChecked(false);

        if (itemChecked[position])
            holder.ck1.setChecked(true);
        else
            holder.ck1.setChecked(false);


        holder.ck1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (holder.ck1.isChecked()) {
                    //checkedAppNames.add(holder.apkName.getText().toString());
                    checkedPkgNames.add(holder.pkgName.getText().toString());
                    itemChecked[position] = true;
                   // Toast.makeText(ListAdapter.this, "HI", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(ListAdapter.this, "retry", Toast.LENGTH_SHORT).show();
                }
                else {
                    itemChecked[position] = false;
                    checkedPkgNames.remove(checkedPkgNames.indexOf(holder.pkgName.getText().toString()));
                    //checkedAppNames.remove(checkedAppNames.indexOf(holder.apkName.getText().toString()));
                }
            }
        });

        return convertView;

    }
}

