package com.webapp.phystechschool.controller;

import com.webapp.phystechschool.model.PhystechClass;
import com.webapp.phystechschool.repository.PhystechClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @Mock
    private PhystechClassRepository phystechClassRepository;

    @InjectMocks
    private AdminController adminController;

    @Test
    public void testSaveClassDetails() {
        PhystechClass phystechClass = new PhystechClass();
        BindingResult errors = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        String result = adminController.saveClassDetails(phystechClass, errors, redirectAttributes);

        assertEquals("redirect:/admin/displayClasses", result);
        verify(errors, times(1)).hasErrors();
        verify(phystechClassRepository, times(1)).save(eq(phystechClass));
    }

    @Test
    void testDisplayStudents() {
        AdminController adminController = new AdminController();
        PhystechClassRepository phystechClassRepository = mock(PhystechClassRepository.class);
        adminController.phystechClassRepository = phystechClassRepository;

        HttpSession httpSession = mock(HttpSession.class);
        PhystechClass mockPhystechClass = new PhystechClass();
        mockPhystechClass.setClassId(1);
        mockPhystechClass.setName("Test Class");

        when(phystechClassRepository.findById(1)).thenReturn(Optional.of(mockPhystechClass));

        ModelAndView modelAndView = adminController.displayStudents(1, httpSession, null);

        assertEquals("students.html", modelAndView.getViewName());
        assertEquals(mockPhystechClass, modelAndView.getModel().get("phystechClass"));
        verify(httpSession, times(1)).setAttribute("phystechClass", mockPhystechClass);
    }
}