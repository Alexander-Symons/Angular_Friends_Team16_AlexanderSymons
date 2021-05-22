package domain;

import java.util.ArrayList;
import java.util.List;

import db.PersonRepository;
import db.PersonRepositoryStub;

public class PersonService {
	private PersonRepository personRepository = new PersonRepositoryStub();

	public PersonService(){
	}
	
	public Person getPerson(String personId)  {
		return getPersonRepository().get(personId);
	}

	public Person findPerson(String naam){
		for (Person p: personRepository.getAll()){
			if (p.getFirstName().equalsIgnoreCase(naam)){
				return p;
			}
		}
		return null;
	}

	public List<Person> getPersons() {
		return getPersonRepository().getAll();
	}

	public void addPerson(Person person) {
		getPersonRepository().add(person);
	}

	public void updatePersons(Person person) {
		getPersonRepository().update(person);
	}

	public void deletePerson(String id) {
		getPersonRepository().delete(id);
	}
	
	public Person getAuthenticatedUser(String email, String password) {
		return getPersonRepository().getAuthenticatedUser(email, password);
	}

	private PersonRepository getPersonRepository() {
		return personRepository;
	}

    public ArrayList<Person> findByName(String searchName) {
		ArrayList<Person> result = new ArrayList<>();
		for (Person p: personRepository.getAll()){
			if (p.getFirstName().equalsIgnoreCase(searchName)){
				result.add(p);
			}
		}
		return result;
    }

	public Person findByEmail(String email){
		for (Person p: personRepository.getAll()){
			if (p.getUserId().equalsIgnoreCase(email)){
				return p;
			}
		}
		return null;
	}
}
