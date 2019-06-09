package com.ginny.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    @Bean("firstQueue")
    public Queue getFirstQueue(){ return new Queue("FIRST_QUEUE");}

    @Bean("secondQueue")
    public Queue getSecondQueue(){ return new Queue("SECOND_QUEUE");}

    @Bean("thirdQueue")
    public Queue getThirdQueue(){ return new Queue("THIRD_QUEUE");}

    @Bean("fourthQueue")
    public Queue getFourthQueue(){ return new Queue("FOURTH_QUEUE");}


    @Bean("topicExchange")
    public TopicExchange getTopicExchange(){return new TopicExchange("TOPIC_EXCHANGE");}

    @Bean("directExchange")
    public DirectExchange getSecondDirectExchange(){return new DirectExchange("DIRECT_EXCHANGE");}

    @Bean("fountExchange")
    public FanoutExchange getHarryFountExchange(){return new FanoutExchange("FOUNT_EXCHANGE");}

    @Bean
    public Binding bingFirst(@Qualifier("topicExchange") TopicExchange exchange,
                              @Qualifier("firstQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("*.ginny.*");
    }

    @Bean
    public Binding bingSecond(@Qualifier("directExchange") DirectExchange exchange,
                             @Qualifier("secondQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("ginny.test");
    }

    @Bean
    public Binding bingThird(@Qualifier("fountExchange") FanoutExchange exchange,
                             @Qualifier("thirdQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bingFourth(@Qualifier("fountExchange") FanoutExchange exchange,
                             @Qualifier("fourthQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange);
    }

    /**
     * 创建监听
     * @param connectionFactory  如果不创建监听，此处默认RabbitMQ的localhost
     * @return
     */
    @Bean("rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setAutoStartup(true);
        return factory;
    }
}
