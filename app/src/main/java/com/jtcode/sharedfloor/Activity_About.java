package com.jtcode.sharedfloor;

import android.content.Intent;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jtcode.sharedfloor.interfaces.CustomConstants;

public class Activity_About extends AppCompatActivity {

    ImageView imgShared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__about);

        imgShared= (ImageView)findViewById(R.id.A_ABOUT_IMVlink);

        imgShared.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(CustomConstants.urlAbout));
                startActivity(intent);
            }
        });
    }
}
