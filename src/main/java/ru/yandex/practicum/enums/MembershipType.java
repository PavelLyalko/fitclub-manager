package ru.yandex.practicum.enums;

public enum MembershipType {
    ONE_MONTH(31),
    SIX_MONTHS(ONE_MONTH.days * 6),
    TWELVE_MONTHS (SIX_MONTHS.days * 2);

    private final int days;

    MembershipType(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
