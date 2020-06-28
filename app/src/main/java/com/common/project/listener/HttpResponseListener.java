package com.common.project.listener;

public interface HttpResponseListener {
    void onSuccess(String json);
    void onFailed(String json);
}
