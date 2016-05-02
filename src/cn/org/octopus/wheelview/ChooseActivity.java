package cn.org.octopus.wheelview;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.example.learnprogect.R;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.org.octopus.wheelview.widget.ArrayWheelAdapter;
import cn.org.octopus.wheelview.widget.OnWheelChangedListener;
import cn.org.octopus.wheelview.widget.OnWheelScrollListener;
import cn.org.octopus.wheelview.widget.WheelView;

public class ChooseActivity extends Activity{

	public static final String TAG = "octopus.activity";
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private static Button rush_milk,cancle_milk,clr_log,bt_click;
	private static TextView text_milk;
	private static TextView text_water,tag_name;
	private static TextView log1,log2,log3,log4,log5,log6,log7;
	public String vLeft;
	public String vRight,str;
	public String log_1,log_2,log_3,log_4,log_5,log_6,log_7;
	
	public int i;//记录的次数
	public String city[] = new String[] { "10", "15", "20", "25", "30", "35"};

    public String province[][] = new String[][]{
            new String[] {"50","70","100","120", "150", "170", "200", "230", "260"},
            new String[] {"50","70","100","120", "150", "170", "200", "230", "260"},
            new String[] {"50","70","100","120", "150", "170", "200", "230", "260"},
            new String[] {"50","70","100","120", "150", "170", "200", "230", "260"},
            new String[] {"50","70","100","120", "150", "170", "200", "230", "260"},
            new String[] {"50","70","100","120", "150", "170", "200", "230", "260"}};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_main);
		
		log1=(TextView)findViewById(R.id.log1);
		log2=(TextView)findViewById(R.id.log2);
		log3=(TextView)findViewById(R.id.log3);
		log4=(TextView)findViewById(R.id.log4);
		log5=(TextView)findViewById(R.id.log5);
		log6=(TextView)findViewById(R.id.log6);
		log7=(TextView)findViewById(R.id.log7);
		//记录
		text_milk=(TextView)findViewById(R.id.edit_milk);
		text_water=(TextView)findViewById(R.id.edit_water);
		tag_name=(TextView)findViewById(R.id.tag_name);
		clr_log=(Button)findViewById(R.id.clr_log);
		rush_milk=(Button)findViewById(R.id.rush_milk);
		cancle_milk=(Button)findViewById(R.id.cancel_milk);
		pref=PreferenceManager.getDefaultSharedPreferences(this);
		//显示之前的冲奶记录
		log_1=pref.getString("log1", "");
		log1.setText(log_1);
		log_2=pref.getString("log2", "");
		log2.setText(log_2);
		log_3=pref.getString("log3", "");
		log3.setText(log_3);
		log_4=pref.getString("log4", "");
		log4.setText(log_4);
		log_5=pref.getString("log5", "");
		log5.setText(log_5);
		log_6=pref.getString("log6", "");
		log6.setText(log_6);
		log_7=pref.getString("log7", "");
		log7.setText(log_7);
		//设置冲奶量完成并记录
		rush_milk.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 if(TextUtils.isEmpty(text_milk.getText())){
	                	Toast.makeText(ChooseActivity.this, "请选择奶粉量", Toast.LENGTH_SHORT).show();
	                }
				 else {	
					 SimpleDateFormat formatter=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
					 Date curDate=new Date(System.currentTimeMillis());
					 str=formatter.format(curDate);
					 editor=pref.edit();
					 if(TextUtils.isEmpty(log1.getText())){		
						 editor.putString("log1", str+" "+"冲奶量:"+vLeft+"g"+" "+"冲水量:"+vRight+"mL");
					 }else if(TextUtils.isEmpty(log2.getText())){
						 editor.putString("log2", str+" "+"冲奶量:"+vLeft+"g"+" "+"冲水量:"+vRight+"mL");
					 }else if(TextUtils.isEmpty(log3.getText())){
						 editor.putString("log3", str+" "+"冲奶量:"+vLeft+"g"+" "+"冲水量:"+vRight+"mL");
					 }else if(TextUtils.isEmpty(log4.getText())){
						 editor.putString("log4", str+" "+"冲奶量:"+vLeft+"g"+" "+"冲水量:"+vRight+"mL");
					 }else if(TextUtils.isEmpty(log5.getText())){
						 editor.putString("log5", str+" "+"冲奶量:"+vLeft+"g"+" "+"冲水量:"+vRight+"mL");
					 }else if(TextUtils.isEmpty(log6.getText())){
						 editor.putString("log6", str+" "+"冲奶量:"+vLeft+"g"+" "+"冲水量:"+vRight+"mL");
					 }else if(TextUtils.isEmpty(log7.getText())){
						 editor.putString("log7", str+" "+"冲奶量:"+vLeft+"g"+" "+"冲水量:"+vRight+"mL");
					 }
					 editor.commit();
//					 Log.d("fuck", str);
					 Intent intent=new Intent();
					 intent.putExtra("return_milk", vLeft);
					 intent.putExtra("return_water", vRight);
					 setResult(RESULT_OK,intent);
					 finish();
				 }
			}
		});
		
		clr_log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor=pref.edit();
				editor.clear().commit();
				AlertDialog.Builder dialog=new AlertDialog.Builder(ChooseActivity.this);
				dialog.setTitle("警告");
				dialog.setMessage("确定删除所有记录?");
				dialog.setCancelable(false);
				dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						log1.setText("");
						log2.setText("");
						log3.setText("");
						log4.setText("");
						log5.setText("");
						log6.setText("");
						log7.setText("");
					}
				});
				dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				});
				dialog.show();
			}
		});
		cancle_milk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				setResult(RESULT_CANCELED,intent);
				finish();
			}
		});
		Intent intent=getIntent();
		String babynamee=intent.getStringExtra("baby");
		tag_name.setText(babynamee);
		}
	
	
	/*
	 * 点击事件
	 */
	public void onClick(View view) {
		showSelectDialog(this, "选择奶粉|选择水量", city, province );
	}

	
	private void showSelectDialog(Context context, String title, final String[] left, final String[][] right) {
    	//创建对话框
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        //为对话框设置标题
        dialog.setTitle(title);
        //创建对话框内容, 创建一个 LinearLayout 
        LinearLayout llContent = new LinearLayout(context);
        //将创建的 LinearLayout 设置成横向的
        llContent.setOrientation(LinearLayout.HORIZONTAL);
        //创建 WheelView 组件
        final WheelView wheelLeft = new WheelView(context);
        //设置 WheelView 组件最多显示 5 个元素
        wheelLeft.setVisibleItems(5);
        //设置 WheelView 元素是否循环滚动
        wheelLeft.setCyclic(false);
        //设置 WheelView 适配器
        wheelLeft.setAdapter(new ArrayWheelAdapter<String>(left));
        //设置右侧的 WheelView
        final WheelView wheelRight = new WheelView(context);
        //设置右侧 WheelView 显示个数
        wheelRight.setVisibleItems(5);
        //设置右侧 WheelView 元素是否循环滚动
        wheelRight.setCyclic(true);
        //设置右侧 WheelView 的元素适配器
        wheelRight.setAdapter(new ArrayWheelAdapter<String>(right[0]));
        //设置 LinearLayout 的布局参数
        LinearLayout.LayoutParams paramsLeft = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 4);
        paramsLeft.gravity = Gravity.LEFT;
        LinearLayout.LayoutParams paramsRight = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, 6);
        paramsRight.gravity = Gravity.RIGHT;
        //将 WheelView 对象放到左侧 LinearLayout 中
        llContent.addView(wheelLeft, paramsLeft);
        //将 WheelView 对象放到 右侧 LinearLayout 中
        llContent.addView(wheelRight, paramsRight);
        
        //为左侧的 WheelView 设置条目改变监听器
        wheelLeft.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
            	//设置右侧的 WheelView 的适配器
                wheelRight.setAdapter(new ArrayWheelAdapter<String>(right[newValue]));
                wheelRight.setCurrentItem(right[newValue].length / 2);
            }
        });
       //设置滚动监听器 
        wheelLeft.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				
			}
		});
        
        //设置对话框点击事件 积极
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	int leftPosition = wheelLeft.getCurrentItem();
                vLeft = left[leftPosition];
                vRight = right[leftPosition][wheelRight.getCurrentItem()];
//                bt_click.setText(vLeft+"-"+vRight);
                text_milk.setText(vLeft+"g");
                text_water.setText(vRight+"ml");
                dialog.dismiss();
            }
        });
        
        //设置对话框点击事件 消极
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //将 LinearLayout 设置到 对话框中
        dialog.setView(llContent);
        //显示对话框
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			bt_click=(Button)rootView.findViewById(R.id.bt_click);
			return rootView;
		}
	}

}
