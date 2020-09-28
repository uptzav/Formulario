package com.example.formulario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MostrarDatos extends AppCompatActivity {


    TextView tvnombre, tvapellidos, tvedad, tvcorreo;
    ImageView ivfoto;
    Button btnok;

    public static final int CAMERA_PIC_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        tvnombre = (TextView)findViewById(R.id.tvNombre);
        tvapellidos = (TextView)findViewById(R.id.tvApellido);
        tvedad = (TextView)findViewById(R.id.tvEdad);
        tvcorreo = (TextView)findViewById(R.id.tvCorreo);
        ivfoto = (ImageView)findViewById(R.id.iv_picture);
        btnok = (Button)findViewById(R.id.btnok);


        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarDatos.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mostrarDatos();
    }

    private void mostrarDatos() {
        Bundle datos = this.getIntent().getExtras();
        String nombre = datos.getString("name");
        String apellidos = datos.getString("ape");
        String edad = datos.getString("age");
        String correo = datos.getString("email");


        tvnombre.setText(nombre);
        tvapellidos.setText(apellidos);
        tvedad.setText(edad);
        tvcorreo.setText(correo);


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