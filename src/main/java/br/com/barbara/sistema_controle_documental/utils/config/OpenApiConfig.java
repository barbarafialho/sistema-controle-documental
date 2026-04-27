package br.com.barbara.sistema_controle_documental.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Controle e Gerenciamento de " +
                                "Documentação Ambiental para Produtores de Cana-de-açúcar")
                        .version("V1")
                        .description("API para auxiliar no gerenciamento e controle das documentações necessárias " +
                                "ao atendimento da legislação ambiental vigente no setor sucroenergético, " +
                                "possibilitando a integração com diferentes sistemas ou interfaces")
                );
    }
}
