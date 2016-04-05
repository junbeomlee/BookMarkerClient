package com.example.leejunbeom.bookMarker.SwipeMenuListView;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leejunbeom.bookMarker.model.Book;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by noduritoto on 2016-04-04.
 */

public class BookAdapter_impl extends BaseAdapter {
    private Context mContext;
    //private BookController mBookController;
    ArrayList<Book> listBook;

    /*
    public BookAdapter_impl(Context context, BookController bookController) {
        this.mContext = context;
        //this.mBookController = bookController;
       //this.listBook = bookController.getBookList();
    }
    */

    public BookAdapter_impl(Context context, ArrayList<Book> listBook){
        this.mContext = context;
        //this.mBookController = bookController;
        this.listBook = listBook;
    }

    @Override
    public int getCount() {
        //return mBookController.getBookList().size();
        return listBook.size();
    }

    @Override
    public Book getItem(int position) {
        //return mBookController.getBookList().get(position);
        return listBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        // menu type count
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        // current menu type
        return position % 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = View.inflate(mContext.getApplicationContext(),
                    R.layout.item_list_app, null);
            new BookHolder(convertView);
        }

        /*
        ViewHolder holder = (ViewHolder) convertView.getTag();
        ApplicationInfo item = getItem(position);
        holder.iv_icon.setImageDrawable(item.loadIcon(mContext.getPackageManager()));
        holder.tv_name.setText(item.loadLabel(mContext.getPackageManager()));
        */

        BookHolder holder = (BookHolder) convertView.getTag();
        Book book = getItem(position);
        holder.book_name.setText(book.getSymbolicRequest());
        return convertView;
    }

    class BookHolder{
        TextView book_name;
        //ImageView iv_icon;

        public BookHolder(View view){
            book_name = (TextView) view.findViewById(R.id.book_name);
            //iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            view.setTag(this);
        }
    }

    /*
    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;

        public ViewHolder(View view) {
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }
    */
}
