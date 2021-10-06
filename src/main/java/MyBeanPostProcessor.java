import com.hrban.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if(s.equals("car")) {
            Car car = (Car)o;
            if (car.getColor() == null) {
                System.out.println("invoke BeanPostProcessor.postProcessBeforeInitialization, " +
                        "color 为空，设置为默认黑色");
                car.setColor("黑色");
            }
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if(s.equals("car")) {
            Car car = (Car)o;
            if (car.getMaxSpeed() >= 200) {
                System.out.println("invoke BeanPostProcessor.postProcessAfterInitialization, " +
                        "将maxSpeed调整为200");
                car.setMaxSpeed(200);
            }
        }
        return o;
    }
}
