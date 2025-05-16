package ru.bmstu.iu7.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bmstu.iu7.API.model.Questionnaire;

@Repository
public interface SpringQuestionnaireRepository extends JpaRepository<Questionnaire, Long>{
}
