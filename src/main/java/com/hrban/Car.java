package com.hrban;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

@Data
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String brand;

    private String color;

    private int maxSpeed;

    private BeanFactory beanFactory;
    private String beanName;

    public Car(){
        System.out.println(" 调用Car()构造函数 ");
    }

    public void setBrand(String brand) {
        System.out.println(" 调用setBrand()设置属性");
        this.brand = brand;
    }

    public void setColor(String color) {
        System.out.println(" 调用setColor() 设置属性");
        this.color = color;
    }

    public void setMaxSpeed(int maxSpeed) {
        System.out.println("调用setMaxSpeed() 设置属性");
        this.maxSpeed = maxSpeed;
    }

    public void introduce() {
        System.out.println("brand:" + brand + "; color:" + color + "; maxSpeed:" + maxSpeed);
    }

    public void myInit() {
        System.out.println("invoke init-method 所指定的myInit(),将maxSpeed设置为240");
        this.maxSpeed = 240;
    }

    public void myDestroy() {
        System.out.println("调用destroy-method 所制定的myDestroy");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("invoke BeanFactoryAware.setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("invoke BeanNameAware.setBeanName");
        this.beanName = beanName;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("invoke disposableBean.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("invoke initializingBean.afterPropertiesSet");
    }
}
