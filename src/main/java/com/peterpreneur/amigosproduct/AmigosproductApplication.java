package com.peterpreneur.amigosproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AmigosproductApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(AmigosproductApplication.class, args);

		// String[] beanDefinitionNames = context.getBeanDefinitionNames();
		// for (String beanDefinitionName: beanDefinitionNames) {
		// 	System.out.println(beanDefinitionName);
		// }
		System.out.println("Bean Count: " + context.getBeanDefinitionCount());
	}

}
