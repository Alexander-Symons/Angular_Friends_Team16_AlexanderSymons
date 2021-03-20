package db;

import java.util.ArrayList;
import java.util.List;

import domain.Group;
import domain.Person;

public interface GroupRepository {

    public abstract void add(Group group);

    public abstract void delete(String groupName);

    public abstract Group get(String groupName);

    public abstract ArrayList<Group> getAll();

//    public abstract Person getAuthenticatedUser(String email, String password);
//
//    public abstract void update(Person person);

}