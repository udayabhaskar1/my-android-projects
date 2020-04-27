package com.iss.implicit.intents;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button geoBtn = findViewById(R.id.geolocation);
        if (geoBtn != null)
            geoBtn.setOnClickListener(this);

        Button smsBtn = findViewById(R.id.sms);
        if (smsBtn != null)
            smsBtn.setOnClickListener(this);

        Button emailBtn = findViewById(R.id.email);
        if (emailBtn != null)
            emailBtn.setOnClickListener(this);

        Button dialBtn = findViewById(R.id.dial);
        if (dialBtn != null)
            dialBtn.setOnClickListener(this);

        Button contactsBtn = findViewById(R.id.contacts);
        if (contactsBtn != null)
            contactsBtn.setOnClickListener(this);

        Button browseBtn = findViewById(R.id.browse);
        if (browseBtn != null)
            browseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Uri uri = null;
        Intent intent = null;

        switch (v.getId())
        {
            case R.id.browse:
                uri = Uri.parse("https://www.iss.nus.edu.sg");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                break;

            case R.id.contacts:
                uri = Uri.parse("content://contacts/people");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                break;

            case R.id.dial:
                uri = Uri.parse("tel:(+65)12345678");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                break;

            case R.id.email:
                uri = Uri.parse("mailto:someone@somewhere.com");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Yo!");
                intent.putExtra(Intent.EXTRA_TEXT, "Want to grab lunch today?");
                break;

            case R.id.geolocation:
                uri = Uri.parse("geo:1.296643,103.776398");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                break;

            case R.id.sms:
                uri = Uri.parse("smsto:12345678");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Grab coffee?");
                break;
        }

        if (intent != null)
            if (intent.resolveActivity(getPackageManager()) != null)
                startActivity(intent);
    }
}
