package com.example.testapp

import android.util.Log

val images = arrayListOf(
        "https://up.enterdesk.com/edpic_source/53/a6/78/53a678b383be6eb9607e875339b1d052.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s480x854c/g5/M00/00/03/ChMkJ1fJV_yIVgwUAAn8uDtkga0AAU-HQO67rwACfzQ413.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJ1fJV_yIOrfwAAJzkO0xBPQAAU-HQOiUVoAAnOo916.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIcDniAA9QRHYhtUYAAU-HQOTAP4AD1Bc258.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIPyQAAACzuvmkILEAAU-HQPyXIgAALPS122.jpg",
        "https://up.enterdesk.com/edpic_source/53/a6/78/53a678b383be6eb9607e875339b1d052.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s480x854c/g5/M00/00/03/ChMkJ1fJV_yIVgwUAAn8uDtkga0AAU-HQO67rwACfzQ413.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJ1fJV_yIOrfwAAJzkO0xBPQAAU-HQOiUVoAAnOo916.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIcDniAA9QRHYhtUYAAU-HQOTAP4AD1Bc258.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIPyQAAACzuvmkILEAAU-HQPyXIgAALPS122.jpg",
        "https://up.enterdesk.com/edpic_source/53/a6/78/53a678b383be6eb9607e875339b1d052.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s480x854c/g5/M00/00/03/ChMkJ1fJV_yIVgwUAAn8uDtkga0AAU-HQO67rwACfzQ413.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJ1fJV_yIOrfwAAJzkO0xBPQAAU-HQOiUVoAAnOo916.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIcDniAA9QRHYhtUYAAU-HQOTAP4AD1Bc258.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIPyQAAACzuvmkILEAAU-HQPyXIgAALPS122.jpg",
        "https://up.enterdesk.com/edpic_source/53/a6/78/53a678b383be6eb9607e875339b1d052.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s480x854c/g5/M00/00/03/ChMkJ1fJV_yIVgwUAAn8uDtkga0AAU-HQO67rwACfzQ413.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJ1fJV_yIOrfwAAJzkO0xBPQAAU-HQOiUVoAAnOo916.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIcDniAA9QRHYhtUYAAU-HQOTAP4AD1Bc258.jpg",
        "http://sjbz.fd.zol-img.com.cn/t_s640x1136c/g5/M00/00/03/ChMkJlfJV_yIPyQAAACzuvmkILEAAU-HQPyXIgAALPS122.jpg"
)

const val tag = "tools"

fun debug(message: String) {
    Log.d(tag, message)
}

fun info(message: String) {
    Log.i(tag, message)
}

fun error(message: String) {
    Log.e(tag, message)
}

fun dp2px(dp: Int): Int {
    val density = TestHomeApplication.getInstance().resources.displayMetrics.density
    return (dp * density).toInt()
}