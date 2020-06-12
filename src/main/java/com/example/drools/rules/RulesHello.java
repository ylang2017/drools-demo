package com.example.drools.rules;

import com.example.drools.entity.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RulesHello {
    public static void main(String[] args) {
        KieServices kss = KieServices.Factory.get();
        KieContainer kc = kss.getKieClasspathContainer();
        KieSession ks = kc.newKieSession("testhelloworld");

        Person person = new Person();
        person.setName("小明");
        person.setAge(20);
        ks.insert(person);

        int count = ks.fireAllRules();
        System.out.println("共执行了："+count+"条规则。");
        ks.dispose();
    }
}
