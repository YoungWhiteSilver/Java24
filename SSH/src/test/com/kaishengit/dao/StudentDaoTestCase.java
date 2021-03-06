package com.kaishengit.dao;

import com.kaishengit.pojo.Student;
import com.kaishengit.service.StudentService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 67675
 * @date: 2017/11/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-hibernate.xml")
public class StudentDaoTestCase {


    @Autowired
    private StudentService studentService;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void getTest() {


    }



    @Test
    public void Test() {
        System.out.println(isContainChinese("China"));

    }
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


    @Test
    public void TestForName() throws ClassNotFoundException {

        System.out.println(Class.forName("com.kaishengit.pojo.StudentClass").toString() + "=======================");

    }

    @Test
    public void TestClass() {


    }

}


