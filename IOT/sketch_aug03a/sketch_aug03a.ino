#include <Adafruit_ESP8266.h>
#include <SoftwareSerial.h>
#include <Servo.h>
#include <Wire.h>

Servo myservo;

#define ir_enter 5
#define ir_back  4

#define ir_car1 12
#define ir_car2 6
#define ESP_RX   2
#define ESP_TX   1
#define ESP_RST  0
#define FIREBASE_HOST "https://vetappointmentbooker-default-rtdb.firebaseio.com/"
#define FIREBASE_AUTH "AIzaSyBEG7ULATYhY93C96psSS19HB3N56gM4As"
SoftwareSerial softser(1,0);
#define ESP_SSID "Son of God" // Your network name here
#define ESP_PASS "" 
int S1 = 0, S2 = 0;
int flag1 = 0, flag2 = 0;
int slot = 2;

void setup() {
  Serial.begin(115200);
  softser.begin(115200);
  delay(2000);
//connectwifi();
  pinMode(ir_car1, INPUT);
  pinMode(ir_car2, INPUT);

  pinMode(ir_enter, INPUT);
  pinMode(ir_back, INPUT);
//digitalWrite(ir_enter,HIGH);
  myservo.attach(9);
  myservo.write(140);
  delay (5000);
  Serial.print("   Today's Project  ");

  Read_Sensor();

  int total = S1 + S2;
  slot = slot - total;
}

void loop() {

  Read_Sensor();

 
  if (S1 == 1) {
    //Serial.print("S1:Full ");
    softser.println(1);
    Serial.print(1);
  }
  else {
    Serial.print("S1:Empty");
  }

  if (S2 == 1) {
    //Serial.print("S2:Full ");
     softser.println(2);
     Serial.print(2);
  }
  else {
    Serial.print("S2:Empty");
  }




  if (digitalRead (ir_enter) == 0 && flag1 == 0) {
    if (slot > 0) {
      flag1 = 1;
      if (flag2 == 0) {
        myservo.write(40);
        slot = slot - 1;
      }
    } else {
      Serial.print(" Sorry Parking Full ");

    }
  }

  if (digitalRead (ir_back) == 0 && flag2 == 0) {
    flag2 = 1;
    if (flag1 == 0) {
      myservo.write(140);
      slot = slot + 1;
    }
  }

  if (flag1 == 1 && flag2 == 1) {
    delay (1000);
    myservo.write(80);
    flag1 = 0, flag2 = 0;
  }
}

void Read_Sensor() {
  S1 = 0, S2 = 0;

  if (digitalRead(ir_car1) == 0) {
    S1 = 1;
  }
  if (digitalRead(ir_car2) == 0) {
    S2 = 1;
  }

}
