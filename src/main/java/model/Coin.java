package model;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Measurement(name = "coin")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coin {
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "slug")
    private String slug;
    @Column(name = "num_market_pairs")
    private int num_market_pairs;
    @Column(name = "date_added")
    private Instant date_added;


    @Column(name = "max_supply")
    private int max_supply;
    @Column(name = "circulating_supply")
    private int circulating_supply;
    @Column(name = "total_supply")
    private int total_supply;
    @Column(name = "last_updated")
    private Instant last_updated;


    @Column(name = "price")
    private Long price;
    @Column(name = "volume_24h")
    private Long volume_24h;
    @Column(name = "volume_change_24h")
    private Long volume_change_24h;
    @Column(name = "percent_change_1h")
    private Long percent_change_1h;
    @Column(name = "percent_change_24h")
    private Long percent_change_24h;
    @Column(name = "percent_change_7d")
    private Long percent_change_7d;
    @Column(name = "percent_change_30d")
    private Long percent_change_30d;
    @Column(name = "percent_change_60d")
    private Long percent_change_60d;
    @Column(name = "percent_change_90d")
    private Long percent_change_90d;
    @Column(name = "market_cap")
    private Long market_cap;
    @Column(name = "market_cap_dominance")
    private Long market_cap_dominance;
    @Column(name = "fully_diluted_market_cap")
    private Long fully_diluted_market_cap;

}
