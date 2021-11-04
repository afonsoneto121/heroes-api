package com.afonso.heroesapi.config;

import com.afonso.heroesapi.constants.HeroesConstants;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class HeroesData {

    public static void main(String[] args) {
        AmazonDynamoDB dynamoDBClient = AmazonDynamoDBAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(HeroesConstants.ENDPOINT_DYNAMO, HeroesConstants.REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(dynamoDBClient);
        Table table = dynamoDB.getTable("Heroes_Table");
        Item hero = new Item()
                .withPrimaryKey("id","1")
                .withString("name", "Super Man")
                .withString("universe", "DC Comics")
                .withNumber("films", 3);

        PutItemOutcome outcome = table.putItem(hero);

    }
}
