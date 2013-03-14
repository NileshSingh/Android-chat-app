package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Chatserver  { 
	  /** 
     * This is server class which accepts all request through device and search for it..... 
     * @param args 
     */  
    private static boolean flag=true;  
    static BufferedReader fromClient = null;
    static String numbStr= null;
    
    public static void main(String[] args){  
        ServerSocket serverSocket = null;  
        Socket socket = null;  
        DataInputStream dataInputStream = null;  
        DataOutputStream dataOutputStream = null;  
      
  
        try {  
            serverSocket = new ServerSocket(8889); //port number  
            System.out.println("Listening :on port no 8889");  
        }catch (BindException e ) {  
            System.out.println("port is already used by some1");  
            flag=false;  
        }   
        catch (IOException e) {  
            flag=false;  
            e.printStackTrace();  
        }  
        System.out.println("value of flag....." + flag);  
        while(flag){  
         
        	try {  
                socket = serverSocket.accept(); //accept client connection  
                
                fromClient = new BufferedReader(
        				new InputStreamReader(socket.getInputStream())); //read from client
                
               // dataInputStream = new DataInputStream(socket.getInputStream()); //read from client through inputstream  
             
          //      dataOutputStream = new DataOutputStream(socket.getOutputStream());//write to stream through outputstream  
                
          //      System.out.println("ip: " + socket.getInetAddress());  //ip of client 
                
                numbStr = fromClient.readLine();
                
                System.out.println("message"+numbStr);
             //   System.out.println("message: " + dataInputStream.readUTF()); // read msg from client device  
                String str=null;  
          //      do{  
              //      System.out.println("##################send message to #########");  
                  
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                            .getOutputStream())), true);    //write to client
                    java.util.Scanner sc = new java.util.Scanner(System.in);  
                    
                    out.println(sc.nextLine());
  
                 //   dataOutputStream.writeUTF(sc.nextLine());//write msg to stream client   
         //           System.out.println("################## message sent to device #########");  
       //             System.out.println("################## again want to send msg?? press y or n #########");  
        //            java.util.Scanner sc1 = new java.util.Scanner(System.in);  
       //              str=sc1.nextLine();  
                      
          //      }while(str.equalsIgnoreCase("y"));  
                         
        	}
        	
        	catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        	
            finally{  
                if( socket!= null){  
                    try {  
                        //close socket connection after using it otherwise next time when u reconnect on  the same port  
                        // it will throw error if you dont close it   
                        socket.close();  
                     //   System.out.println("we have exited");
                    } catch (IOException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                }  
  
                if( dataInputStream!= null){  
                    try {  
                        //close inputsteam  
                        dataInputStream.close();  
                        
                    } catch (IOException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                }  
  
                if( dataOutputStream!= null){  
                    try {  
                        //close outputstream  
                        dataOutputStream.close();  
                    } catch (IOException e) {  
                        // TODO Auto-generated catch block  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
    }  
  

}
