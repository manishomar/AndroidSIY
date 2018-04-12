package com.siy.SlideBar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siy.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Manish Omar on 3/15/2018.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<ModelUserChat> modelUserarray;


    public ChatRecyclerAdapter(ArrayList<ModelUserChat> modelUserarray,Context context) {
        this.context=context;
        this.modelUserarray = modelUserarray;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelUserChat user = modelUserarray.get(position);
       // holder.ivProfilePic.setIm
        holder.tvName.setText(user.getName());
        holder.tvMessage.setText(user.getMessage());
        holder.tvTime.setText(user.getTime());

    }

    @Override
    public int getItemCount() {
        return modelUserarray.size();
    }
    //Dismiss
    public void dismiss(int position)
    {
        modelUserarray.remove(position);
        this.notifyItemRemoved(position);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView ivProfilePic;
        public TextView tvName;
        public TextView tvMessage;
        public TextView tvTime;
        public RelativeLayout viewBackground;
        public RelativeLayout viewForeground;


        public MyViewHolder(View itemView) {
            super(itemView);
            ivProfilePic = (CircleImageView) itemView.findViewById(R.id.iv_profilepic_chat);
            tvName = (TextView) itemView.findViewById(R.id.tv_name_chat);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_message_chat);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_chat);
            viewBackground= (RelativeLayout) itemView.findViewById(R.id.view_background);
            viewForeground= (RelativeLayout) itemView.findViewById(R.id.view_foreground);
        }
    }
}
