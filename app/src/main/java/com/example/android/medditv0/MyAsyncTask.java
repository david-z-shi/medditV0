package com.example.android.medditv0;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, String> {
    public interface MyAsyncTaskListener {
        void onPreExecuteConcluded();
        void onPostExecuteConcluded(String result);
    }
    private MyAsyncTaskListener mListener;

    final public void setListener(MyAsyncTaskListener listener) {
        mListener = listener;
    }

    @Override
    final protected String doInBackground(Void... progress) {
        // do stuff, common to both activities in here
        return null;
    }

    @Override
    protected void onPreExecute() {
        // common stuff
        if (mListener != null)
            mListener.onPreExecuteConcluded();
    }

    @Override
    protected void onPostExecute(String result) {
        // common stuff
        if (mListener != null)
            mListener.onPostExecuteConcluded(result);
    }
}