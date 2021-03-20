package domain;

import java.util.ArrayList;
import java.util.List;

import db.GroupRepository;
import db.GroupRepositoryStub;
import db.PersonRepository;
import db.PersonRepositoryStub;

public class GroupService {
    private GroupRepository groupRepository ;

    public GroupService(PersonService personService) {
        groupRepository = new GroupRepositoryStub(personService);

    }
    public Group getGroup(String groupName)  {
        return getGroupRepository().get(groupName);
    }

    public List<Group> getAll() {
        return getGroupRepository().getAll();
    }

    public void addGroup(Group group) {
        getGroupRepository().add(group);
    }

    public void deletePerson(String groupName) {
        getGroupRepository().delete(groupName);
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    public List<Group> getAllWithUser(Person person){
        List<Group> result = new ArrayList<>();
        for(Group g : getAll()){
            for(Person p: g.getGroupmembers()){
                if(p.getUserId().equals(person.getUserId())){
                    result.add(g);
                    break;
                }
            }
        }
        return result;
    }
}
