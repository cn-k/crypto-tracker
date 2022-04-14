package influxdb;


public class BuildConn {
    /*
    Logger log = LoggerFactory.getLogger("BuildConn");
    String userName = "crypto";
    String password = "password";

    public InfluxDB buildConnection(String url) {
        InfluxDB influxDB = InfluxDBFactory.connect(url, userName, password);
        return influxDB;
    }

    public void pong(InfluxDB influxDB) {
        Pong response = influxDB.ping();
        if (response.getVersion().equalsIgnoreCase("unknown")) {
            log.error("Error pinging server.");
            return;
        }
    }

    public void createDatabase(InfluxDB influxDB) {
        influxDB.createDatabase("baeldung");
        influxDB.createRetentionPolicy(
                "defaultPolicy", "baeldung", "30d", 1, true);
    }

     */
}
