package ntudp.psj.lab3.controller;

import ntudp.psj.lab3.AbbreviationsMaker;
import ntudp.psj.lab3.Printer;
import ntudp.psj.lab3.model.Faculty;

import java.util.ArrayList;

public class FacultyController implements
        Printer<Faculty>,
        AbbreviationsMaker {
    private ProfessorController professorController;
    private ArrayList<Faculty> faculties = new ArrayList<>();
    private final String[] FACULTY_NAMES = new String[]{"Information Technology", "Management"};

    public FacultyController(ProfessorController professorController, int facultiesAmount) {
        this.professorController = professorController;
        int facultiesMax = Math.min(facultiesAmount, FACULTY_NAMES.length);
        createFaculties(facultiesMax);
    }

    private void createFaculties(int n) {
        for (int i = 0; i < n; i++) {
            Faculty faculty = new Faculty(FACULTY_NAMES[i], professorController.getVacantProfessor());
            faculties.add(faculty);
            professorController.assignPositionToProfessor("dean" + makeAbbreviation(faculty.getName()));
            printCreationText(faculty);
        }
    }

    @Override
    public void printCreationText(Faculty faculty) {
        System.out.printf("Faculty of " + faculty.getName() + " is created. ");
        printHeadInfo(faculty);
    }

    @Override
    public void printHeadInfo(Faculty faculty) {
        System.out.println("Dean: " + faculty.getHead().getFullName() + ".");
    }

    public void linkDepartments(DepartmentController departmentController) {
        for (Faculty faculty : faculties) {
            faculty.setDepartments(departmentController.getDepartments());
        }
    }

    public Faculty[] getFaculties() {
        return faculties.toArray(Faculty[]::new);
    }
}
