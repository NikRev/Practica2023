package ru.neoflex.practics4.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.practics4.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}