package coin.market.cap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.Coin;
import model.CoinMarketCap;
import model.USD;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class APICall {
    final private static String  apiKey = "bc27e606-6f8a-4864-8b14-930537e462f7";
    final private static String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";


    public static List<Coin>  callTop50Coins(){
        return callTop50Coins("1", "50", "USD");
    }
    public static List<Coin>  callTop50Coins(String limit, String convert){
        return callTop50Coins("1", limit, convert);
    }
    public static List<Coin>  callTop50Coins(String convert){
        return callTop50Coins("1", "50", convert);
    }

    public static List<Coin> callTop50Coins(String start, String limit, String convert){
        List<NameValuePair> paratmers = new ArrayList<>();
        paratmers.add(new BasicNameValuePair("start","1"));
        paratmers.add(new BasicNameValuePair("limit","500"));
        paratmers.add(new BasicNameValuePair("convert","USD"));
        paratmers.add(new BasicNameValuePair("cryptocurrency_type","coins"));
        String result = "";
        Gson gson = new Gson();
        try {
            result = makeAPICall(uri, paratmers);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject jobj = new Gson().fromJson(result, JsonObject.class);
        var jsonArray = jobj.get("data").getAsJsonArray();
            List<Coin> coinList = new ArrayList<>();
            for(int i =0; i< jsonArray.size(); i++){
                var obj = jsonArray.get(i).getAsJsonObject();
                System.out.println(result);
                var name = obj.get("name").getAsString();
                System.out.println("name: " + name);
                CoinMarketCap coinMarketCap = gson.fromJson(obj, CoinMarketCap.class);
                System.out.println(coinMarketCap.getName());
                Coin coin = new Coin();
                coin.setId(coinMarketCap.getId());
                coin.setName(coinMarketCap.getName());
                coin.setSymbol(coinMarketCap.getSymbol());
                coin.setSlug(coinMarketCap.getSlug());
                coin.setNum_market_pairs(coinMarketCap.getNum_market_pairs());
                DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSz");  // Specify locale to determine human language and cultural norms used in translating that input string.
                LocalDateTime dateAddedLdt = LocalDateTime.parse(coinMarketCap.getDate_added() , f );
                coin.setDate_added(dateAddedLdt.toInstant(ZoneOffset.UTC));

                coin.setMax_supply(coinMarketCap.getMax_supply());
                coin.setCirculating_supply(coinMarketCap.getCirculating_supply());
                coin.setTotal_supply(coinMarketCap.getTotal_supply());
                LocalDateTime lastUpdatedLdt = LocalDateTime.parse(coinMarketCap.getLast_updated() , f );
                coin.setLast_updated(lastUpdatedLdt.toInstant(ZoneOffset.UTC));

                var quote = coinMarketCap.getQuote();
                var usdObj  = quote.get("USD").getAsJsonObject();
                var usd = gson.fromJson(usdObj, USD.class);

                coin.setPrice(usd.getPrice());
                coin.setVolume_24h(usd.getVolume_24h());
                coin.setVolume_change_24h(usd.getVolume_change_24h());
                coin.setPercent_change_1h(usd.getPercent_change_1h());
                coin.setPercent_change_24h(usd.getPercent_change_24h());
                coin.setPercent_change_7d(usd.getPercent_change_7d());
                coin.setPercent_change_30d(usd.getPercent_change_30d());
                coin.setPercent_change_60d(usd.getPercent_change_60d());
                coin.setPercent_change_90d(usd.getPercent_change_90d());
                coin.setMarket_cap(usd.getMarket_cap());
                coin.setMarket_cap_dominance(usd.getMarket_cap_dominance());
                coin.setFully_diluted_market_cap(usd.getFully_diluted_market_cap());
                coinList.add(coin);
            }

            return coinList;
    }
    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }
}
