package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {

	private String userId;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private Role role;
	private String status;
	@JsonIgnore
	private ArrayList<Person> friendlist;
	private Map<Person, ArrayList<Bericht>> messages;

	public Person(String userId, String password, String firstName, String lastName,Role role) {
		setUserId(userId);
		setHashedPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
		setStatus("offline");
		this.friendlist = new ArrayList<>();
		this.messages = new HashMap<>();
	}

	public Person(String userId, String password, String salt, String firstName, String lastName,Role role) {
		setUserId(userId);
		setPassword(password);
		setSalt(salt);
		setFirstName(firstName);
		setLastName(lastName);
		setStatus("offline");
		setRole(role);
		this.friendlist = new ArrayList<>();
	}

	public void addMessage(Person zender, Person second_person, String bericht){
		int teller = 0;
		try {
			this.messages.get(second_person).add(new Bericht(zender,bericht));
		}catch (Exception e){
			System.out.println("test");
		}
	}

	public ArrayList<Bericht> getBerichten(){
		return this.messages.get(this);
	}

	public ArrayList<Bericht> getBerichten(Person second){
		return this.messages.get(second);
	}

	public ArrayList<Person> getFriendlist(){
		return this.friendlist;
	}

	public Person getVriendByName(String naam){
		for(Person p: friendlist){
			if(p.getFirstName().equalsIgnoreCase(naam)){
				return p;
			}
		}
		return null;
	}

	public void addFriend(Person person){
		if (!friendlist.contains(person)){
			this.friendlist.add(person);
			messages.put(person,new ArrayList<>());
		}else{
			System.out.println("al toegevoegd");
		}
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role=role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserId(String userId) {
		if (userId.isEmpty()) {
			throw new IllegalArgumentException("No id given");
		}
		String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(userId);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(hashPassword(password, getSalt()));
	}

	public void setPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public void setHashedPassword(String password) {
		if (password.isEmpty()) {
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}

	private String hashPassword(String password) {
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);

		String salt = new BigInteger(1, seed).toString(16);
		this.setSalt(salt);

		return hashPassword(password, salt);
	}

	private String hashPassword(String password, String seed) {
		String hashedPassword = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(salt.getBytes("UTF-8"));
			crypt.update(password.getBytes("UTF-8"));
			hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			throw new DomainException(e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			throw new DomainException(e.getMessage(), e);
		}
		return hashedPassword;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSalt() {
		return salt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName.isEmpty()) {
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;// firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName.isEmpty()) {
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

}
