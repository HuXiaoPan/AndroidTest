package com.example.admin.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;


//litePal的API支持各种条件的组合查询模式，findBySql可以执行sql语句自定义查询，这个方法返回一个cursor对象，然后用传统方法操作。
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createData = findViewById(R.id.create_database);
        createData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
                Toast.makeText(MainActivity.this, "创建数据库", Toast.LENGTH_SHORT).show();
            }
        });

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setAuthor("Dan Brown");
                book.setName("The Da Vinci Code");
                book.setPrice(16.96);
                book.setPages(454);
                book.setPress("Unknow");
                book.save();
                Toast.makeText(MainActivity.this, "生成数据", Toast.LENGTH_SHORT).show();
            }
        });

        //setToDefault 设置所有默认值
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setAuthor("Dan Brown");
                book.setName("The Lost Symbol");
                book.setPrice(19.95);
                book.setPages(510);
                book.setPress("Unknow");
                book.save();
//                Toast.makeText(MainActivity.this, "生成数据", Toast.LENGTH_SHORT).show();
//                book.setPrice(10.99);
//                book.save();
//                Toast.makeText(MainActivity.this, "修改数据", Toast.LENGTH_SHORT).show();
                book.setPrice(14.95);
                book.setPress("Author");
                book.updateAll("name = ? and author = ?","The Lost Symbol","Dan Brown");
                Toast.makeText(MainActivity.this, "修改数据", Toast.LENGTH_SHORT).show();
            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Book.class,"price < ?","15");
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book:books) {
                    Log.d("tag", "book name is:" +book.getName());
                    Log.d("tag", "book author is:" +book.getAuthor());
                    Log.d("tag", "book pages is:" +book.getPages());
                    Log.d("tag", "book price is:" +book.getPrice());
                    Log.d("tag", "book press is:" +book.getPress());
                }
            }
        });
    }
}
