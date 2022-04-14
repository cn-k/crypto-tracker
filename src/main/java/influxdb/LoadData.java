package influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.query.FluxTable;
import model.Coin;

import java.util.List;

public class LoadData {
    static InfluxDBClient client;
    static String token ;
    static String bucket;
    static String org ;
    static String query ;
    static String hostName = "host";
    static String hostValue = "host1";

    public static void connection() {
        // You can generate a Token from the "Tokens Tab" in the UI
        token = "R7RYpGMeB-olkqU1XEMeG_z5abmowcuk_G4a4js0PAi6oNoXbiMj5eu93JnKSauR-Gzm0sUxLsWDItM_k_TlMQ==";
        bucket = "crypto-bucket";
        org = "crypto-workspace";
        client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());
        query = String.format("from(bucket: \"%s\") " +
                "  |> range(start: -24h)" +
                "  |> filter(fn: (r) => r[\"%s\"] == \"%s\") "
                , bucket, hostName, hostValue);
    }

    public static void writeCoin(Coin coin) {
        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writeMeasurement(bucket, org, WritePrecision.NS, coin);
        }
    }

    public static void writeData(){
        String data = "mem,host=host1 used_percent=23.43234543";
        try (WriteApi writeApi = client.getWriteApi()) {
            writeApi.writeRecord(bucket, org, WritePrecision.NS, data);
        }
    }

    public static void queryData(){
        //String query = String.format("from(bucket: \"%s\") |> range(start: -24h)", bucket);
        List<FluxTable> tables = client.getQueryApi().query(query, org);
        tables.forEach(ft -> ft.getRecords().forEach(r -> System.out.println(r.getValue())));
    }

}
