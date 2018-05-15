package com.service.system;

public enum Config {
    Host("0.0.0.0"), 
    Port("0"),
    PollingCycle("60"),//系統輪詢時間週期(秒)
    IvAES("123456"),
    KeyAES("asdfghjklpoi");

    private String value;
    
    private Config(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return this.value;
    }
    
    public int toInt() {
        return Integer.parseInt(this.value);
    }
}