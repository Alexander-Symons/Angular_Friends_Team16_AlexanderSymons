package domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
    String groupname;
    List<Person> groupmembers = new ArrayList<>();

    public Group(String groupname){
        setGroupname(groupname);
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        if(!groupname.trim().isEmpty()) {
            this.groupname = groupname;
        }
    }

    public List<Person> getGroupmembers() {
        return groupmembers;
    }

    public void setGroupmembers(List<Person> groupmembers) {
        this.groupmembers = groupmembers;
    }
    public void addGroupmember(Person person){
        groupmembers.add(person);
    }
    public int getSize(){
        return groupmembers.size();
    }
}
