package xml;

public class SubUserBean extends UserBean {

    private String value;

    private String key;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "SubUserBean [name=" + getName() + ", age=" + getAge() + ", id=" + getId() + ", value=" + value + ", key=" + key + "]";
    }
}
