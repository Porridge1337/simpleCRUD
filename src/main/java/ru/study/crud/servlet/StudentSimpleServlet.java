package ru.study.crud.servlet;

import ru.study.crud.dto.StudentDto;
import ru.study.crud.service.UserService;
import ru.study.crud.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/student", "/delete", "/create"})
public class StudentSimpleServlet extends HttpServlet {

    private UserService service;
    private final static String index = "/WEB-INF/view/students-page.jsp";

    @Override
    public void init() throws ServletException {
        Map<Integer, StudentDto> students = new HashMap();
        students.put(1, new StudentDto(1, "Sergey", "Andronov"));
        students.put(2, new StudentDto(2, "Alexandr", "Krivenchuk"));
        students.put(3, new StudentDto(3, "Alexey", "Abramov"));
        service = new UserServiceImpl(students);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/delete":
                deleteStudent(req, resp);
            case "/create":
                createStudent(req, resp);
            default:
                listStudent(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    private boolean requestIsValid(final HttpServletRequest req) {

        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");

        return id != null && id.length() > 0 &&
                name != null && name.length() > 0 &&
                surname != null && surname.length() > 0 &&
                id.matches("[+]?\\d+");
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", service.findAllStudent());
        req.getRequestDispatcher(index).forward(req, resp);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.delete(id);
        //resp.sendRedirect("/student");
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (requestIsValid(req)) {
            //resp.sendRedirect("/student");
            final int id = Integer.parseInt(req.getParameter("id"));
            final String name = req.getParameter("name");
            final String surname = req.getParameter("surname");
            final StudentDto student = new StudentDto(id, name, surname);
            service.save(student);
        }
    }
}
