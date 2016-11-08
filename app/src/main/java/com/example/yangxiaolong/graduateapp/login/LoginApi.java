package com.example.yangxiaolong.graduateapp.login;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.example.yangxiaolong.graduateapp.MyApp;
import com.example.yangxiaolong.graduateapp.MyApplication;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;

public class LoginApi implements Callback {
	private static final int MSG_AUTH_CANCEL = 1;
	private static final int MSG_AUTH_ERROR= 2;
	private static final int MSG_AUTH_COMPLETE = 3;

	private OnLoginListener loginListener;
	private String platform;
	private Context context;
	private Handler handler;

	public LoginApi() {
		handler = new Handler(Looper.getMainLooper(), this);
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public void setOnLoginListener(OnLoginListener login){
		this.loginListener=login;
	}

	public void login(Context context) {
		this.context = context.getApplicationContext();
		if (platform == null) {
			return;
		}

		//初始化SDK
		ShareSDK.initSDK(context);
		Platform plat = ShareSDK.getPlatform(platform);
		if (plat == null) {
			return;
		}

		if (plat.isAuthValid()) {
			plat.removeAccount(true);
			return;
		}

		//使用SSO授权，通过客户单授权
		plat.SSOSetting(false);
		plat.setPlatformActionListener(new PlatformActionListener() {
			public void onComplete(Platform plat, int action,HashMap<String, Object> res) {
				if (action == Platform.ACTION_USER_INFOR) {
					Message msg = new Message();
					msg.what = MSG_AUTH_COMPLETE;
					msg.arg2 = action;
					msg.obj =  new Object[] {plat.getName(), res};
					handler.sendMessage(msg);
				}
			}

			public void onError(Platform plat, int action, Throwable t) {
				if (action == Platform.ACTION_USER_INFOR) {
					Message msg = new Message();
					msg.what = MSG_AUTH_ERROR;
					msg.arg2 = action;
					msg.obj = t;
					handler.sendMessage(msg);
				}
				t.printStackTrace();
			}

			public void onCancel(Platform plat, int action) {
				if (action == Platform.ACTION_USER_INFOR) {
					Message msg = new Message();
					msg.what = MSG_AUTH_CANCEL;
					msg.arg2 = action;
					msg.obj = plat;
					handler.sendMessage(msg);
				}
			}
		});
		plat.showUser(null);
	}

	/**处理操作结果*/
	public boolean handleMessage(Message msg) {
		switch(msg.what) {
			case MSG_AUTH_CANCEL: {
				// 取消
				Toast.makeText(context, "canceled", Toast.LENGTH_SHORT).show();
			} break;
			case MSG_AUTH_ERROR: {
				// 失败
				Throwable t = (Throwable) msg.obj;
				String text = "caught error: " + t.getMessage();
				Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
				t.printStackTrace();
			} break;
			case MSG_AUTH_COMPLETE: {
				// 成功
				Object[] objs = (Object[]) msg.obj;
				String plat = (String) objs[0];
				@SuppressWarnings("unchecked")
				HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
				if (loginListener!= null && loginListener.onLogin(plat, res)) {
					Platform platform=ShareSDK.getPlatform(plat);
					PlatformDb platformDb=platform.getDb();
					String name=platformDb.getUserName();


					String userIcon=platformDb.getUserIcon();
					String userId=platformDb.getUserId();

                    UserInfo userInfo=new UserInfo();
					userInfo.setUserName(name);
					userInfo.setUserIcon(userIcon);
                    if("m".equals(platformDb.getUserGender())){
						userInfo.setUserGender(UserInfo.Gender.MALE);
					}else{
						userInfo.setUserGender(UserInfo.Gender.FEMALE);
					}
                    userInfo.setUserNote(platformDb.getUserId());

					System.out.println("userInfo"+userIcon);

					SharedPreferences sharedPreferences=((MyApplication)((Activity)context).getApplication()).getSharedPreferences("userInfo.xml",Context.MODE_PRIVATE);
					SharedPreferences.Editor editor=sharedPreferences.edit();

					editor.putString("name",userInfo.getUserName());
					editor.putString("userId",userInfo.getUserNote());
					editor.putString("userIcon",userInfo.getUserIcon());
					editor.putString("flag","1");

					editor.commit();

					((MyApplication)((Activity)context).getApplication()).flag=1;

					((MyApplication)((Activity)context).getApplication()).loginFlag=true;
					((Activity) context).finish();
				}
			} break;
		}
		return false;
	}

}
