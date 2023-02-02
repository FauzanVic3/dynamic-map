/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.com.fauzanvic3.dynamic.map;

import com.sun.glass.ui.SystemClipboard;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import my.com.fauzanvic3.dynamic.map.entity.DynamicTypeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Fauzan
 */
@SpringBootApplication
public class Main implements CommandLineRunner{
    @Autowired
    private AppConfiguration config;
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    
    public static void main(String[] args)throws Exception{
        SpringApplication.run(Main.class,args);
    }
    
    @Override
    public void run(String... args) throws Exception{
        log.info("Service starting...");
        log.info("This program is to show the usage of dynamic single declaration Map");
        Map<String, DynamicTypeValue> dynamicMapper = new HashMap<>();
        boolean proceed = true;
        String response = "";
        
        do{
            Scanner s = new Scanner(System.in);
            
            log.info("Insert text to save into a dynamic map");
            log.info("text:");
            String input = s.nextLine();
            
            log.info("int:");
            int number = s.nextInt();

            Date date = new Date();        
            log.info("Date:"+date.toString());

            dynamicMapper.put("int", new DynamicTypeValue(number));
            dynamicMapper.put("date", new DynamicTypeValue(date));
            dynamicMapper.put("input", new DynamicTypeValue(input));    
            
            validateInputType(dynamicMapper);


            log.info("Success insert to map");
            log.info("Extracting map...");

            String textResult = dynamicMapper.get("inputo").getValue(String.class);
            int intResult = dynamicMapper.get("int").getValue(Integer.class);
            Date dateResult = dynamicMapper.get("date").getValue(Date.class);

            log.info("text: "+textResult);
            log.info("int: "+intResult);
            log.info("date: "+dateResult);


            log.info("Continue using the app? [Y] to continue | [N] to stop");

            s.nextLine();
            response = s.nextLine();
            s.nextLine();
            
            if(response.equalsIgnoreCase("Y")) {
                proceed = true;
            }else if(response.equalsIgnoreCase("N")){
                proceed = false;
            }else{
                response = "";
                proceed = false;
            }
            
        }while(proceed);
        
        log.info("Service stopping...");
    }
    
    private void validateInputType(Map<String, DynamicTypeValue> dynamicMapper){
                        
        for(String key : dynamicMapper.keySet()){
        log.info("Validate input Type: "+key);
        log.info("====================");            
            try {
                String output = dynamicMapper.get(key).getValue(String.class);
                log.info(key+" is String : "+output);
            } catch (Exception e) {
                log.info(key+" is not String");
            }

            try {
                int output = dynamicMapper.get(key).getValue(Integer.class);
                log.info(key+" is Integer : "+output);
            } catch (Exception e) {
                log.info(key+" is not Integer");
            }

            try {
                Date output = dynamicMapper.get(key).getValue(Date.class);
                log.info(key+" is Date : "+output);
            } catch (Exception e) {
                log.info(key+" is not Date");
            }

            try {
                double output = dynamicMapper.get(key).getValue(Double.class);
                log.info(key+" is Double : "+output);
            } catch (Exception e) {
                log.info(key+" is not Double");
            }

            try {
                char output = dynamicMapper.get(key).getValue(Character.class);
                log.info(key+" is Character : "+output);
            } catch (Exception e) {
                log.info(key+" is not Character");
            }
            
        }
        
        log.info("====================");        
    }
}
