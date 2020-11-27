package com.lxiaocode.boardgame.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author lixiaofeng
 * @date 2020/11/26 上午10:35
 * @blog http://www.lxiaocode.com/
 */
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        return RestClients.create(ClientConfiguration.localhost()).rest();
//        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
//                .connectedTo("127.0.0.1:9200")
//                .build();
//
//        return RestClients.create(clientConfiguration).rest();
    }


}
