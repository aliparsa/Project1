package com.pga.project1.Utilities;

import android.os.AsyncTask;
import android.os.Environment;

import com.pga.project1.Intefaces.ProgressCallBack;
import com.pga.project1.Structures.ErrorPlaceHolder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class AsynLoadImage extends AsyncTask<String, String, String> {


    private static int imageNumber = 0;

    private String imageUrl;
    private ProgressCallBack<String> callback;

    public AsynLoadImage(String imageUrl, ProgressCallBack<String> callback){

        this.imageUrl = imageUrl;
        this.callback = callback;
    }

        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(imageUrl);
                URLConnection connection = url.openConnection();
                connection.connect();
                // this will be useful so that you can show a tipical 0-100% progress
                // bar
                int lengthOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                String path = Environment.getExternalStorageDirectory().toString();
                File file = new File(path, "download" + (imageNumber++) + ".jpg");
                OutputStream output = new FileOutputStream(file);


                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));


                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

                return file.getPath();

            } catch (Exception e) {

            }

            return null;
        }

    @Override
    protected void onProgressUpdate(String... values) {

        callback.onProgress( Integer.parseInt(values[0]), 0, null);
    }

    /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {

            if(file_url == null){
                callback.onError(new ErrorMessage(ErrorPlaceHolder.err2));
            }else{
                callback.onSuccess(file_url);
            }

        }
}