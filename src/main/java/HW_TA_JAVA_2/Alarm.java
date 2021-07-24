package HW_TA_JAVA_2;

public interface Alarm {
    default String turnAlarmOn() {
        return "Alarm is on.";
    }
    default String turnAlarmOff() {
        return "Alarm is off.";
    };
}
