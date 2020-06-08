package com.example.appcontacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class Main2ActivityAddContact extends AppCompatActivity {
      EditText editname ;
      EditText editphone ;
      Button but_save ;
      DbContact db ;
      ImageButton imageButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_add_contact);

        editname = findViewById(R.id.editText_name);
        editphone = findViewById(R.id.editText_phone) ;
        but_save =findViewById(R.id.button_save) ;
        imageButton = findViewById(R.id.imagebutttonAdd) ;
        db = new DbContact(this);



    }


    public void saveContact(View view) {

        String name = editname.getText().toString();
        int phone = Integer.parseInt(editphone.getText().toString());
          byte[] image = imageViewToByte(imageButton) ;


        if (editname.length() !=0 ) {
            addData(new Contact(name,phone,image));
            editname.setText("");
            editphone.setText("");
            finish();


        } else {
            toastMethode("colon vide");
        }




    }

private void addData(Contact ct){
    boolean insertdata = db.addContact(ct);
    if(insertdata){
        toastMethode("add with success");
    }else{
        toastMethode("il y a un erreur");
    }
}

    public void openGallorie(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 100 ){
            Uri  uri  = data.getData();

            try{
                InputStream inputStream = getContentResolver().openInputStream(uri) ;
                Bitmap  decodestrem = BitmapFactory.decodeStream(inputStream);
                imageButton.setImageBitmap(decodestrem);

            }catch (Exception ex){
                Log.e("ex",ex.getMessage()) ;

            }




        }
    }



    public Bitmap imageViewToBitmap(ImageButton ib){
        Bitmap bitmap = ((BitmapDrawable)ib.getDrawable()).getBitmap() ;
        return bitmap;
    }
    private byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream  = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);

        return   stream.toByteArray() ;
    }
    private byte[] imageViewToByte(ImageButton ib){
            return bitmapToByte(imageViewToBitmap(ib));
    }
    private void toastMethode(String string){
        Toast.makeText(this,string,Toast.LENGTH_LONG).show();

    }
}
