package ru.yandex.practicum.repository;

public interface MembershipQueries {
    String INSERT_INTO_MEMBERSHIP = "INSERT INTO membership (client_id, type_id, start_date, total_days, total_freeze_days) " +
            "VALUES (?, (select id from membership_type where name = ?), ?, ?, ?)";

    String SELECT_MEMBERSHIP_WHERE_ID = "SELECT " +
            "m.id, " +
            "m.client_id, " +
            "m.type_id, " +
            "mt.name AS membership_type, " +
            "m.start_date, " +
            "m.total_days, " +
            "m.total_freeze_days " +
            "FROM membership m " +
            "JOIN membership_type mt ON m.type_id = mt.id " +
            "WHERE m.id = :id";

    String DELETE_MEMBERSHIP = "DELETE FROM membership WHERE id = :id";

    String SELECT_ALL_MEMBERSHIPS = "SELECT " +
            "m.id, " +
            "m.client_id, " +
            "m.type_id, " +
            "mt.name AS membership_type, " +
            "m.start_date, " +
            "m.total_days, " +
            "m.total_freeze_days " +
            "FROM membership m " +
            "JOIN membership_type mt ON m.type_id = mt.id ";

    String SELECT_MEMBERSHIP_BY_CLIENT_ID = """
            SELECT
                m.id,
                m.client_id,
                m.type_id,
                mt.name AS membership_type,
                m.start_date,
                m.total_days,
                m.total_freeze_days
            FROM membership m
            JOIN membership_type mt ON m.type_id = mt.id
            WHERE m.client_id = :clientId
              AND CURRENT_DATE BETWEEN m.start_date
                AND m.start_date + m.total_days
            ORDER BY m.start_date DESC
            LIMIT 1;
    """;
}
