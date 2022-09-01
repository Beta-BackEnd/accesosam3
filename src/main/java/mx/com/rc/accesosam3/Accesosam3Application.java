package mx.com.rc.accesosam3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //permite sobre cargar configuraciones buscando por el decorador @Configuration para que la cargue en la congiguracion basica
public class Accesosam3Application {

	public static void main(String[] args) {
		SpringApplication.run(Accesosam3Application.class, args);
	}

}
