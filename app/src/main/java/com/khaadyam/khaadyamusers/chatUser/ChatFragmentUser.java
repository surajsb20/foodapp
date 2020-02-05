package com.khaadyam.khaadyamusers.chatUser;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.build.api.ApiClient;
import com.khaadyam.khaadyamusers.build.api.ApiInterface;
import com.khaadyam.khaadyamusers.helper.ConnectionHelper;
import com.khaadyam.khaadyamusers.helper.GlobalData;
import com.khaadyam.khaadyamusers.utils.TextUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragmentUser extends Fragment implements View.OnClickListener {

    Context context;

    private EditText et_message_user;
    private ImageView btn_send_user;
    ConnectionHelper connectionHelper;
    ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chat_fragment_user, container, false);
        context = getActivity();
        et_message_user = view.findViewById(R.id.et_message_user_c);
        btn_send_user = view.findViewById(R.id.btn_send_user_c);

        btn_send_user.setOnClickListener(this);

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                getChatMessage();
            }

        }, 0, 2000);
        getChatMessage();
        return view;
    }

    //
    @Override
    public void onClick(View view) {
        if (btn_send_user.getId() == view.getId()) {
            initValue();
        }
    }

    public void initValue() {
        String message = et_message_user.getText().toString();
        et_message_user.setText("");
        if (TextUtils.isEmpty(message)) {
            Toast.makeText(context, "Please Enter Something", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("cud_msg", message);
            map.put("cud_order_id", GlobalData.isSelectedOrder.getId().toString());
            map.put("msg_by", "User");
//            if (connectionHelper.isConnectingToInternet()) {
            chatmessagebyuser(map);
//
//            } else {
//                Utils.displayMessage(getActivity(), getContext(), getString(R.string.oops_connect_your_internet));
//            }
        }
    }


    public void chatmessagebyuser(HashMap<String, String> map) {

        Call<ChatUserModel> call = apiInterface.chatUser(map);
        call.enqueue(new Callback<ChatUserModel>() {
            @Override
            public void onResponse(@NonNull Call<ChatUserModel> call, @NonNull Response<ChatUserModel> response) {
                if (response.body() != null) {
                    getChatMessage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChatUserModel> call, @NonNull Throwable t) {
                Toast.makeText(context, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getChatMessage() {

        HashMap<String, String> map = new HashMap<>();

        map.put("cud_order_id", GlobalData.isSelectedOrder.getId().toString());

        Call<NewChatModel> call = apiInterface.getChatUser(map);
        call.enqueue(new Callback<NewChatModel>() {
            @Override
            public void onResponse(@NonNull Call<NewChatModel> call, @NonNull Response<NewChatModel> response) {
                if (response.body() != null) {
                    List<ChatUserModel> chatUserModelList = response.body().getChats();
                    try {
                        RecyclerView mRecycle_jobs = view.findViewById(R.id.chat_view_user_new);
                        mRecycle_jobs.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true));

                        ChatUserAdapter mAdapter_jobs = new ChatUserAdapter(getContext(), chatUserModelList);
                        mRecycle_jobs.setAdapter(mAdapter_jobs);
                        mAdapter_jobs.notifyDataSetChanged();

                        Parcelable recyclerViewState;
                        recyclerViewState = mRecycle_jobs.getLayoutManager().onSaveInstanceState();
                        mRecycle_jobs.getLayoutManager().onRestoreInstanceState(recyclerViewState);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewChatModel> call, @NonNull Throwable t) {
                Toast.makeText(context, "Nothing", Toast.LENGTH_SHORT).show();
            }
        });

    }

//
//    class DoSomeTask extends AsyncTask {
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            return "s";
//        }
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        protected void onPostExecute(String s) {
//            if (s.equalsIgnoreCase("s")) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        getChatMessage();
//                    }
//                }, 5 * 5 * 1000);
//            }
//        }
//    }


}
