package sbf.shafi.takephoto;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static int REQUEST_CAPTURE = 1;
    ImageView result_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button click = findViewById(R.id.Bcapture);
        result_photo = findViewById(R.id.imageView);

        if(!hasCamera()){
            click.setEnabled(false);
        }

    }

    public boolean hasCamera()
    {
       return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void LaunchCamera(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extra = data.getExtras();
            Bitmap photo = (Bitmap) extra.get("data");
            result_photo.setImageBitmap(photo);
        }
    }
}
