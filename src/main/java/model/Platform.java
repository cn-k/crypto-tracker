package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Platform {
    private int id;
    private String name;
    private String symbol;
    private String slug;
    private String token_address;
}
