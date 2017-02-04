package reminder;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("loggedInUser")
@Controller
public class MVCController {
	
	@Autowired
	DatabaseService service;
	// Mapping for the welcome page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage(){
		return "welcome";
	}
	// Mapping for the login page get request
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		return "login";
	}
	// Mapping for the login page post request
	@RequestMapping(value="/login", method=RequestMethod.POST)
	// Accepting username and password from login form as parameters
	public String handleLoginRequest(@RequestParam String userName,@RequestParam String password,ModelMap model){
		// Method returns true if the username and password match a database entry 
		if(service.loginRequest(userName, password,model)){
			// Adding User object retrieved from the database to the model for display in the view
			model.addAttribute("loggedInUser", model.get("user"));
			return "user-page";
		}
		// Adding error message to the model for view display when username and password aren't valid
		else{
			model.addAttribute("loginError","Invalid Credentials");
			return "welcome";
		}
	}
	// Mapping for user page get request
	@RequestMapping(value="/user-page", method=RequestMethod.GET)
	public String showUserPage(ModelMap model){
		// Adding the current user to the model for view display
		model.put("user", model.get("loggedInUser"));
		return "user-page";
	}
	// Mapping for login page get request
	@RequestMapping(value="/sign-up",method=RequestMethod.GET)
	public String showSignUpPage(ModelMap model){
		// adding default User object to be populate with data in the view
		model.addAttribute("user",new User());	
		return "sign-up";
	}
	// Mapping for login page post request
	@RequestMapping(value="/sign-up",method=RequestMethod.POST)
	public String saveNewUser(ModelMap model, @Valid User user,BindingResult result){
		// Using Hibarnate Validator to validate user sign up input
		// see User class for input requirements 
		if(result.hasErrors()){
			return "sign-up";
		}
		// Method returns true when the user has been successfully saved in the database
		if(service.addUser(user,model)){
			// Adding the user that was added to the database to the model for view display
			model.addAttribute("loggedInUser", model.get("user"));
			return "user-page";
		}
		// User choose a username or email that was already taken 
		else{
			return "sign-up";
		}
	}
	// Mapping for add reminder page get request
	@RequestMapping(value="/add-reminder",method=RequestMethod.GET)
	public String showAddReminder(ModelMap model){
		model.addAttribute("reminder", new Reminder());
		return "add-reminder";
	}
	// Mapping for add reminder page post request
	@RequestMapping(value="/add-reminder",method=RequestMethod.POST)
	public String AddReminder(@ModelAttribute("reminder") Reminder reminder,ModelMap model){
	// Getting current logged User from the model to access it's user id
	User user = (User)model.get("loggedInUser");
	// Setting the reminder user ID with the ID from the current user
	reminder.setReminderUser(user.getId());
	service.addReminder(reminder,user);
	model.addAttribute("user", user);
		return "redirect:/user-page";
	}
	// Mapping for delete reminder get request
	@RequestMapping(value="/delete-reminder",method=RequestMethod.GET)
	public String deleteReminder(@RequestParam int id,ModelMap model){
		service.deleteReminder(id,(User)model.get("loggedInUser"));
		model.put("user", (User)model.get("loggedInUser"));
		return "user-page";
	}

}
