import com.hrban.Car;
import com.hrban.MyInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanLifeCycle {
    private static void LifeCycleInBeanFactory() {
        Resource res = new ClassPathResource("hrban-context.xml");

        BeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory)bf);

        reader.loadBeanDefinitions(res);

        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyBeanPostProcessor());
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        Car car1 = (Car)bf.getBean("car");
        car1.introduce();
        car1.setColor("红色");

        Car car2 = (Car)bf.getBean("car");
        System.out.println("car2 == car1 : " + (car2 == car1));

        ((DefaultListableBeanFactory)bf).destroySingletons();
    }

    public static void main(String[] args) {
//        LifeCycleInBeanFactory();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("hrban-context.xml");
        context.start();
        Car car = (Car)context.getBean("car");
        car.introduce();
    }
}
