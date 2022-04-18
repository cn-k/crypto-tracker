package es;


import co.elastic.clients.elasticsearch.cat.ElasticsearchCatClient;
import co.elastic.clients.elasticsearch.cat.IndicesResponse;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.IndexSettings;
import co.elastic.clients.elasticsearch.indices.PutIndicesSettingsRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.google.gson.Gson;
import jakarta.json.Json;
import jakarta.json.stream.JsonParser;
import model.Coin;
import model.Conn;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ElasticPublisher {

    public void createIndex(Coin coin, List<String> indices, Conn conn) throws IOException {
         var indexName = extractIndexName.apply(coin);
        var settings = new IndexSettings.Builder().shards(1).build();
        // Create the "products" index
        //conn.client.indices().putSettings(new PutIndicesSettingsRequest.Builder().settings(settings).build());
        if(!indices.contains(indexName)){
            conn.client.indices().create(c -> c.index(indexName));
        }
    }

    public void createIndexWithData(Coin coin, Conn conn) {
        try {
            final JsonParser parser = Json.createParser(new StringReader(new Gson().toJson(coin)));
            JacksonJsonpMapper mapper = new JacksonJsonpMapper();
            CreateIndexRequest req = CreateIndexRequest.of(b -> b.index(coin.getName().toLowerCase())
                    .withJson(parser, mapper));
            boolean created = conn.client.indices().create(req).acknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.transport.close();
                conn.restClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertData(Coin coin, Conn conn) throws IOException {
        var indexName = extractIndexName.apply(coin);
        System.out.println("insertData" + coin.getName());
        IndexRequest indexRequest = new IndexRequest.Builder<Coin>().document(coin).index(indexName).build();
        conn.client.index(indexRequest);
    }
    public void closeConnection(Conn conn){
        try {
            conn.transport.close();
            conn.restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public List<String> getAllIndices(Conn conn) {
        ElasticsearchCatClient elasticsearchCatClient = new ElasticsearchCatClient(conn.transport);
        IndicesResponse indices = null;
        try {
            indices = elasticsearchCatClient.indices();
        } catch (IOException e) {
            e.printStackTrace();
        }
        var indicesList = indices.valueBody().stream().map(body -> body.index()).collect(Collectors.toList());
        return indicesList;
    }
    public Function<Coin, String> extractIndexName = (Coin coin) -> coin.getName().toLowerCase().replace(" ", "_")
            .replace("#", ".,.")
            .replace("/","...");
}
