package HW_TA_JAVA_2;

import java.io.Serializable;

public class Car implements Vehicle, Alarm, Serializable {
    static final long serialVersionUID = 1L;
    private boolean alarmState = false;
    private String brand;
    private String model;
    private int speed = 0;
    private int maxSpeed = 180;
    private final int minSpeed = 0;

    Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String alarm() {
        String result;
        if(!alarmState) {
            result = turnAlarmOn();
        } else {
            result = turnAlarmOff();
        }
        alarmState = !alarmState;
        return result;
    }

    @Override
    public void speedUp(int speed) {
        if( this.speed + speed <= maxSpeed ) {
            this.speed += speed;
        } else {
            this.speed = maxSpeed;
        }
    }

    @Override
    public void slowDown(int speed) {
        if( this.speed - speed >= minSpeed ) {
            this.speed -= speed;
        } else {
            this.speed = minSpeed;
        }
    }
}
