package com.afonso.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.util.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {

    @Value("{amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("{aws_access_key_id}")
    private String amazonAWSAccessKey;

    @Value("{aws_secret_access_key_id}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(awsCredentials());
        if(!StringUtils.isNullOrEmpty(amazonDynamoDBEndpoint)) {
            amazonDynamoDB.setEndpoint("http://localhost:8000");
        }
        return  amazonDynamoDB;
    }

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey,amazonAWSAccessKey);
    }
}
