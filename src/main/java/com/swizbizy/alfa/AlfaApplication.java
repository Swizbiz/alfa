package com.swizbizy.alfa;

import com.swizbizy.alfa.util.XmlParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AlfaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AlfaApplication.class, args);
        XmlParser xmlParser = context.getBean(XmlParser.class);
        if (args.length == 1) {
            xmlParser.parseXml(args[0]);
        } else
            throw new IllegalArgumentException("Задайте путь к файлу XML");
    }
}
