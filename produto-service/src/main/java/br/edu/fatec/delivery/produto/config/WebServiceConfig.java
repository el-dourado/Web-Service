package br.edu.fatec.delivery.produto.config;

import br.edu.fatec.delivery.produto.exception.GlobalExceptionHandler;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

/**
 * Configuração do Web Service SOAP.
 * Registra o MessageDispatcherServlet e expõe o WSDL dinamicamente a partir do XSD.
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * Registra o MessageDispatcherServlet para processar requisições SOAP.
     * Mapeado para /ws/*
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * Define o WSDL a partir do XSD.
     * Acessível via: http://localhost:8080/ws/produtos.wsdl
     */
    @Bean(name = "produtos")
    public DefaultWsdl11Definition produtosWsdl(XsdSchema produtosSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("ProdutosPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://fatec.edu.br/delivery/produto");
        wsdl.setSchema(produtosSchema);
        return wsdl;
    }

    /**
     * Bean que carrega o XSD do classpath.
     */
    @Bean
    public XsdSchema produtosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("produtos.xsd"));
    }

    /**
     * Bean para tratamento global de exceções SOAP.
     */
    @Bean
    public GlobalExceptionHandler exceptionResolver() {
        GlobalExceptionHandler resolver = new GlobalExceptionHandler();
        resolver.setOrder(1);
        return resolver;
    }

    /**
     * Validação de payload contra o XSD.
     */
    @Bean
    public PayloadValidatingInterceptor payloadValidatingInterceptor() {
        PayloadValidatingInterceptor interceptor = new PayloadValidatingInterceptor();
        interceptor.setXsdSchema(produtosSchema());
        interceptor.setValidateRequest(true);
        interceptor.setValidateResponse(true);
        return interceptor;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(payloadValidatingInterceptor());
    }
}