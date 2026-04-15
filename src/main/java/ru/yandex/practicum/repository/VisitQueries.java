package ru.yandex.practicum.repository;

public interface VisitQueries {
    String INSERT_OPEN_VISIT = """
        INSERT INTO visit (client_id, start_visit)
        VALUES (:clientId, :startVisit)
        RETURNING id, client_id, start_visit, end_visit
        """;

    String UPDATE_CLOSE_VISIT = """
        UPDATE visit
        SET end_visit = :endVisit
        WHERE client_id = :clientId
          AND end_visit IS NULL
        """;
    String SELECT_VISIT_WHERE_ID = """
            SELECT id,
            client_id,
            start_visit,
            end_visit
            FROM visit
            WHERE client_id = :clientId
            """;
}