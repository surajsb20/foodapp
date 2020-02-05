package com.khaadyam.khaadyamusers.chatUser;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.khaadyam.khaadyamusers.R;
import com.khaadyam.khaadyamusers.helper.CustomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChatHelpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_chat)
    Toolbar toolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @BindView(R.id.order_id_txt_chat_user)
    TextView orderIdTxt;

    Context context;
    CustomDialog customDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_help);
        ButterKnife.bind(this);
        context = ChatHelpActivity.this;
        customDialog = new CustomDialog(context);
        fragmentManager = getSupportFragmentManager();
//
//        Order order = GlobalData.isSelectedOrder;
        orderIdTxt.setText("ORDER #000" );

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.chat_fragment_user, new ChatFragmentUser());
        fragmentTransaction.commit();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.anim_nothing, R.anim.slide_out_right);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
