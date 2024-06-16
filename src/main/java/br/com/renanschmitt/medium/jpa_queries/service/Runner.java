package br.com.renanschmitt.medium.jpa_queries.service;

import br.com.renanschmitt.medium.jpa_queries.entities.MaritalStatus;
import br.com.renanschmitt.medium.jpa_queries.repositories.PersonRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class Runner implements CommandLineRunner {

  private final PersonRepository personRepository;

  @Override
  public void run(String... args) throws Exception {
    log.info("Starting");

    int counter = 0;

    log.info(++counter + " - findByFirstNameAndLastName");
    personRepository.findByFirstNameAndLastName("John", "Doe");

    log.info(++counter + " - findByFirstNameStartingWith");
    personRepository.findByFirstNameStartingWith("Jo");

    log.info(++counter + " - findByFirstNameStartingWith");
    personRepository.findByFirstNameStartingWith("Jo", Sort.by("lastName"));

    log.info(++counter + " - findAllByLastNameOrderByFirstName");
    personRepository.findAllByLastNameOrderByFirstName("Doe");

    log.info(++counter + " - findAllByLastNameOrderByBirthDateDescFirstName");
    personRepository.findAllByLastNameOrderByBirthDateDescFirstName("Doe");

    log.info(++counter + " - findAllByBirthDateGreaterThanEqual");
    personRepository.findAllByBirthDateGreaterThanEqual(LocalDate.now());

    log.info(++counter + " - findAllByBirthDateBetween");
    personRepository.findAllByBirthDateBetween(
        LocalDate.of(1980, 1, 1), LocalDate.of(1989, 12, 31));

    log.info(++counter + " - findAllByMaritalStatus");
    personRepository.findAllByMaritalStatus(MaritalStatus.SINGLE);

    log.info(++counter + " - findAllByMaritalStatusIn");
    personRepository.findAllByMaritalStatusIn(List.of(MaritalStatus.SINGLE, MaritalStatus.MARRIED));

    log.info(++counter + " - existsByFirstNameOrLastName");
    personRepository.existsByFirstNameOrLastName("John", "Doe");

    log.info(++counter + " - countByBirthDateBefore");
    personRepository.countByBirthDateBefore(LocalDate.now());

    log.info(++counter + " - queryByLastName");
    personRepository.queryByLastName("Doe", PageRequest.of(1, 10, Sort.by("firstName")));

    log.info(++counter + " - findByCountryName");
    personRepository.findByBirthCountryName("Canada");

    log.info(++counter + " - findByBirthCountryNameStartingWith");
    personRepository.findByBirthCountryNameStartingWith("Ca");

    log.info(++counter + " - findByBirthCountryNameAndAddressCountryName");
    personRepository.findByBirthCountryNameAndAddressCountryName("Canada", "Canada");

    log.info("Ending");
  }
}
