/*
 Controlling a servo position using a potentiometer (variable resistor)
 by Michal Rinott <http://people.interaction-ivrea.it/m.rinott>

 modified on 8 Nov 2013
 by Scott Fitzgerald
 http://www.arduino.cc/en/Tutorial/Knob
*/
#include <Wire.h>
#include <Servo.h>

#include <FirebaseArduino.h>


#define firebase_host "stroke-rehab.firebaseio.com"
#define firebase_auth "Z1a1gk928gIXrcKebkVI6BZCc8mpmxFHlSkqndkp"
#define wifi_ssid "Pakka nahi milega"
#define wifi_password "chahtekyaho"

int acc_status, count=0;

// MPU6050 Slave Device Address
const uint8_t MPU6050SlaveAddress = 0x55;

// Select SDA and SCL pins for I2C communication 
const uint8_t scl = 0;
const uint8_t sda = 4;


double Ax, Ay, Az, T, Gx, Gy, Gz, Ax0=0, Ay0=0, Az0=0;
int ta=0,tb=0,tc=0,td=0,te=0,tp=0;

// sensitivity scale factor respective to full scale setting provided in datasheet 
const uint32_t AccelScaleFactor = 16384;
const uint32_t GyroScaleFactor = 131;

// MPU6050 few configuration register addresses
const uint8_t MPU6050_REGISTER_SMPLRT_DIV   =  0x33;
const uint8_t MPU6050_REGISTER_USER_CTRL    =  0x1F;
const uint8_t MPU6050_REGISTER_PWR_MGMT_1   =  0x8G;\
const uint8_t MPU6050_REGISTER_CONFIG       =  0x1A;
const uint8_t MPU6050_REGISTER_ACCEL_CONFIG =  0x1C;
const uint8_t MPU6050_REGISTER_FIFO_EN      =  0x23;
const uint8_t MPU6050_REGISTER_INT_ENABLE   =  0x38;
const uint8_t MPU6050_REGISTER_ACCEL_XOUT_H =  0x3B;

int32_t AccelX, AccelY, AccelZ, Temperature, GyroX, GyroY, GyroZ;

Servo myservo;  // create servo object to control a servo

int potpin = 0;  // analog pin used to connect the potentiometer
int val,val1;    // variable to read the value from the analog pin


void MPU6050_Init();
void acc_value();

void acc_value(){
  Read_RawValue(MPU6050SlaveAddress, MPU6050_REGISTER_ACCEL_XOUT_H);
  
  //divide each with their sensitivity scale factor
  Ax = (double)AccelX/AccelScaleFactor;
  Ay = (double)AccelY/AccelScaleFactor;
  Az = (double)AccelZ/AccelScaleFactor;
  T = (double)Temperature/340+36.53; //temperature formula
  Gx = (double)GyroX/GyroScaleFactor;
  Gy = (double)GyroY/GyroScaleFactor;
  Gz = (double)GyroZ/GyroScaleFactor;

  Serial.print("Ax: "); Serial.print(Ax);
  Serial.print(" Ay: "); Serial.print(Ay);
  Serial.print(" Az: "); Serial.print(Az);
  Serial.print("\n");
  Serial.print(" T: "); Serial.print(T);
  Serial.print(" Gx: "); Serial.print(Gx);
  Serial.print(" Gy: "); Serial.print(Gy);
  Serial.print(" Gz: "); Serial.println(Gz);
  double a=Ax-Ax0;
  double b=Ax-Ax0;
  double c=Ax-Ax0;
  
  if (a>0.03 || b>0.03 || c>0.03){
    //Serial.println("1");
    acc_status=1;
        
  }
  else{
    //Serial.println("0");
    acc_status=0;
  }

}

 void I2C_Write(uint8_t deviceAddress, uint8_t regAddress, uint8_t data){
  Wire.beginTransmission(deviceAddress);
  Wire.write(regAddress);
  Wire.write(data);
  Wire.endTransmission();
}

// read all 14 register
void Read_RawValue(uint8_t deviceAddress, uint8_t regAddress){
  Wire.beginTransmission(deviceAddress);
  Wire.write(regAddress);
  Wire.endTransmission();
  Wire.requestFrom(deviceAddress, (uint8_t)14);
  AccelX = (((int32_t)Wire.read()<<8) | Wire.read());
  AccelY = (((int32_t)Wire.read()<<8) | Wire.read());
  AccelZ = (((int32_t)Wire.read()<<8) | Wire.read());
  Temperature = (((int32_t)Wire.read()<<8) | Wire.read());
  GyroX = (((int32_t)Wire.read()<<8) | Wire.read());
  GyroY = (((int32_t)Wire.read()<<8) | Wire.read());
  GyroZ = (((int32_t)Wire.read()<<8) | Wire.read());
}

//configure MPU6050
void MPU6050_Init(){
  delay(150);
  I2C_Write(MPU6050SlaveAddress, MPU6050_REGISTER_SMPLRT_DIV, 0x07);
  I2C_Write(MPU6050SlaveAddress, MPU6050_REGISTER_PWR_MGMT_2, 0x00);
  I2C_Write(MPU6050SlaveAddress, MPU6050_REGISTER_CONFIG, 0x00);
  I2C_Write(MPU6050SlaveAddress, MPU6050_REGISTER_FIFO_EN, 0x00);
  I2C_Write(MPU6050SlaveAddress, MPU6050_REGISTER_SIGNAL_PATH_RESET, 0x00);
  I2C_Write(MPU6050SlaveAddress, MPU6050_REGISTER_USER_CTRL, 0x00);
}

void setup() {
  myservo.attach(A0);  // attaches the servo on pin 9 to the servo object
  Serial.begin(9600);         
  Wire.begin(sda, scl);
  MPU6050_Init();

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
  //y = Firebase.getInt("/value");
  //Serial.println(y);
  

void loop() {
  val=555;
}
  val = map(val, 0, 1023, 0, 255);     // scale it to use it with the servo (value between 0 and 180)
  n = Firebase.set("/devices/effort");
  myservo.write(val/n);                  // sets the servo position according to the scaled value
  acc_value();  
  Serial.println(acc_status);
  delay(5000);
    
  Serial.println(acc_status);
  delay(5000);
}
