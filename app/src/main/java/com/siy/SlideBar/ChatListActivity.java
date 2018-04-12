package com.siy.SlideBar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.siy.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatRecyclerAdapter chatRecyclerAdapter;
    private ArrayList<ModelUserChat> userChatArrayList;
    private CircleImageView profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        userChatArrayList = getUserList();
        recyclerView= (RecyclerView) findViewById(R.id.rc_chat_list);
        profileImage= (CircleImageView) findViewById(R.id.iv_profilepic_chat);
        chatRecyclerAdapter=new ChatRecyclerAdapter(userChatArrayList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        recyclerView.setAdapter(chatRecyclerAdapter);


        ItemTouchHelper.Callback callback=new swipeHelperAdapter(chatRecyclerAdapter);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

    }

    public ArrayList<ModelUserChat> getUserList() {
        ArrayList<ModelUserChat> userChats=new ArrayList<>();
        ModelUserChat chat1=new ModelUserChat(profileImage,"Manish","android developer","9:00");
        ModelUserChat chat2=new ModelUserChat(profileImage,"Anurag","ios developer","10:00");
        ModelUserChat chat3=new ModelUserChat(profileImage,"Janmejay","android developer","11:00");
        ModelUserChat chat4=new ModelUserChat(profileImage,"Prabhat","android developer","12:00");
        ModelUserChat chat5=new ModelUserChat(profileImage,"Abhijeet","android developer","1:00");
        ModelUserChat chat6=new ModelUserChat(profileImage,"rishabh","backend developer","2:00");
        ModelUserChat chat7=new ModelUserChat(profileImage,"satyam","android developer and backend","3:00");
        userChats.add(chat1);
        userChats.add(chat2);
        userChats.add(chat3);
        userChats.add(chat4);
        userChats.add(chat5);
        userChats.add(chat6);
        userChats.add(chat7);
        return userChats;
    }

    public void ivToBackActivity(View view) {
        super.onBackPressed();
    }
}
