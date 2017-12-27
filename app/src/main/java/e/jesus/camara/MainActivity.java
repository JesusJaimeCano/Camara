package e.jesus.camara;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button camara;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    camara = findViewById(R.id.camaraBoton);
    foto = findViewById(R.id.imagen);

    camara.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,11);
        }
    });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        foto.setImageBitmap(bitmap);
        crearNotificacion();
    }

    public void crearNotificacion(){

        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, 1, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Han tomado una foto")
                .setContentText("Tomaron una fotografia con tu aplicacion")
                .setSmallIcon(R.drawable.marcador_na_at)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notification.flags |= Notification.FLAG_AUTO_CANCEL; 

        notificationManager.notify(6, notification);


    }
}
