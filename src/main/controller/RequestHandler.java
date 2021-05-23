package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.GroupService;
import domain.PersonService;
import domain.Person;
import domain.Role;
import opdracht1.ScoreRepository;

import java.io.IOException;

public abstract class RequestHandler {

	private PersonService personService;
	private GroupService groupService;
	private ScoreRepository scoreRepository;

	public abstract String handleRequest (HttpServletRequest request, HttpServletResponse response) throws IOException;

	public void setModel (PersonService personService) {
		this.personService = personService;
	}
	public void setGroupService(GroupService groupService){this.groupService = groupService;}
	public void setScoreRepository(ScoreRepository scoreRepository){this.scoreRepository = scoreRepository;}

	public GroupService getGroupService(){return groupService;}
	public PersonService getPersonService() {
		return personService;
	}
	public ScoreRepository getScoreRepository(){return scoreRepository;}

	protected boolean isFromUserWithRole (HttpServletRequest request, Role role) {
		Person person = (Person) request.getSession().getAttribute("user");
		if (person != null && person.getRole().equals(role)) {
			return true;
		}
		return false;
	}

}
