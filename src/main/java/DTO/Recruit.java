package DTO;

public class Recruit {
    private String company;
    private String job;
    private int money;
    private String region;
    private String phone;

    public Recruit (String company, String job, int money, String region, String phone) {
        this.company = company;
        this.job = job;
        this.money = money;
        this.region = region;
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }
    public String getJob() {
        return job;
    }
    public int getMoney() {
        return money;
    }
    public String getRegion() {
        return region;
    }
    public String getPhone() {
        return phone;
    }
}
