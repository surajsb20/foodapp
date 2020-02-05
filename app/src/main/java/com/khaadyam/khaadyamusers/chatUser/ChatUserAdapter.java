package com.khaadyam.khaadyamusers.chatUser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.khaadyam.khaadyamusers.R;

import java.io.InputStream;
import java.util.List;

public class ChatUserAdapter extends RecyclerView.Adapter<ChatUserAdapter.ChatUserHolder> {

    Context context;

    List<ChatUserModel> chatUserModelList;
    private LinearLayout card_category;

    public ChatUserAdapter(Context context, List<ChatUserModel> chatUserModelList) {
        this.context = context;
        this.chatUserModelList = chatUserModelList;

    }

    @Override
    public ChatUserAdapter.ChatUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatuserlayoutcustom, parent, false);
        ChatUserHolder chat_holder = new ChatUserHolder(view);
        card_category = view.findViewById(R.id.main_chat_card);
        return chat_holder;
    }

    @Override
    public void onBindViewHolder(final ChatUserAdapter.ChatUserHolder holder, int position) {


        if (chatUserModelList.get(position).getMsg_by().equalsIgnoreCase("user")) {
            holder.usersendtime.setText(chatUserModelList.get(position).getCud_date());
            holder.usermessage.setText(chatUserModelList.get(position).getCud_msg());
            holder.sender.setVisibility(View.GONE);
        }
        if (chatUserModelList.get(position).getMsg_by().equalsIgnoreCase("Admin")) {
            holder.adminsendtime.setText(chatUserModelList.get(position).getCud_date());
            holder.adminmessage.setText(chatUserModelList.get(position).getCud_msg());

            holder.reciever.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {

        return chatUserModelList.size();
    }

    public class ChatUserHolder extends RecyclerView.ViewHolder {

        private final LinearLayout cardView;
        private final TextView adminmessage;
        private final TextView adminsendtime;
        private final TextView usermessage;
        private final TextView usersendtime;
        private final LinearLayout sender;
        private final LinearLayout reciever;

        public ChatUserHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.main_chat_card);

            usermessage = itemView.findViewById(R.id.usermessage);
            usersendtime = itemView.findViewById(R.id.usersendtime);
            adminmessage = itemView.findViewById(R.id.adminmessage);
            adminsendtime = itemView.findViewById(R.id.adminsendtime);
            sender = itemView.findViewById(R.id.sender);
            reciever = itemView.findViewById(R.id.reciever);

        }
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            // Toast.makeText(context, "Please wait, it may take a few seconds...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }

    }


}
