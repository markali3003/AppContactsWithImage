package com.example.appcontacts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class ContactAdpter extends ArrayAdapter<Contact> {

    Context context ;
    int resource ;


    public ContactAdpter(@NonNull Context context, int resource, @NonNull ArrayList<Contact> contact) {
        super(context, resource, contact);
        this.context=context ;
        this.resource=resource ;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       convertView =  LayoutInflater.from(context).inflate(resource,parent ,false);

        TextView txt_name = convertView.findViewById(R.id.textView_name) ;
        TextView txt_phone = convertView.findViewById(R.id.textView_phone) ;
        ImageView imageView = convertView.findViewById(R.id.imageuser) ;
              String name = getItem(position).getName() ;
              String phone = String.valueOf(getItem(position).getPhone()) ;
        Bitmap bitmap = BitmapFactory.decodeByteArray(getItem(position).getImage(),0,getItem(position).getImage().length);
              txt_name.setText(name);
              txt_phone.setText(phone);
              imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
