package com.example.App;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.App.domain.Ingredient;

@SpringBootApplication
public class PancakeCloudApplication implements WebMvcConfigurer {
    private static Logger log =  LoggerFactory.getLogger(PancakeCloudApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PancakeCloudApplication.class, args);
 	}	 
	 @Bean
	 public CommandLineRunner run()  throws Exception { 	 
		   return args ->{ 
			Ingredient ing = new Ingredient("fruit chocolate" , Ingredient.Type.CHOCOLATE); 
			ConsumingAPI cApi = new ConsumingAPI(); 
			Ingredient get =   cApi.getIngredientById("1"); 
			Ingredient post = cApi.postIngredient(ing); 
			log.info(get.toString());
			log.info(post.toString()); 

		   };
		 }
 }
	 

