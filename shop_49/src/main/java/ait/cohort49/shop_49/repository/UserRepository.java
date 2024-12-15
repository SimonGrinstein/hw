package ait.cohort49.shop_49.repository;

import ait.cohort49.shop_49.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
findBy - для поиска / объекты, списки, потоки, Optional
countBy - для подсчета / long
deleteBy - для удаления / void, long
existsBy - для проверки существования / boolean

+ использовать имена полей наших сущностей
findByLastname - ищет сущности по фамилии
findByAgeGreaterThan - ищет все сущности, у которых возраст больше указанного

And
Or
Between
LessThan, GreaterThan
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAll();
}