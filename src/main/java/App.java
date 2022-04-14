import model.Coin;

import java.util.List;

import static coin.market.cap.APICall.callTop50Coins;
import static influxdb.LoadData.*;

public class App {
    public static void main(String[] args) {
        List<Coin> coins = callTop50Coins();
        connection();
        System.out.println(coins.size());
        for(Coin c: coins){
            System.out.println(c.toString());
            writeCoin(c);
        }
    }
}
