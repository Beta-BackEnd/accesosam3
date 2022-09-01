package mx.com.rc.accesosam3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration //se carga primero que todo
@EnableSwagger2 //para habilitar la libreria
public class SwaggerConfig {

    @Bean //este es de java, sirve para crear un componente
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("mx.com.rc.accesosam3.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo(
                "Api Usuario SAM 3",
                "Api para la gestion de usuarios y roles en el SAM 3",
                "1.0",
                "",
                new Contact("Desarrollos Mostradores","", "programadorcontablea@corprama.com.mx"),
                "@Cordina",
                "Cordina S.A. de C.V.",
                Collections.emptyList()
        );
    }
}
