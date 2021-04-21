package com.se.back.common.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * es的配置
 * @author xin
 * 参考 https://segmentfault.com/a/1190000020179390
 */
@Configuration
public class ElasticSearchRestHighLevelClientConfig {
    private static final int CONNECT_TIMEOUT_MILLIS = 2 * 60 * 1000;
    private static final int CONNECTION_REQUEST_TIMEOUT_MILLIS = 2 * 60 * 1000;
    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";

    @Value("${elasticsearch.ip.single}")
    String[] singleNodes;

    @Bean(value = "singleNodeHighLevelClient")
    public RestHighLevelClient makeSingleRestHighLevelClient() {
        // 参考 https://juejin.im/post/5ddde6a75188256e956b008b
//        System.out.println("singleNodes = " + Arrays.toString(singleNodes));
        List<HttpHost> httpHosts = makeHttpHosts(singleNodes);
//        System.out.println("httpHosts = " + httpHosts);
        return makeRestHighLevelClient(httpHosts);
    }

    @Value("${elasticsearch.ip.cluster}")
    String[] clusterNodes;

    @Primary
    @Bean(value = "clusterHighLevelClient")
    public RestHighLevelClient makeClusterRestHighLevelClient() {
        // 参考 https://juejin.im/post/5ddde6a75188256e956b008b
//        System.out.println("Arrays.toString(clusterNodes) = " + Arrays.toString(clusterNodes));
        List<HttpHost> httpHosts = makeHttpHosts(clusterNodes);
        return makeRestHighLevelClient(httpHosts);
    }

    private List<HttpHost> makeHttpHosts(String[] nodes) {
        List<HttpHost> httpHosts = new ArrayList<>();
        // 解析节点
        for (String node : nodes) {
            String[] parts = StringUtils.split(node, ":");
            Assert.notNull(parts, "Must defined");
            Assert.state(parts.length == ADDRESS_LENGTH, "Must be defined as 'host:port'");
            httpHosts.add(new HttpHost(parts[0], Integer.parseInt(parts[1]), HTTP_SCHEME));
        }
        return httpHosts;
    }

    private RestHighLevelClient makeRestHighLevelClient(List<HttpHost> httpHosts) {
        // 创建builder
        HttpHost[] httpHostArray = new HttpHost[httpHosts.size()];
        httpHosts.toArray(httpHostArray);
        RestClientBuilder builder = RestClient.builder(httpHostArray);

        // 设置请求超时时间
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            requestConfigBuilder.setSocketTimeout(CONNECT_TIMEOUT_MILLIS);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
            return requestConfigBuilder;
        });

        // 设置连接数, 默认
        // 设置失效后的重试次数, 默认
        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(100);
            httpClientBuilder.setMaxConnPerRoute(100);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }

}