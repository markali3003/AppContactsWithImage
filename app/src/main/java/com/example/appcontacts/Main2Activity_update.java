package com.example.appcontacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Main2Activity_update extends AppCompatActivity {
      EditText editname ;
      EditText editphone ;
      Button   buUpdate ;
      ImageButton imageButton ;
      DbContact db ;
      int id ;
      Contact contact ;
    byte[] image ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_update);
        editname = findViewById(R.id.editText_update_name) ;
        editphone = findViewById(R.id.editText_update_phone);
        buUpdate = findViewById(R.id.button_update) ;
        imageButton = findViewById(R.id.imagebutttonUpdate);
        db = new DbContact(this) ;
         id =getIntent().getIntExtra("id",0);
          contact = db.getContactById(id);
        editname.setText(contact.getName());
        editphone.setText(contact.getPhone()+"");
             image = contact.getImage();
                 Bitmap bt = byteToBitmap(image);
        imageButton.setImageBitmap(bt);

        buUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editname.getText().toString() ;
                int phone   = Integer.parseInt(editphone.getText().toString());

                   Bitmap b = imageViewToBitmap(imageButton);
                   byte[] img = bitmapToByte(b);
                db.updateContact(new Contact(id,name,phone,img));
                Toast.makeText(Main2Activity_update.this,"contact update with success",Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.id_delete:
                showAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("confirmation")
                .setMessage("are you sure ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DELETE CODE
                        db.deleteContact(id);
                        Toast.makeText(Main2Activity_update.this,"contact deleted with success",Toast.LENGTH_LONG).show();
                        finish();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = alertBuilder.create();
        dialog.show();

    }

    public void openGallerie(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT) ;
        intent.setType("image/*") ;
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK &&requestCode == 100){
           Uri uri =data.getData();
           try{
               InputStream inputStream = getContentResolver().openInputStream(uri);
              Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);
                  imageButton.setImageBitmap(bitmap);

           }catch (Exception ex){
               Log.e("ex",ex.getMessage());

           }


        }
    }

    public byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream) ;
       return  stream.toByteArray();

    }
public Bitmap imageViewToBitmap(ImageButton view){
Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap() ;
return bitmap;
}
public Bitmap byteToBitmap(byte[] byteimage){
    Bitmap bitmap = BitmapFactory.decodeByteArray(byteimage,0,byteimage.length);


return  bitmap ;

}
}