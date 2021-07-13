package month;

public enum MonthEnum {
    JANUARY("Styczeń",31),
    FEBRUARY("Luty",28),
    MARCH("Marzec",31),
    APRIL("Kwiecień",30),
    MAY("Maj",31),
    JUNE("Czerwiec",30),
    JULY("Lipiec",31),
    AUGUST("Sierpień",31),
    SEPTEMBER("Wrzesień",30),
    OCTOBER("Październik",31),
    NOVEMBER("Listopad",30),
    DECEMBER("Grudzień",31);
    private final String desc;
    private int days;

    private MonthEnum(String desc, int days) {
        this.desc=desc;
        this.days=days;
    }

    public String getDesc() {
        return desc;
    }

    public int getDays() {
        return days;
    }


}