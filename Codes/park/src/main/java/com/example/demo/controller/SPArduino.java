package com.example.demo.controller;

import com.fazecast.jSerialComm.SerialPort;

import java.util.*;
public class SPArduino {
    private static SerialPort comPorts[];
    private static int port=-1;
    public static SerialPort[] comPort;
    public static StringBuilder text=new StringBuilder();  // will hold a line of text to be parse for an float

    public static void main(String[] args) { 
        Scanner console = new Scanner(System.in);
        SerialPort sp = SerialPort.getCommPort("COM5");
       if(sp.openPort())          
            {System.out.println("COM5 open.");}
      
       // loop read characters from keyboard transmit over serial
       //   read characters from serial and display them
       try {
         while (true)
         {
           // if keyboard token entered read it
           if(System.in.available() > 0)
           {
             //System.out.println("enter chars ");
             String s = console.nextLine() + "\n";                       // read text
             byte[] writeBuffer = s.getBytes() ;
             comPorts[port].writeBytes(writeBuffer, writeBuffer.length); // transmit it
             //System.out.println("write " + writeBuffer.length);
           }
           // read serial port  and display data
           if(sp.bytesAvailable() > 0) 
           {
             byte[] data = new byte[10];
             sp.readBytes(data,1);
             //System.out.print((char)data[0]);
             if((char)data[0] >= ' ') 
               text.append((char)data[0]);  // if printable append to text
             else 
               // ****  if end of line parse text for a float value  ****
               if((char)data[0]== '\n'){
                  //System.out.println("new text array " + text);
                  String x = (text.toString());
                  System.out.println("*** float value parsed " + x);
                  text.setLength(0);
             }
          }


         }
       } catch (Exception e) { e.printStackTrace(); }
       sp.closePort();         
   }
  
    // attempt to open COM port 
    
}
