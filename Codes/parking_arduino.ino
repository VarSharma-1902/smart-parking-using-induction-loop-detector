/*
In this Project We are going to read the capacitor charge to determine a change in the inductance of the LCR circuit.
 */

// Defining pin
#define capacitor A5
#define pulsePin A4
#define led 13
#define battery A2


void setup() 
{
  //Setting PinMode
  pinMode(pulsePin, OUTPUT); 
  digitalWrite(pulsePin, LOW);
  pinMode(capacitor, INPUT);  
  pinMode(battery, INPUT);
  Serial.begin(9600);
  pinMode(led, OUTPUT);
}

//Global variable
  int pre_bat = 0;
  int bat_count = 0;
  float pre_avg1 = 0;
  float pre_avg2 = 0;

void loop() {
  //Taking Multiple readings and averaging it, to reduce spikes
  
  long sum = 0;
  for(int i=0;i<150;i++)
    {
      //reset the capacitor
      pinMode(capacitor,OUTPUT);
      digitalWrite(capacitor,LOW);
      delayMicroseconds(20);
      pinMode(capacitor,INPUT);
      applyPulses();
      //read the charge of capacitor
      int val = analogRead(capacitor);
      sum = sum + val;
    }
  float avg = 0;
  pre_avg2 = pre_avg1;
    pre_avg1 = avg;

  //For removing spikes, we are taking avarage  
   avg = (sum * 4.883) / 150;
 // if(avg >= 0.03){
    // Serial.print("avg = ");
    // Serial.println(avg);
 // }
  
  //Condition For metal detection
  //This condition indicates metal 
  if(avg >= thr)  //thr needs to be inserted based on the threshold kept for distinction of a vehicle
  {
    digitalWrite(led, HIGH);
    Serial.println(avg);
    Serial.print("\n");
  }

  //This condition indicates Diamagnetic material
  else if(avg < 0.07)
  {
    digitalWrite(led, LOW);
  }

  //This will turn of all notification if it's on
  else
  {
    digitalWrite(led, LOW);
  }
  
  
  //For getting output through usb port 
  //This is useful if we want to develope multipurpose metal detector 
  //Which can be attached to drone, robot, etc.
    // Serial.print("Coil:");
    // Serial.print(avg);
    // Serial.print(" mV\n");
}

//This is single coil metal detector therefore we are appling pulse to this coil
//After that reading the value from the same coil
void applyPulses()
{
    for (int i=0;i<3;i++) 
    {
      digitalWrite(pulsePin,HIGH); //take 3.5 uS
      delayMicroseconds(3);
      digitalWrite(pulsePin,LOW);  //take 3.5 uS
      delayMicroseconds(3);
    }
}
