package com.example.learnprogect.login;

import com.example.learnprogect.MainActivity;
import com.example.learnprogect.R;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Login_Activity extends Activity implements OnClickListener{
	private Button login;
	private Button exit;
	EditText etUser;
	RadioButton rbMale,rbfaMale;
	private String babyname;
	private SharedPreferences pref;
	private CheckBox auto_login;

	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);	
		login=(Button)findViewById(R.id.btnlogin);
		exit=(Button)findViewById(R.id.btEixt);
		etUser=(EditText)findViewById(R.id.etUser);
		rbfaMale=(RadioButton)findViewById(R.id.rbfaMale);
		rbMale=(RadioButton)findViewById(R.id.rbMale);
//		按键定义
		auto_login=(CheckBox)findViewById(R.id.remember_name);
		login.setOnClickListener(this);
		exit.setOnClickListener(this);
		pref=PreferenceManager.getDefaultSharedPreferences(this);
		if(pref.getBoolean("AUTO_ISCHECK",false)){
			auto_login.setChecked(true);
		    Intent intent=new Intent(Login_Activity.this,MainActivity.class);
		    intent.putExtra("baby",babyname);
		    startActivity(intent);
		}
		auto_login.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(auto_login.isChecked()){
					pref.edit().putBoolean("AUTO_ISCHECK", true).commit();
				}else {
					pref.edit().putBoolean("AUTO_ISCHECK", false).commit();
				}
			}
		});
	}
	public void onClick(View view){
		switch(view.getId()){
		case R.id.btnlogin:
		    if(TextUtils.isEmpty(etUser.getText())){
		    Toast.makeText(this, "名字不能为空", 2000).show();
		    return;
		    }
		    else {
				babyname=etUser.getText().toString();
			}
		    if(rbMale.isChecked()){
		    rbMale.getText().charAt(0);
		    }else{
		    rbfaMale.getText().charAt(0);
		    }
		    new StringBuffer();
		    Intent intent=new Intent(Login_Activity.this,MainActivity.class);
		    intent.putExtra("baby",babyname);
		    startActivity(intent);
		 case R.id.btEixt:
		  finish();
		    break;
		    }
		}
	}

