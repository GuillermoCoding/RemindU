package reminder;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class DatabaseService {

	// Creating a factory from the hibernate config file
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
								addAnnotatedClass(User.class).addAnnotatedClass(Reminder.class).buildSessionFactory();
	Session session;
	
	//Add user will return true if the user was saved to the database successfully 
	public boolean addUser(User user,ModelMap model){
	boolean status=false;
	
	session = factory.getCurrentSession();
	session.beginTransaction();
	
	List<User> userNames = session.createQuery("from User u where u.userName='"+user.getUserName()+"'").getResultList();
	List<User> emails = session.createQuery("from User u where u.email='"+user.getEmail()+"'").getResultList();
	
	if(!emails.isEmpty()){
		model.addAttribute("emailError", "Email is already linked to another account");
		status=false;
	}
	if(!userNames.isEmpty()){
		model.addAttribute("userNameError","Username is already taken");
		status=false;
	}
	else {	
		// Encrypting the password to the database
		String securePassowrd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
		user.setPassword(securePassowrd);
		session.save(user);
		// Getting user that was added from the database
		List<User> users = session.createQuery("from User u where u.userName='"+user.getUserName()+"'").getResultList();
		model.addAttribute("user", users.get(0));
		status=true;
	}		
		session.getTransaction().commit();
		return status;
	}
	// loginRequest will return true if the username and password match an entry in the database
	public boolean loginRequest(String userName,String password,ModelMap model){
	boolean status=false;	
	session = factory.getCurrentSession();	
	session.beginTransaction();	
		
	List<User> users = session.createQuery("from User u where u.userName='"+userName+"'").getResultList();	
	if(users.size()>0){
	User user = users.get(0);
	// Checking username and encrypted password
	if(user.getUserName().equals(userName) && BCrypt.checkpw(password,user.getPassword())){
		List<Reminder> reminders = new ArrayList<Reminder>();
		// Getting user reminders that match its user id from the database
		reminders = session.createQuery("from Reminder r where r.reminderUser='"+user.getId()+"'").getResultList();
		user.setReminder(reminders);
		model.addAttribute("user", user);
		status=true;
	}
	// Password dose not match
	else{
		status=false;
		}
	}
	session.getTransaction().commit();
	return status;
	}
	// Adding user's reminder to the database
	public void addReminder(Reminder reminder,User user){
		session = factory.getCurrentSession();
		session.beginTransaction();
		// Saving reminder to the database
		session.save(reminder);
		// Retrieving all the user reminders from the database with matching user id
		List<Reminder> reminders = session.createQuery("from Reminder r where r.reminderUser='"+user.getId()+"'").getResultList();
		// Updating the user's reminders ArrayList
		user.setReminder(reminders);
		session.getTransaction().commit();
	}
	// Deleting user's reminders from the database
	public void deleteReminder(int reminderId, User user){
		session = factory.getCurrentSession();
		session.beginTransaction();
		// Deleting user's reminder from the database
		session.createQuery("delete Reminder r where r.id='"+reminderId+"'").executeUpdate();
		// Retrieving an update list of the user's reminders
		List<Reminder> reminders = session.createQuery("from Reminder r where r.reminderUser='"+user.getId()+"'").getResultList();
		session.getTransaction().commit();		
		user.setReminder(reminders);	
	}
}
