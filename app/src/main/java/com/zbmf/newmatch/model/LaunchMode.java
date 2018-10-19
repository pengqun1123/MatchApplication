package com.zbmf.newmatch.model;

import com.zbmf.newmatch.api.HostUrl;
import com.zbmf.newmatch.api.Method;
import com.zbmf.newmatch.api.SendParam;
import com.zbmf.newmatch.bean.Vers;
import com.zbmf.newmatch.model.imode.ILaunchMode;
import com.zbmf.worklibrary.model.BaseMode;
import com.zbmf.worklibrary.model.CallBack;
import com.zbmf.worklibrary.rxjava.RetroSubscrube;
import com.zbmf.worklibrary.util.GsonUtil;

/**
 * Created by xuhao
 * on 2017/11/24.
 */

public class LaunchMode extends BaseMode implements ILaunchMode {
    @Override
    protected String getHost() {
        return HostUrl.WWW_URL;
    }

    @Override
    public void getVers(final CallBack callBack) {
        RetroSubscrube.getInstance().postSubscrube(HostUrl.WWW_URL,
                Method.VERS,
                SendParam.getRequest(Method.VERS,null),new CallBack<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Vers vers= GsonUtil.parseData(o,Vers.class);
                        assert vers != null;
                        if(vers.getStatus()){
                            callBack.onSuccess(vers);
                        }else{
                            callBack.onFail(vers.getErr().getMsg());
                        }

                    }

                    @Override
                    public void onFail(String msg) {
                        callBack.onFail(msg);
                    }
                });
    }
}
