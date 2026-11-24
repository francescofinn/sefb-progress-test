package model;

public abstract class Student {
    private String name;
    private String cid;
    private String type;

    public Student(String n, String c) {
        assert n != null;
        assert c != null;
        this.name = n;
        this.cid = c;
    }

    public String getName() {
        return name;
    }

    public String getCid() {
        return cid;
    }

    public String getType() {
        return type;
    }
}
