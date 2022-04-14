package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class USD {
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
    private String last_updated;
}
