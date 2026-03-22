package ru.yandex.practicum.repository;

public interface MembershipQueries {
    String INSERT_INTO_MEMBERSHIP = "INSERT INTO membership (client_id, type_id, start_date, total_days, total_freeze_days) " +
            "VALUES (:clientId, (select id from membership_type where name = :membershipName), :startDate, :totalDays, :totalFreezeDays) RETURNING id";
    String SELECT_ID_FROM_MEMBERSHIP = "SELECT id FROM membership_type WHERE name = :name";
}
