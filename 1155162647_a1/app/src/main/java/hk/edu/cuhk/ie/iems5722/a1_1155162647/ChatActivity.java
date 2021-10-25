package hk.edu.cuhk.ie.iems5722.a1_1155162647;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import java.util.List;
import java.text.SimpleDateFormat;

public class ChatActivity extends AppCompatActivity {

    private ImageButton sendbtn;
    private List<DialogEntity> dialog2 = new ArrayList<>();
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //设置导航栏返回键
        ActionBar actionbar = this.getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);

//        InitDialog2();

        //连接适配器
        ListView listview1 = findViewById(R.id.listview1);
        myAdapter = new MyAdapter();
        listview1.setAdapter(myAdapter);

        //往dialog2数组中填充读取到的数据
        sendbtn = findViewById(R.id.sendbtn);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1 = "";
                EditText edittext1 = (EditText) findViewById(R.id.edittext1);
                String dial = edittext1.getText().toString();
                if (!dial.equals("")){
                    DialogEntity dialogEntity = new DialogEntity();
                    dialogEntity.setDialog(dial);
                    dialogEntity.setTime(getTime());
                    dialog2.add(dialogEntity);
                    myAdapter.notifyDataSetChanged();
                    listview1.setSelection(dialog2.size()-1);
                    edittext1.setText("");
                }
            }
        });
    }

    //返回键执行函数
    public boolean onSupportNavigateUp()
    {
        finish();
        return super.onSupportNavigateUp();
    }


//    public void InitDialog2(){
//        dialog2.add(new DialogEntity("haha","12:30"));
//        dialog2.add(new DialogEntity("my name is Zhu Yilin","12:32"));
////        dialog2.add(new DialogEntity("I live in Tai Wai now","12:32"));
////        dialog2.add(new DialogEntity("nei hou","12:32"));
//    }

    //获取时间戳
    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return format.format(new Date());
    }

    //一个对话+时间的class
    public class DialogEntity{
        private String dialog;
        private String time;

        //从实体提取数据用来显示
        public String getMessage(){return dialog;}
        public String getTime(){return time;}

        //往实体里面添加数据
        public void setDialog(String dialog){
            this.dialog = dialog;
        }
        public void setTime(String time){
            this.time = time;
        }
//        public DialogEntity(String dialog, String time){
//            this.dialog = dialog;
//            this.time = time;
//        }
    }

    //那个Viewholoder定义
    private static class ViewHolder{
        TextView send1;
        TextView send2;
    }

    //根据适配器baseAdapter定义一个自己的适配器
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {return dialog2.size();}

        @Override
        public Object getItem(int i){return dialog2.get(i);}

        @Override
        public long getItemId(int i){return i;}

        @Override
        public  View getView(int i, View convertview, ViewGroup viewGroup){
            ViewHolder holder;
            if(convertview == null){
                holder = new ViewHolder();
                convertview = View.inflate(getApplicationContext(),R.layout.item_dialog,null);
                //textview send1连接对应组件
                holder.send1 = (TextView)convertview.findViewById(R.id.send1);
                holder.send2 = (TextView)convertview.findViewById(R.id.send2);

                convertview.setTag(holder);
            }
            else {holder = (ViewHolder) convertview.getTag();}
            holder.send1.setText(dialog2.get(i).getMessage());
            holder.send2.setText(dialog2.get(i).getTime());

            return convertview;
        }
    }

}

