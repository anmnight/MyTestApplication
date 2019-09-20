package com.anmnight.imageloader.base;

import java.io.IOException;
import java.io.InputStream;

public interface Downloader {

    //获取数据流
     StreamInfo getStream(String url) throws IOException;

     class StreamInfo {

        private InputStream stream;
        private int contentLength;

        public StreamInfo() {
        }

        public StreamInfo(InputStream stream, int contentLength) {
            this.stream = stream;
            this.contentLength = contentLength;
        }

        public InputStream getStream() {
            return stream;
        }

        public void setStream(InputStream stream) {
            this.stream = stream;
        }

        public int getContentLength() {
            return contentLength;
        }

        public void setContentLength(int contentLength) {
            this.contentLength = contentLength;
        }
    }

}
