/**********
 Copyright @ 苏州瑞泰信息技术有限公司 All rights reserved.
 ****************
 创建人   : bluesonli
 创建时间 : 2015/4/8
 说明     : 等待框的dialog
 ****************/
package com.example.anxiao.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * 等待框的dialog
 */
public class WaitingDialog extends ProgressDialog {

    public WaitingDialog (Context context){
        super(context);

    }

    public WaitingDialog(Context context,String title,String message){
        super(context);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(false);
        this.setTitle(title);
        this.setMessage(message);
        this.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                cancel();
            }
        });
    }
}
