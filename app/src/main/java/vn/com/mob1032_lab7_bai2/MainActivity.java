package vn.com.mob1032_lab7_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private EditText txt_Content = null;
    private Button btn_Save = null;
    private Button btn_Load = null;
    final String TAG = "check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapComp();
        /*checkAndRequestPermissions();
        isExternalStorageReadable();*/
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        btn_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    readData();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private void mapComp() {
        if (txt_Content == null)
            txt_Content = findViewById(R.id.txt_Content);
        if (btn_Save == null)
            btn_Save = findViewById(R.id.btn_Save);
        if (btn_Load == null)
            btn_Load = findViewById(R.id.btn_Load);
    }
    public void saveData() throws IOException {
       String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath()+"/khoa.txt";
       OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard));
        writer.write(txt_Content.getText()+"");
        Toast.makeText(this,"Luu thanh cong!",Toast.LENGTH_LONG).show();
        writer.close();
    }

    public void readData() throws FileNotFoundException {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath()+"/khoa.txt";
        Scanner sc = new Scanner(new File(sdcard));
        String data="";
        while(sc.hasNext()){
            data+=sc.nextLine()+"\n";
        }
        Toast.makeText(this,"Doc thanh cong!",Toast.LENGTH_LONG).show();
        sc.close();
        txt_Content.setText(data+"");
    }
    /*private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }*/
}
