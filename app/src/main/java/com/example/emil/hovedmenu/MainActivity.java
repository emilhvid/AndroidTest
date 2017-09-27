package com.example.emil.hovedmenu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    private WebView webViewurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button one = (Button) findViewById(R.id.button1);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.button2);
        two.setOnClickListener(this); // calling onClick() method



        webViewurl = (WebView) findViewById(R.id.webView1);

        webViewurl.getSettings().setJavaScriptEnabled(true);

        webViewurl.getSettings().setBuiltInZoomControls(true);
        final Activity activity = this;
        webViewurl.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        final EditText editText = (EditText) findViewById(R.id.editText);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        editText.setInputType(InputType.TYPE_NULL);

        editText.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                editText.requestFocus();
                editText.selectAll();
                if(editText.getInputType() == InputType.TYPE_NULL)
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        Button ok = (Button) findViewById(R.id.button3);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = editText.getText().toString();
                webViewurl.loadUrl("http://"+str);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

        webViewurl.loadUrl("http://javabog.dk");
        webViewurl.computeScroll();
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;

        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText("Du klikkede på " + b.getText().toString());
        // default method for handling onClick Events..
    }
}


