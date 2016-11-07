package com.example.yangxiaolong.graduateapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangxiaolong.graduateapp.R;
import com.example.yangxiaolong.graduateapp.utils.GETCurrentTime;
import com.example.yangxiaolong.graduateapp.utils.NetWorkListUserContent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WriteCommentActivity extends Activity implements View.OnClickListener{


    @BindView(R.id.image_writeComment_back)
    ImageView imageWriteComment_Back;
    @BindView(R.id.text_writeComment_report)
    TextView textWriteComment_Report;
    @BindView(R.id.relativeLayout_wirte_head)
    RelativeLayout relativeLayoutWirteHead;
    @BindView(R.id.editText_commentContent)
    EditText editTextComment_Content;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.iamgeView_Aa)
    ImageView iamgeView_Aa;
    @BindView(R.id.checkbox_smile)
    CheckBox checkbox_Smile;
    @BindView(R.id.iamgeView_face)
    ImageView iamgeView_Face;
    @BindView(R.id.imageView_feed)
    ImageView imageView_Feed;
    @BindView(R.id.linearLayout_chk)
    LinearLayout linearLayoutChk;
    @BindView(R.id.imageVeiw_smilies)
    ImageView imageView_smilies;

    private String path="http://xb.huabao.me/?json=gender/post_comment_v2";
    private Intent intent;
    private NetWorkListUserContent.GetResultCallback getResultCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        ButterKnife.bind(this);
        textWriteComment_Report.setOnClickListener(this);
        intent=getIntent();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.text_writeComment_report:
                String comment=editTextComment_Content.getText().toString();
                if(TextUtils.isEmpty(comment)){
                    Toast.makeText(this, "亲，请填写评论", Toast.LENGTH_SHORT).show();
                    return;
                }
                int articleId=intent.getIntExtra("articleId",0);
                System.out.println("==================Comment.articleId="+articleId);
                String pathKey="sign=5A44E06971AC928B27FE1F9AC83EA6FF&timestamp="+ GETCurrentTime.getTimeMS()+"&v=2140&comment="+comment+"&articleId="+articleId+"&userId=2172827";
                getResultCallback=new NetWorkListUserContent.GetResultCallback() {
                    @Override
                    public void getMessage(String message) {
                        try {
                            JSONObject jsonObject=new JSONObject(message);
                            if(jsonObject.isNull("Coed")){
                                String result=jsonObject.getString("Message");
                                Toast.makeText(WriteCommentActivity.this, result, Toast.LENGTH_SHORT).show();
                            }else{
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                NetWorkListUserContent.getPostResult(path,pathKey,getResultCallback);

        }
    }
}
