package ru.yandex.practicum.repository;

public interface VisitQueries {
    String INSERT_OPEN_VISIT = "INSERT INTO visit (client_id, start_visit,end_visit,visit_type) VALUES (:clientId,:startVisit)";
}
