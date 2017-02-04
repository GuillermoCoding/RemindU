package reminder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reminders")
public class Reminder {
	
	@Id
	@Column(name="reminder_id")
	private int id;
	@Column(name="reminder_user")
	private int reminderUser;
	@Column(name="reminder_desc")
	private String desc;
	@Column(name="target_date")
	private String targetDate;
	@Column(name="is_done")
	private boolean isDone;
	
	public Reminder(){		
	}
	public int getReminderUser(){
		return reminderUser;
	}
	public void setReminderUser(int user){
		reminderUser=user;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}
	public boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString(){
		return id+" "+desc+" "+targetDate+" "+isDone;
	}
	
}
