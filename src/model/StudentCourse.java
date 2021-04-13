package model;

public class StudentCourse {
	private int idStudent;
	private int idCourse;
	
	public StudentCourse() {
		
	}
	
	public StudentCourse(int idStudent, int idCourse) {
		super();
		this.idStudent = idStudent;
		this.idCourse = idCourse;
	}
	
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public int getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}
	
}
