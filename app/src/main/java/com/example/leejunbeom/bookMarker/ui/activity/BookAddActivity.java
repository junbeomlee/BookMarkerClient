package com.example.leejunbeom.bookMarker.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leejunbeom.bookMarker.dagger.injector;
import com.example.leejunbeom.bookMarker.jericho.Jericho;
import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.ui.presenter.BookAddPresenter;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookAddScreen;
import com.example.leejunbeom.bookMarker.util.log.BMLogger;
import com.example.leejunbeom.test.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import net.htmlparser.jericho.Source;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookAddActivity extends AppCompatActivity implements BookAddScreen{

    @Inject
    BookAddPresenter bookAddPresenter;

    @Bind(R.id.confirmButton)
    Button confirmButton;

    @Bind(R.id.bookTitle)
    TextView bookTitieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);

        injector.get().inject(this);
        ButterKnife.bind(this);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //Log.d(BMLogger.LOG_TAG, "started");
        //Log.d(BMLogger.LOG_TAG, scanResult.toString());
        //Log.d(BMLogger.LOG_TAG, scanResult.getContents().toString());
        if ((scanResult != null) && (scanResult.getContents() != null)) {
            String data = scanResult.getContents();
            Toast.makeText(this, data,
                  Toast.LENGTH_LONG).show();
            //Log.d(BMLogger.LOG_TAG, data);
            //qrcodeInfo.append(intent.getStringExtra("Value"));
            //Jericho jericho=new Jericho();
            //Source htmlSource=jericho.getURLtoText("http://library.cau.ac.kr/search/DetailView.ax?sid=1&cid=5241729");
            //Toast.makeText(this,htmlSource,Toast.LENGTH_LONG).show();
            this.bookAddPresenter.getBookData(data);
        }
    }

    @OnClick(R.id.confirmButton)
    public void onCallBack(){
        bookAddPresenter.finishActivity(this);
    }

    @Override
    public void finishBookAddActivity() {
        finish();
    }

    @Subscribe
    public void onSetBookInfo(Book book){
        this.bookTitieView.setText(book.getTitileAuthorsType());
        Log.d(BMLogger.LOG_TAG,book.toString());
    }
}
