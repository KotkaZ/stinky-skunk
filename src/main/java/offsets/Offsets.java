package offsets;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offsets {

    @SerializedName("timestamp")
    @Expose
    public long timestamp;
    @SerializedName("signatures")
    @Expose
    public Signatures signatures;
    @SerializedName("netvars")
    @Expose
    public Netvars netvars;

}
