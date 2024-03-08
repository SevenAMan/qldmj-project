import java.util.List;

public class JavaBean {

    private String string;

    private int num;

    private Integer no;

    private List<ProxyTest> list;

    private ProxyTest[] array;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public List<ProxyTest> getList() {
        return list;
    }

    public void setList(List<ProxyTest> list) {
        this.list = list;
    }

    public ProxyTest[] getArray() {
        return array;
    }

    public void setArray(ProxyTest[] array) {
        this.array = array;
    }
}
