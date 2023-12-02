package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import com.fazecast.jSerialComm.SerialPort;
import jssc.SerialPortList;

public class ArduinoOutput {
	public static void main(String args[]) throws IOException, InterruptedException
	{
		SerialPort sp = SerialPort.getCommPort("COM5");
		sp.setComPortParameters(9600, 8, 1, 0);
		sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
		
		if(sp.openPort())
		{
			System.out.println("port is open.");
		}
		else {
			System.out.println("port is not open.");
			return;
		}
		
		for(Integer i=0;i<=5;i++)
		{
			OutputStream op = sp.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			//baos.writeTo(op);
			
			byte[] array = baos.toByteArray();
			op.write(array);
			String streamData = op.toString();
//			PrintStream p = new PrintStream(op);
//			p.println();
//			op.write(i.byteValue());
			System.out.println(streamData);
//			System.out.println(sp.getOutputStream());
			sp.getOutputStream().flush();
			Thread.sleep(1000);
		}
		
		if(sp.closePort())
		{
			System.out.println("Port is closed.");
		}
		else {
			System.out.println("Port is not closed.");
		}
		
		
	}
}
