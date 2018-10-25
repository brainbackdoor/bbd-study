package com.elasticsearch.bbd.config;

import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.UnknownHostException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EsConfigTest {

    @Autowired
    private EsConfig esConfig;

    @Test
    @DisplayName("🚀 Register elasticsearch client in bean object")
    public void connectionTest() throws UnknownHostException {
        TransportClient client = esConfig.client();
        assertThat(client.transportAddresses().get(0).toString(), is("127.0.0.1:9300"));
    }
}