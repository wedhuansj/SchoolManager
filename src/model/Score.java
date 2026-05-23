package model;

public class Score {
    private String subId;
    private double val;
    public Score (String subId, double val) {
        this.subId = subId;
        this.val = val;
    }
    public String getSubId() { return subId; }
    public void setSubId(String subId) { this.subId = subId; }
    public double getVal() { return val; }
    public void setVal(double val) { this.val = val; }
}
