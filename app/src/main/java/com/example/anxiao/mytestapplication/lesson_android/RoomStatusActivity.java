package com.example.anxiao.mytestapplication.lesson_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import com.example.anxiao.mytestapplication.R;
import com.example.anxiao.mytestapplication.app.Logger;
import com.example.anxiao.mytestapplication.app.UnScrollRecycleView;
import com.example.anxiao.mytestapplication.app.WaitingDialog;
import com.example.anxiao.mytestapplication.lesson_android.roomstatus.ResponseRoomTypes;
import com.example.anxiao.mytestapplication.lesson_android.roomstatus.RoomListAdapter;
import com.example.anxiao.mytestapplication.lesson_android.roomstatus.RoomStatusListModel;
import com.example.anxiao.mytestapplication.lesson_android.roomstatus.RoomStatusModel;
import com.example.anxiao.mytestapplication.lesson_http.ExceptionRunable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import http.RestClient;
import http.bean.LoginRequestBean;
import http.bean.UserResponseBean;
import retrofit2.Response;

public class RoomStatusActivity extends AppCompatActivity {

    @BindView(R.id.room_list)
    UnScrollRecycleView roomList;
    final private String HTTPTHREAD = "http_thread";
    private Handler mDoHttpHandler;
    RoomListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mAdapter = new RoomListAdapter(this);
        roomList.setLayoutManager(layoutManager);
        roomList.setAdapter(mAdapter);

        HandlerThread httpThread = new HandlerThread(HTTPTHREAD);
        httpThread.start();
        mDoHttpHandler = new Handler(httpThread.getLooper());


        loadRoomStatus();


    }


    private void loadRoomStatus() {

        final WaitingDialog dialog = new WaitingDialog(this, "waiting", "请稍后...");
        dialog.show();
        mDoHttpHandler.post(new ExceptionRunable() {
            @Override
            public void doingBackground() throws Exception {

                LoginRequestBean bean = new LoginRequestBean();
                bean.setPassword("96e79218965eb72c92a549dd5a330112");
                bean.setUserAccountName("18502938991");
                Response<UserResponseBean> respUser = RestClient.SERVICES().userInfo(bean).execute();
                UserResponseBean user = respUser.body();
                assert user != null;
                Response<ResponseRoomTypes> respRoomType = RestClient.SERVICES().roomType(user.getAuthenticationType() + " " + user.getAuthToken()).execute();
                List<RoomStatusListModel.RoomInfoBean> roomTypes;

                if (respRoomType.body().getErrorCode() == 0) {
                    roomTypes = respRoomType.body().getListhotelRoomInfo();
                } else {
                    throw new Exception(respRoomType.body().getMessage());
                }

                int index = 1;

                @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                String today = format.format(new Date());

                List<RoomStatusModel> roomStatus = new ArrayList<>();
                while (((index - 1) * 8) < roomTypes.size()) {
                    Response<List<RoomStatusModel>> respStatus = RestClient.SERVICES().roomStatus(user.getAuthenticationType() + " " + user.getAuthToken(), today, index).execute();
                    List<RoomStatusModel> temp = respStatus.body();
                    roomStatus.addAll(temp);
                    index++;
                }

                final List<RoomStatusListModel> roomList = new ArrayList<>();
                int tempIndex = 0;
                for (int i = 0; i < roomTypes.size(); i++) {
                    RoomStatusListModel model = new RoomStatusListModel();
                    model.setRoomInfo(roomTypes.get(i));

                    List<RoomStatusModel> statusModels = new ArrayList<>();
                    for (int j = 0; j < 36; j++) {
                        statusModels.add(roomStatus.get(tempIndex));
                        tempIndex++;
                    }
                    model.setStatusList(statusModels);

                    roomList.add(model);
                }

                dialog.dismiss();

                mAdapter.setDates(roomList);
            }
        });

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        Logger.err("dispatchTouchEvent : " + (event.getAction() == MotionEvent.ACTION_MOVE));

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Logger.info("fire");
////
////        if (event.getAction() == MotionEvent.ACTION_MOVE) {
////            Logger.info("X : " + event.getX() + ",Y : " + event.getY());
////        }
//
//        roomList.setScrollY((int) event.getY());

        return super.onTouchEvent(event);
    }
}
