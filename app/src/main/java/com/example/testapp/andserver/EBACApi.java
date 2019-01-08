package com.example.testapp.andserver;

import com.example.testapp.TestHomeApplication;
import com.yanzhenjie.andserver.annotation.Controller;
import com.yanzhenjie.andserver.annotation.GetMapping;

@Controller
public class EBACApi {

    @GetMapping("/test")
    String login() {
        return "helloword";
    }
}
