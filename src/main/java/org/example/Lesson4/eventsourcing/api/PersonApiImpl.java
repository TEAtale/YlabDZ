package org.example.Lesson4.eventsourcing.api;

import org.example.Lesson4.eventsourcing.Person;

import javax.sql.DataSource;
import java.util.List;

/**
 * Тут пишем реализацию
 */
public class PersonApiImpl implements PersonApi {

  private DataSource dataSource;



  @Override
  public void deletePerson(Long personId) {
    
  }

  @Override
  public void savePerson(Long personId, String firstName, String lastName, String middleName) {

  }

  @Override
  public Person findPerson(Long personId) {
    return null;
  }

  @Override
  public List<Person> findAll() {
    return null;
  }
}
