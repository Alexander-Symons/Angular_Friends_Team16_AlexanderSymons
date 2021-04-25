package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Group;
import domain.Person;
import domain.PersonService;
import domain.Role;

public class GroupRepositoryStub implements GroupRepository {
    private Map<String, Group> groups = new HashMap<String, Group>();

    public GroupRepositoryStub (PersonService personService) {
        Group group1 = new Group("everyone");
        group1.setGroupmembers(personService.getPersons());
        groups.put(group1.getGroupname(),group1);
    }

    @Override
    public void add(Group group) {
        if(group == null){
            throw new IllegalArgumentException("No group given");
        }
        if (groups.containsKey(group.getGroupname())) {
            throw new IllegalArgumentException("Group already exists");
        }
        groups.put(group.getGroupname(), group);
    }

    @Override
    public void delete(String groupName) {
        if(groupName == null){
            throw new IllegalArgumentException("No groupname given");
        }
        groups.remove(groupName);

    }

    @Override
    public Group get(String groupName) {
        if(groupName == null){
            throw new IllegalArgumentException("No id given");
        }
        return groups.get(groupName);
    }

    @Override
    public ArrayList<Group> getAll() {
        return new ArrayList<Group>(groups.values());
    }
}
