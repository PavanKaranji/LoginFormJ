package com.example.loginformjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomeAdapter extends ArrayAdapter<Object> {

    List<UserData> users;
    LayoutInflater inflater;
    public CustomeAdapter(Context context, int resource,List<UserData> users) {
        super(context, resource);
        this.users = users;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder{
        TextView email;
        TextView phNum;
        TextView fName;
        TextView lName;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final UserData user = users.get(position);
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.table_view,parent,false);
            holder = new ViewHolder();
            holder.email = convertView.findViewById(R.id.emailId);
            holder.email.setText(user.getEmailId());
            holder.phNum = convertView.findViewById(R.id.phNum);
            holder.phNum.setText(user.getPhoneNumber());
            holder.fName = convertView.findViewById(R.id.firstName);
            holder.fName.setText(user.getFirstName());
            holder.lName = convertView.findViewById(R.id.lastName);
            holder.lName.setText(user.getLastName());
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            holder.email = convertView.findViewById(R.id.emailId);
            holder.email.setText(user.getEmailId());
            holder.phNum = convertView.findViewById(R.id.phNum);
            holder.phNum.setText(user.getPhoneNumber());
            holder.fName = convertView.findViewById(R.id.firstName);
            holder.fName.setText(user.getFirstName());
            holder.lName = convertView.findViewById(R.id.lastName);
            holder.lName.setText(user.getLastName());
        }



        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getCount() {
        return users.size();
    }
}
