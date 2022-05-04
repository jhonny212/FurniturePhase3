package com.furniture.inventoryService.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    Util util;
    @Mock
    private BindingResult result;
    @Mock
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        util = new Util();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void formatMessageWithNoErrors() {
        List<FieldError> list = new ArrayList();
        Mockito.when(
                result.getFieldErrors()
        ).thenReturn(list);
        assertEquals(util.formatMessage(result),"{\"code\":\"01\",\"messages\":[]}");
    }

    @Test
    void formatMessageWithErrors() {
        List<FieldError> list = new ArrayList();
        FieldError err1 = new FieldError("s","s","s");
        list.add(err1);
        Mockito.when(
                result.getFieldErrors()
        ).thenReturn(list);

        assertEquals(util.formatMessage(result),"{\"code\":\"01\",\"messages\":[{\"s\":\"s\"}]}");
    }

}