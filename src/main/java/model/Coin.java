package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coin {

    private int id;
    private String name;
    private String symbol;
    private String slug;
    private int num_market_pairs;
    //private String date_added;

    private int max_supply;
    private int circulating_supply;
    private int total_supply;
    //private String last_updated;

    private Long price;
    private Long volume_24h;
    private Long volume_change_24h;
    private Long percent_change_1h;
    private Long percent_change_24h;
    private Long percent_change_7d;
    private Long percent_change_30d;
    private Long percent_change_60d;
    private Long percent_change_90d;
    private Long market_cap;
    private Long market_cap_dominance;
    private Long fully_diluted_market_cap;
}
