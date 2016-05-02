package com.example.learnprogect;

import cn.org.octopus.wheelview.ChooseActivity;
import com.example.learnprogect.socket.TcpSocket;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener,
		OnSeekBarChangeListener {
	private Button ledSwitch, choosemilkButton,milk_drink;
	private Button upB, downB, leftB, rightB, stopB;
	private TextView temp,hehum,tagname;
	private TcpSocket tcpSocket;
	private byte ledStatus,milk,water;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				byte bytes[] = (byte[])msg.obj;
				refreshView(bytes); // 刷新界面
				break;

			default:
				break;
			}

		};
	};
	//线程问题

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏		
		setContentView(R.layout.activity_main);
		findView();//初始化按键
		Intent intent=getIntent();
		String babynamee=intent.getStringExtra("baby");
		tagname.setText(babynamee);
		//Log.d("MainActivity", babynamee);
		tcpSocket = new TcpSocket(handler);// 初始化tcp连接
	}

	private void findView() {
		ledSwitch = (Button) findViewById(R.id.ledSwitch);
		choosemilkButton=(Button)findViewById(R.id.choose_milk);
		upB = (Button) findViewById(R.id.upButton);
		downB = (Button) findViewById(R.id.downButton);
		leftB = (Button) findViewById(R.id.leftButton);
		rightB = (Button) findViewById(R.id.rightButton);
		stopB = (Button) findViewById(R.id.stopButton);
		tagname=(TextView)findViewById(R.id.tag_name);
		milk_drink=(Button)findViewById(R.id.milk_drink);
/*		water_drink=(Button)findViewById(R.id.water_drink);*/
		// 注册点击事件
		upB.setOnClickListener(this);
		ledSwitch.setOnClickListener(this);
		downB.setOnClickListener(this);
		leftB.setOnClickListener(this);
		rightB.setOnClickListener(this);
		stopB.setOnClickListener(this);
		choosemilkButton.setOnClickListener(this);
	}

	private void refreshView(byte bytes[]) {
		int choice = (bytes[0]&0xff);
		switch (choice) {
		case 0x01:

			break;
		case 0x02:
			int tem =  (bytes[1]&0xff);
			int hum =  (bytes[2]&0xff);
            temp.setText(""+ tem);
            hehum.setText(""+ hum);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View arg0) {
		byte bytes[] = null ;
		switch (arg0.getId()) {
		case R.id.leftButton:   // 电机左
			
			bytes = new byte[]{0x03,0x01};
			sendMessage(bytes);
			break;
		case R.id.rightButton:  // 电机右
			bytes = new byte[]{0x03,0x02};
			sendMessage(bytes);
			break; 
		case R.id.upButton:     // 点击上
			bytes = new byte[]{0x03,0x03};
			sendMessage(bytes);
			break;
		case R.id.downButton: //  点击下
			
			bytes = new byte[]{0x03,0x04};
			sendMessage(bytes);
			break;
		case R.id.stopButton:   //点击停止
			
			bytes = new byte[]{0x03,0x05};
			sendMessage(bytes);
			break;
		case R.id.ledSwitch:  //控制led
			
			if (ledStatus == 0) {
				ledStatus = 0x01;
				ledSwitch.setBackgroundResource(R.drawable.on);
			}else {
				ledStatus = 0x00;
				ledSwitch.setBackgroundResource(R.drawable.off);
			}
			bytes = new byte[] { 0x01,ledStatus };
			sendMessage(bytes);
			break;
		case R.id.choose_milk:
			Intent intent=new Intent(MainActivity.this,ChooseActivity.class);
//			Intent intent2=new Intent(MainActivity.this,ChooseActivity.class);
			startActivityForResult(intent,1);
//			startActivityForResult(intent2, 2);
		default:
			break;
		}
		
	}
	
	@Override
	public void onActivityResult(int requestCode,int resultCode,Intent data)
	{
		switch (requestCode) {
		case 1:
			if(resultCode==RESULT_OK){
				final String returnmilk=(String) data.getExtras().getSerializable("return_milk");
				final String returnwater=(String) data.getExtras().getSerializable("return_water");
//				Log.d("MainActivity", returnwater);
				milk_drink.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						if (returnmilk.equals(10+"")) {
//							Log.d("MainActivity",returnmilk);
//							Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
							milk=0x02;
						}
						if(returnmilk.equals(15+"")){
							milk=0x03;
						}
						if (returnmilk.equals(20+"")) {
							milk=0x04;
						}
						if (returnmilk.equals(25+"")) {
							milk=0x05;
						}
						if (returnmilk.equals(30+"")) {
							milk=0x06;
						}
						if (returnmilk.equals(35+"")) {
							milk=0x07;
						}
//						byte[] bytes=new byte[]{milk};
//						sendMessage(bytes);
//						Toast.makeText(MainActivity.this, "奶粉已确定", Toast.LENGTH_SHORT).show();
//					}
//				});
				
//				water_drink.setOnClickListener(new OnClickListener() {
//					@Override
//					public void onClick(View v) {
						// TODO Auto-generated method stub							
							if (returnwater.equals(50+"")) {
								water=0x09;
							}							
						    if (returnwater.equals(70+"")) {
								water=0x10;
							}
							if (returnwater.equals(100+"")) {
								water=0x11;
							}
							if (returnwater.equals(120+"")) {
								water=0x12;
							}
							if (returnwater.equals(150+"")) {
								water=0x13;
							}
							if (returnwater.equals(170+"")) {
								water=0x14;
							}
							if (returnwater.equals(200+"")) {
								water=0x15;
							}
							if (returnwater.equals(230+"")) {
								water=0x16;
							}
							if (returnwater.equals(260+"")) {
								water=0x17;
							}
					byte[] bytes=new byte[]{milk,water};
//					Log.i("bytes",water+"");
					sendMessage(bytes);
					Toast.makeText(MainActivity.this, "数据已发送", Toast.LENGTH_SHORT).show();
					}
				});
			}
				default:
					break;
		}
	}
	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		byte[] bytes = new byte[] { 0x05,(byte) arg1 };
		sendMessage(bytes);
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {

	}

    private void sendMessage(final byte[] bytes) {
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				tcpSocket.sendMessage(bytes);
				
			}
		}).start();
    }
}
