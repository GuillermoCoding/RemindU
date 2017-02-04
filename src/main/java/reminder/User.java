package reminder;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="user_id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="last_name")
	private String lastName;
	@Size(min=7,message="Usename must be atleast 8 characters")
	@Column(name="user_name")
	private String userName;
	@Size(min=7,message="Password must be atleast 8 characters")
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Transient
	private List<Reminder> reminders = new ArrayList<Reminder>();;
	
	public User(){
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString(){
		return name+" "+lastName+" "+userName+" "+email;
	}
	public void setReminder(List<Reminder> reminders){
		this.reminders=reminders;
	}
	public List<Reminder> getReminders(){
		return reminders;		
	}	
	
}

	
	
	
	
	

	

