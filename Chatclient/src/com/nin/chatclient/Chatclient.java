package com.nin.chatclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Chatclient extends Activity {
	
	 static BufferedReader fromServer = null;

	 private EditText textOut; //write msg  
	    private TextView textIn; //show received msg  
	    private EditText ipaddressEdt; //enter ip address of server  
	    private EditText portno; //port no  
	//    InternetTask task ;
	    static Socket socket = null;  
        DataOutputStream dataOutputStream = null;  
        DataInputStream dataInputStream = null;  
        private Handler handler = new Handler();
        private String serverIpAddress = "";
        private boolean connected = false;

        int flag=0;
	    /** Called when the activity is first created. */  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.activity_chatclient);  
	        ipaddressEdt = (EditText)findViewById(R.id.EditText01);  
	 //       task = new InternetTask();
	        textOut = (EditText)findViewById(R.id.msg);  
	        Button buttonSend = (Button)findViewById(R.id.myButton);  
	        textIn = (TextView)findViewById(R.id.texin);  
	        buttonSend.setOnClickListener(buttonSendOnClickListener);  
	    }  
	  
	    Button.OnClickListener buttonSendOnClickListener  
	    = new Button.OnClickListener(){  
	  
	        @Override  
	        public void onClick(View arg0) {  
	         if (!connected) {
	        		 
	                 serverIpAddress = ipaddressEdt.getText().toString();
	             /*  if (!serverIpAddress.equals("")) {
	                     Thread cThread = new Thread(new ClientThread());
	                     cThread.start();
	                }*/
	            }
	         Thread cThread = new Thread(new ClientThread());
             cThread.start();
	//        	task.execute();
	      // 	textOut.setText("");
	                    
	        }};  
	        
	        
	        public class ClientThread implements Runnable {

	            public void run() {
	                try {
	                	
	                  InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
	                    Log.d("ClientActivity", "C: Connecting...");
	                    Socket socket = new Socket(serverAddr, 8889);
	                    connected = true;
	                    while (connected) {
	                        try {
	                        	
	                            Log.d("ClientActivity", "C: Sending command.");
	                         PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
	                                        .getOutputStream())), true);  //write to server
	                           
	                           out.println(textOut.getText().toString());
	                           out.flush();
	                           
	                           fromServer = new BufferedReader(
	                   				new InputStreamReader(socket.getInputStream())); //read from server
	                           
	                           textIn.setText(fromServer.readLine());
	                           
	                     /*       dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
	                            dataOutputStream.writeUTF(textOut.getText().toString());*/ 
	                     //     dataInputStream = new DataInputStream(socket.getInputStream());  
	                  //          textIn.setText(dataInputStream.readUTF()+"");
	                            // where you issue the commands
	                           connected = false;
	                                Log.d("ClientActivity", "C: Sent.");
	                        } catch (Exception e) {
	                            Log.e("ClientActivity", "S: Error", e);
	                        }
	                    }
	                    
	                    
	                    Log.d("ClientActivity", "C: Closed.");
	                } catch (Exception e) {
	                    Log.e("ClientActivity", "C: Error", e);
	                    connected = false;
	                    
	                }
	                finally{  
		                if (socket != null){  
		                    try {  
		                        //close socket connection after using it otherwise next time when u reconnect on  the same port  
		                        // it will throw error if you dont close it   
		  
		                        socket.close();  
		                    } catch (IOException e) {  
		                        // TODO Auto-generated catch block  
		                        e.printStackTrace();  
		                    }  
		                } 
		                
	                }
	                
	                
	            }
	            
	           
	        }

	     /*   public class InternetTask extends AsyncTask<Void, Integer, Void> {

	            private WeakReference<TextView> mUpdateView;

	               
				@Override
				protected Void doInBackground(Void... params) {
					
					
			  
			            try {  
			            	if (flag==0)
			                socket = new Socket(ipaddressEdt.getText().toString().trim(), 8889);  
			                dataOutputStream = new DataOutputStream(socket.getOutputStream());  
			                dataInputStream = new DataInputStream(socket.getInputStream());  
			                dataOutputStream.writeUTF(textOut.getText().toString());  
			              //  textIn.setText(dataInputStream.readUTF());
			                flag=1;
			            } catch (UnknownHostException e) {  
			                //if specified ip address is not found in the network  
			                e.printStackTrace();  
			            } catch (IOException e) {  
			                // TODO Auto-generated catch block  
			                e.printStackTrace();  
			            }  
			            finally{  
			                if (socket != null){  
			                    try {  
			                        //close socket connection after using it otherwise next time when u reconnect on  the same port  
			                        // it will throw error if you dont close it   
			  
			                        socket.close();  
			                    } catch (IOException e) {  
			                        // TODO Auto-generated catch block  
			                        e.printStackTrace();  
			                    }  
			                }  
			  
			                if (dataOutputStream != null){  
			                    try {  
			                        //close outputstream  
			                        dataOutputStream.close();  
			                    } catch (IOException e) {  
			                        // TODO Auto-generated catch block  
			                        e.printStackTrace();  
			                    }  
			                }  
			  
			                if (dataInputStream != null){  
			                    try {  
			                        //close inputsteam  
			                        dataInputStream.close();  
			                    } catch (IOException e) {  
			                        // TODO Auto-generated catch block  
			                        e.printStackTrace();  
			                    }  
			                }  
			            }  
					// TODO Auto-generated method stub
					return null;
				}
				
				
				
				 @Override
				    protected void onProgressUpdate(Integer... values) {
				        if(mUpdateView.get() != null && values.length > 0){
				                     mUpdateView.get().setText(values[0].toString());
				                }
				    }
				 
				protected void  publishProgress(){
						// execution of result of Long time consuming operation
					    try {
							textIn.setText(dataInputStream.readUTF());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
				 }


	        }
*/
}
