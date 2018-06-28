/**
 * Created by ZingBug on 2018/6/26.
 */
public class Image {
    private long id;
    private String address;
    private String label;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public String getLabel() {
        return label;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
