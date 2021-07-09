package kr.co.ezenac.main;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import kr.co.ezenac.beans.TestBean;

public class MainClass {
	
	public static void main(String[] args) {
		//test1();
		//test2();
		//test3();
		test4();
	}
	
	// IoC(Inversion of Control) : 제어의 역전 ==> 스프링 프레임워크는 IoC컨테이너를 이용해 Bean 객체들을 관리한다.
	
	// BeanFactory - beans.xml이 패키지 내부
	public static void test1() {
		// 패키지 안에 있는 beans.xml을 사용할 때는 ClassPathResource를 사용한다.
		ClassPathResource res=new ClassPathResource("kr/co/ezenac/config/beans.xml");
		XmlBeanFactory factory=new XmlBeanFactory(res);
		
		TestBean t1=factory.getBean("t1",TestBean.class);
		System.out.printf("t1 : %s\n",t1);
		
		// 이미 만들어진 객체는 생성되지 않는다.
		TestBean t2=factory.getBean("t1",TestBean.class);
		System.out.printf("t2 : %s\n",t2);
	}

	// Factory - beans.xml이 패키지 외부에 있을 때
	public static void test2() {
		// 패키지 외부를 사용 할 때는 FilesystemResource를 사용한다.
		FileSystemResource res=new FileSystemResource("beans.xml");
		XmlBeanFactory factory=new XmlBeanFactory(res); // 객체 생성 안된다.

		TestBean t1=factory.getBean("t2",TestBean.class);
		System.out.printf("t1 : %s\n",t1);
		
		// 이미 만들어진 객체는 생성되지 않는다.
		TestBean t2=factory.getBean("t2",TestBean.class);
		System.out.printf("t2 : %s\n",t2);
	}
	
	// ApplicationContext - beans.xml이 패키지 내부에 있을 때
	public static void test3() {
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("kr/co/ezenac/config/beans.xml");
		
		TestBean t1=ctx.getBean("t1",TestBean.class);
		System.out.printf("t1 : %s\n",t1);

		TestBean t2=ctx.getBean("t1",TestBean.class);
		System.out.printf("t2 : %s\n",t2);
		
		ctx.close();
	}
	
	// ApplicationContext - beans.xml이 패키지 외부에 있을 때
	public static void test4() {
		FileSystemXmlApplicationContext ctx=new FileSystemXmlApplicationContext("beans.xml");
	
		TestBean t1=ctx.getBean("t2",TestBean.class);
		System.out.printf("t1 : %s\n",t1);

		TestBean t2=ctx.getBean("t2",TestBean.class);
		System.out.printf("t2 : %s\n",t2);
		
		ctx.close();
	}
}












