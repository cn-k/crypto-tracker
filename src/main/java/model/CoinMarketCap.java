package model;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
public class CoinMarketCap {
    private int id;
    private String name;
    private String symbol;
    private String slug;
    private int num_market_pairs;
    private String date_added;
    private String [] tags;
    private int max_supply;
    private int circulating_supply;
    private int total_supply;
    private Platform platform;
    private int cmc_rank;
    private String self_reported_circulating_supply;
    private String self_reported_market_cap;
    private String last_updated;
    private JsonObject quote;
}
