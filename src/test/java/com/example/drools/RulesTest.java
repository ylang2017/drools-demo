package com.example.drools;

import com.example.drools.entity.Person;
import com.example.drools.entity.School;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;

public class RulesTest {
    KieServices kss = KieServices.Factory.get();
    KieContainer kc = kss.getKieClasspathContainer();

    @Test
    public void testRules(){
        KieSession ks = kc.newKieSession("testhelloworld");
        Person person = new Person();
        person.setName("小明");
        person.setAge(20);
        ks.insert(person);

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }

    /**
     * contains关系符可以比较数组、集合和字符串类型
     */
    @Test
    public void testContains(){
        KieSession ks = kc.newKieSession("testContains");

        Person person = new Person();
        person.setName("小明");
        person.setAge(20);
        person.setClassName("一班");

        List<String> classNames = new ArrayList<>();
        classNames.add("一班");
        classNames.add("二班");
        School school = new School();
        school.setClassNames(classNames);
        school.setClassCount(classNames.size());

        ks.insert(person);
        ks.insert(school);

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        System.out.println("测试是否修改了对象："+person.getClassName());
        ks.dispose();
    }

    /**
     * memberOf关系符可以比较数组、list、set集合、map和字符串类型
     * （其实和contains类似，只是操作对象调换位置了）
     * contains和memberOf在对map对象进行比较时，使用的是map的key
     */
    @Test
    public void testMemberOf(){
        KieSession ks = kc.newKieSession("testContains");

        Person person = new Person();
        person.setName("小明");
        person.setAge(20);
        person.setClassName("一班");

        List<String> classNames = new ArrayList<>();
        classNames.add("一班");
        classNames.add("二班");
        School school = new School();
        school.setClassNames(classNames);
        school.setClassCount(classNames.size());

        ks.insert(person);
        ks.insert(school);

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }

    @Test
    public void testMatches(){
        KieSession ks = kc.newKieSession("testMatches");

        Person person = new Person();
        person.setName("小明");

        ks.insert(person);

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }

    @Test
    public void testCollection(){
        KieSession ks = kc.newKieSession("testCollection");

        List<String> list = new ArrayList<>();
        list.add("hello list");
        Set<String> set = new HashSet<>();
        set.add("hello set");
        Map<String,String> map = new HashMap<>();
        map.put("hello","hello map");

        ks.insert(list);
        ks.insert(set);
        ks.insert(map);

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }

    @Test
    public void testAttribute(){
        KieSession ks = kc.newKieSession("testAttribute");

        Person person = new Person();
        person.setAge(10);

        ks.insert(person);

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }

    @Test
    public void testDateAttribute(){
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm:ss");

        KieSession ks = kc.newKieSession("testAttribute");

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }

    @Test
    public void testDeclare(){
        KieSession ks = kc.newKieSession("testDeclare");
        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }
}
