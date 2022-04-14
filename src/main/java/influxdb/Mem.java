package influxdb;


import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

@Measurement(name = "memory")
public class Mem {

    @Column(name = "time")
    private Instant time;

    @Column(name = "name")
    private String name;

    @Column(name = "free")
    private Long free;

    @Column(name = "used")
    private Long used;

    @Column(name = "buffer")
    private Long buffer;
}