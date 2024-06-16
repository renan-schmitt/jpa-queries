package br.com.renanschmitt.medium.jpa_queries.repositories;

import br.com.renanschmitt.medium.jpa_queries.entities.MaritalStatus;
import br.com.renanschmitt.medium.jpa_queries.entities.Person;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

  Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);

  List<Person> findByFirstNameStartingWith(String firstName);

  List<Person> findByFirstNameStartingWith(String firstName, Sort sort);

  List<Person> findAllByLastNameOrderByFirstName(String lastName);

  List<Person> findAllByLastNameOrderByBirthDateDescFirstName(String lastName);

  List<Person> findAllByBirthDateGreaterThanEqual(LocalDate birthDate);

  List<Person> findAllByMaritalStatus(MaritalStatus maritalStatus);

  List<Person> findAllByBirthDateBetween(LocalDate start, LocalDate end);

  List<Person> findAllByMaritalStatusIn(Collection<MaritalStatus> maritalStatusCollection);

  boolean existsByFirstNameOrLastName(String firstName, String lastName);

  List<Person> findByBirthCountryName(String countryName);

  List<Person> findByBirthCountryNameStartingWith(String countryName);

  List<Person> findByBirthCountryNameAndAddressCountryName(
      String birthCountryName, String AddressCountryName);

  Page<Person> queryByLastName(String lastName, Pageable pageable);

  long countByBirthDateBefore(LocalDate birthDate);
}
