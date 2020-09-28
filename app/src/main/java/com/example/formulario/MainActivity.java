package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellidos, edad, correo;
    Button aceptar;

    public static final int CAMERA_PIC_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText)findViewById(R.id.editNombre);
        apellidos = (EditText)findViewById(R.id.editApellidos);
        edad = (EditText)findViewById(R.id.editEdad);
        correo = (EditText)findViewById(R.id.editCorreo);
        aceptar = (Button) findViewById(R.id.btnAceptar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nombre.getText().toString();
                String ape = apellidos.getText().toString();
                String age = edad.getText().toString();
                String email = correo.getText().toString();

                Intent i = new Intent(MainActivity.this, MostrarDatos.class);

                i.putExtra("name", name);
                i.putExtra("ape", ape);
                i.putExtra("age", age);
                i.putExtra("email", email);

                startActivity(i);

            }
        });

        Button btn_ubicar = (Button)findViewById(R.id.btn_location);
        btn_ubicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri location = Uri.parse("geo:0.0?q=Mulato+Restomar,+Ejercito,+Tacna");
                Intent locationIntent = new Intent(Intent.ACTION_VIEW,location);
                startActivity(locationIntent);
            }
        });

        ((Button)findViewById(R.id.btn_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requesCode, int resultCode, Intent data){
        super.onActivityResult(requesCode,resultCode,data);
        if(requesCode == CAMERA_PIC_REQUEST){
            if(resultCode == RESULT_OK){
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                ImageView iv_foto = (ImageView)findViewById(R.id.iv_picture);
                iv_foto.setImageBitmap(bitmap);
            }
        }
    }

}