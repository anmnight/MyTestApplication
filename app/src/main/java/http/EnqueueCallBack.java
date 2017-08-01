package http;

/**
 * Created by anxiao on 2017/8/1.
 * 异步回调方法
 */

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class EnqueueCallBack<T> implements Callback<BaseResponseBean<T>> {

    @Override
    public void onResponse(Call<BaseResponseBean<T>> call, Response<BaseResponseBean<T>> response) {

        switch (response.body().getCode()) {
            case "OK":
                _success(response.body().getData());
                break;

            case "Failed":
                _err(response.body().getMessage());
                break;
            case "NotAuth":
                try {
                    throw new Throwable("用户授权异常");
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                break;
        }

        _finish();

    }

    @Override
    public void onFailure(Call<BaseResponseBean<T>> call, Throwable t) {
        _err(t.getMessage());
    }

    public abstract void _finish();

    protected abstract void _success(T response);

    protected abstract void _err(String err);

}
