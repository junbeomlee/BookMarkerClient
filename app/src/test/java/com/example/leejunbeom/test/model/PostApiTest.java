package com.example.leejunbeom.test.model;

import com.example.leejunbeom.bookMarker.model.PostApi;
import com.example.leejunbeom.bookMarker.model.pojo.Book;
import com.example.leejunbeom.bookMarker.network.okhttp.OkHttp;
import com.example.leejunbeom.test.BuildConfig;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Jun on 16. 4. 12..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PostApiTest {

    PostApi postApi;
    Book testBook;

    @Before
    public void setUp(){
        OkHttp okHttp= Mockito.mock(OkHttp.class);
        try {
            when(okHttp.doPostRequest("http://52.79.133.224/book/getbook/","34679")).
                    thenReturn("{\"map\":\"\",\"success\":true,\"author\":\"石原愼太郞 지음 ; 李俊凡 譯\",\"title\":\"스파르타 敎育 : 强한 사나이로 키우는 冊\",\"feature\":\"\",\"mark\":\"649.1석원신스\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        postApi=new PostApi(okHttp);
    }

    @Test
    public void testPostApiObservableBook() throws Exception {

        final CountDownLatch lock = new CountDownLatch(1);

        postApi.postBook("34679").
                //subscribeOn(Schedulers.io()).
                //observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<Book>() {
                    @Override
                    public void call(Book book) {
                        lock.countDown();
                        testBook=book;
                    }
                });
        try
        {
            lock.await(5000, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e)
        {
            lock.notifyAll();
        }
        //System.out.print(testBook.toString());
        assertEquals("fail post api book",testBook.toString(),"Book{author='石原愼太郞 지음 ; 李俊凡 譯', featureUrl='', title='스파르타 敎育 : 强한 사나이로 키우는 冊', mark='649.1석원신스', mapUrl=''}");
    }
}
