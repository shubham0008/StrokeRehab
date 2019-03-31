int MUX_A=16;             //D0 PIN
int MUX_B=5;              //D1 PIN
int MUX_C=2;              //D4 PIN
int MUX_D=14;             //D5 PIN
int MUX_E=12;             //D6 PIN
int PALM=13;              //D7 PIN

int ANALOG_INPUT=17;      //A0 PIN
#include <ESP8266WiFi.h>



#define FIREBASE_HOST "stroke-rehab.firebaseio.com"
#define FIREBASE_AUTH "Z1a1gk928gIXrcKebkVI6BZCc8mpmxFHlSkqndkp"
#define WIFI_SSID "Pakka nahi milega"
#define WIFI_PASSWORD "chahtekyaho"


int va, vb, vc, vd, ve, vp ;
int acc_status, count=0;

void init_sensors();
void changeMux();

void changeMux(int a, int b, int c, int d, int e, int p) {
  digitalWrite(MUX_A, a);
  digitalWrite(MUX_B, b);
  digitalWrite(MUX_C, c);
  digitalWrite(MUX_D, d);
  digitalWrite(MUX_E, e);
  digitalWrite(PALM, p);
}

void init_sensors(){
  pinMode(MUX_A, OUTPUT);
  pinMode(MUX_B, OUTPUT);     
  pinMode(MUX_C, OUTPUT);
  pinMode(MUX_D, OUTPUT);
  pinMode(MUX_E, OUTPUT);
  pinMode(PALM, OUTPUT);
  pinMode(ANALOG_INPUT, INPUT); 
}

void sensorout(){

  
  changeMux(HIGH, LOW, LOW, LOW, LOW, LOW);
  va = analogRead(ANALOG_INPUT); //Value of the sensor connected D0 pin of Mux

  
  vb = analogRead(ANALOG_INPUT); //Value of the sensor connected Option D1 pin of Mux
  
  
  vc = analogRead(ANALOG_INPUT); //Value of the sensor connected Option D4 pin of Mux

  
  vd = analogRead(ANALOG_INPUT); //Value of the sensor connected Option D5 pin of Mux

  
  ve = analogRead(ANALOG_INPUT); //Value of the sensor connected Option D6 pin of Mux

  
  vp = analogRead(ANALOG_INPUT); //Value of the sensor connected Option D7 pin of Mux

}

void setup() {
  init_sensors();         //Deifne output pins for Mux
 
  
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
   delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  
  if (Firebase.failed()){
   Serial.println("Connection Failed!!");
    }
  

  //blinker.attach(10,firedata);

  pinMode(10,OUTPUT);
  digitalWrite(10,HIGH);
  delay(10000);
  digitalWrite(10,LOW);
  
}

void loop() {
  sensorout();
  
  Serial.print(va);
  Serial.print(" ");
  Serial.print(vb);
  Serial.print(" ");
  Serial.print(vc);
  Serial.print(" ");
  Serial.print(vd);
  Serial.print(" ");
  Serial.print(ve);
  Serial.print(" ");
  Serial.print(vp);
  Serial.print(" ");
  Serial.print("\n");
  Firebase.setInt("gloveid/f1",va);
  Firebase.setInt("gloveid/f2",vb);
  Firebase.setInt("gloveid/f3",vc);
  Firebase.setInt("gloveid/f4",vd);
  Firebase.setInt("gloveid/f5",ve);
  Firebase.setInt("gloveid/flexibilty",vp);
  Firebase.setInt("gloveid/motion",acc_status);        //acc_status
     
}
