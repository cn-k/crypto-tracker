import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import es.ElasticPublisher;
import model.Coin;
import model.Conn;

import java.io.IOException;
import java.util.List;

import static coin.market.cap.APICall.callTop50Coins;
import static influxdb.LoadData.*;

public class App {
    public static void main(String[] args){
        ElasticPublisher ep = new ElasticPublisher();
        List<Coin> coins = callTop50Coins();
        var connTmp = new Conn();
        var indices = ep.getAllIndices(connTmp);
        ep.closeConnection(connTmp);
        for (Coin c : coins) {
            Conn conn = new Conn();
            try {
                ep.createIndex(c, indices, conn);
                ep.insertData(c, conn);

            } catch (IOException | ElasticsearchException e) {
                e.printStackTrace();
            }
            finally {
                ep.closeConnection(conn);
            }
        }
        System.out.println("finished");
    }
}
