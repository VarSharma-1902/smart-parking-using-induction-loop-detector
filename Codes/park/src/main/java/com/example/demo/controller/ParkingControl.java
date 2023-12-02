package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Parking;
import com.example.demo.services.ParkService;

import com.fazecast.jSerialComm.SerialPort;

import java.util.*;

@Controller
@RequestMapping("/parking")
public class ParkingControl {
	@Autowired
	ParkService pservice;
	
//	private static SerialPort comPorts[];
//    private static int port=-1;
    public static SerialPort[] comPort;
    public static StringBuilder text=new StringBuilder(); 
	
	@GetMapping("/list")
	public String list(Model model) {
		//System.out.println("hello");
		List<Parking> parkList = pservice.getAllSlots();
		model.addAttribute("parkList",parkList);
		System.out.println(parkList.toString());
		return "whole";
	}
	
	@GetMapping("/mySlot")
	public String slot(Model model) {	
		
		String slotno = pservice.getAvailableSlot();
		System.out.println(slotno);
		if(slotno.equals("-1"))
		{
			model.addAttribute("value","No parking slot available!");
			return "main";
		}
		model.addAttribute("value", slotno);
		pservice.updateAvailability(slotno);
		return "main";
	}
	
	@GetMapping("/arduino")
	public String output(Model model) { 
		Scanner console = new Scanner(System.in);
        SerialPort sp = SerialPort.getCommPort("COM5");
        String[] x_outputs = {"0.00", "0.00", "0.00", "0.00", "0.00"};
       if(sp.openPort())          
            {System.out.println("COM5 open.");}
       try {
    	   int i =0;
         while (true)
//    	   for(i=0; i<5; i++)
         {
           // if keyboard token entered read it
//        	 i++;
    		   System.out.println("loop : "+i);
    		   //System.out.println(System.in.available());
           if(System.in.available() > 0)
           {
        	   
             System.out.println("sys.in ");
             String s = console.nextLine() + "\n";                       // read text
             byte[] writeBuffer = s.getBytes() ;
             sp.writeBytes(writeBuffer, writeBuffer.length); // transmit it
             //System.out.println("write " + writeBuffer.length);
           }
           // read serial port  and display data
           if(sp.bytesAvailable() > 0) 
           {
        	 System.out.println("I have come inside second if");
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
                  x_outputs[i] = x;
                  String str = "P"+(i+1); 
                  model.addAttribute("change", i+1);
                  pservice.updateAvailability(str);
                  text.setLength(0);
             }
          }
//           	if(i==5)
//           		break;
           	i++;
           	if(i==15)
           		break;
         }
         model.addAttribute("ardop", x_outputs);
//         sp.closePort();
       } catch (Exception e) { e.printStackTrace(); }
//       sp.closePort();         
		return "display";
   }
	
}

//model.addAttribute("change", i);
////Parking curr_slot = pservice.findBySlotno(str);
//pservice.updateAvailability(strSlot);
