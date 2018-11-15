package com.example.xyzreader.util;

import android.content.Context;

public interface ClickCallBack {
    void onClick(Context context, Integer id, String description, String url, String thumbnailUrl);
}
