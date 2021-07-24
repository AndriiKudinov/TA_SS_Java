package HW_TA_JAVA_2;

@FunctionalInterface
public interface ChangeableSpeed<T> {
    void apply(T t);
}