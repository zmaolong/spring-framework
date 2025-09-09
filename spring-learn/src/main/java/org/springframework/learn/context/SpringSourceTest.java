package org.springframework.learn.context;


import org.springframework.context.support.*;

// 测试用的服务类
class UserService {
    private UserDao userDao;

    // 依赖注入 setter 方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser() {
        userDao.insert();
    }
}

// 测试用的 DAO 类
class UserDao {
    public void insert() {
        System.out.println("执行用户插入操作");
    }
}

public class SpringSourceTest {
    public static void main(String[] args) {
        // 1. 初始化 Spring 容器（从 XML 配置文件加载）
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-test.xml");

        // 2. 从容器中获取 Bean（跟踪 getBean 方法，了解 Bean 的创建过程）
        UserService userService = context.getBean("userService", UserService.class);

        // 3. 调用 Bean 的方法（验证依赖注入效果）
        userService.saveUser();

        // 4. 演示 Bean 的生命周期（需要在 XML 中配置 init-method 和 destroy-method）
        System.out.println("容器初始化完成");

		// 关闭容器，触发销毁方法
        context.close();

    }
}
