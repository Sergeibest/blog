package com.gubko.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	/*если возникает так штука значит ты где то уже заюзал это порт - Отруби !!
	web server failed to start Port 8080 was already in use*/
}
