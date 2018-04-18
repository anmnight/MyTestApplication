package com.example.anxiao.lesson_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.anxiao.app.Logger;
import com.example.anxiao.lesson_android.roomstatus.OberverScrollView;
import com.example.anxiao.lesson_android.roomstatus.RoomNunAdapter;
import com.example.anxiao.mytestapplication.R;
import com.example.anxiao.app.TableRecycleView;
import com.example.anxiao.app.ToastUnit;
import com.example.anxiao.app.WaitingDialog;
import com.example.anxiao.lesson_android.roomstatus.NextLineLayoutManger;
import com.example.anxiao.lesson_android.roomstatus.ResponseRoomTypes;
import com.example.anxiao.lesson_android.roomstatus.RoomListAdapter;
import com.example.anxiao.lesson_android.roomstatus.RoomStatusListModel;
import com.example.anxiao.lesson_android.roomstatus.RoomStatusModel;
import com.example.anxiao.lesson_http.ExceptionRunable;
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
    TableRecycleView roomList;
    @BindView(R.id.room_num)
    ListView roomNum;
    @BindView(R.id.v_scroll_view)
    OberverScrollView vScrollView;


    final private String HTTPTHREAD = "http_thread";
    private Handler mDoHttpHandler;
    RoomListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_status);
        ButterKnife.bind(this);

        NextLineLayoutManger layoutManager = new NextLineLayoutManger();
        layoutManager.setAutoMeasureEnabled(true);
        mAdapter = new RoomListAdapter(this);
        roomList.setLayoutManager(layoutManager);
        roomList.setAdapter(mAdapter);

        HandlerThread httpThread = new HandlerThread(HTTPTHREAD);
        httpThread.start();
        mDoHttpHandler = new Handler(httpThread.getLooper());

        loadRoomStatus();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.room_name_layout,
                R.id.room_num);

        roomNum.setAdapter(adapter);

        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            temp.add(String.valueOf(i));
        }
        adapter.addAll(temp);

        vScrollView.setListener(new OberverScrollView.ScrollListener() {
            @Override
            public void onYChange(int y) {
                roomNum.scrollBy(0, y);
            }
        });
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

            @Override
            public void onErr(Exception e) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastUnit.sortToase(e.getMessage());
            }


        });

    }

}
