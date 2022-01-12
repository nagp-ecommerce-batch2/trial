package com.ecommerce.order.utils;

import java.net.URI;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@EnableDynamoDBRepositories
public class DynamoDBConfig {

        
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;
    

    @Bean
    DynamoDbClient amazonDynamoDBClient() {
        return getDynamoDbClient();
    }

    @Bean
    DynamoDbEnhancedClient amazonDynamoDBEnhancedClient() {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(getDynamoDbClient()).build();
    }

    private DynamoDbClient getDynamoDbClient() {
        ClientOverrideConfiguration.Builder overrideConfig =
          ClientOverrideConfiguration.builder();
      
        return DynamoDbClient.builder()
          .overrideConfiguration(overrideConfig.build())
          .endpointOverride(URI.create("https://dynamodb.ap-south-1.amazonaws.com"))
          .region(Region.AP_SOUTH_1)
          .credentialsProvider(StaticCredentialsProvider.create(amazonAWSCredentials()))
          .build();
      }

    @Bean
    public AwsBasicCredentials amazonAWSCredentials() {
      AwsBasicCredentials awsCreds = AwsBasicCredentials.create(amazonAWSAccessKey, amazonAWSSecretKey);
        return awsCreds;
    }

}
