package com.example.busticketsystem.BusTicketPaymentSystemTestProject.configuration;

import com.example.busticketsystem.BusTicketPaymentSystemTestProject.providers.PaymentStatusProvider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public PaymentStatusProvider paymentStatusService() {
        return new PaymentStatusProvider();
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder().build();
    }

//    @Bean
//    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder mapperBuilder) {
//        DefaultSerializerProvider sp = new DefaultSerializerProvider.Impl();
//
//        sp.setNullValueSerializer(new JsonSerializer<Object>() {
//            public void serialize(Object value, JsonGenerator jgen,
//                                  SerializerProvider provider)
//                    throws IOException, JsonProcessingException
//            {
//                jgen.writeString("");
//            }
//        });
//        ObjectMapper mapper = mapperBuilder.build();
//        mapper.setSerializerProvider(sp);
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
//        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
//        return mapper;
//    }


//    @Bean
//    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
//        return new Jackson2ObjectMapperBuilder()
//                .serializationInclusion(JsonInclude.Include.NON_NULL)
//                .build();
//        //.configOverride(List.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //objectMapper.configOverride(List.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        return objectMapper;
    }
}
