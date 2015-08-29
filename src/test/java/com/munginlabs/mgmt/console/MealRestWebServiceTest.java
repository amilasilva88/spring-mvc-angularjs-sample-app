package com.munginlabs.mgmt.console;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import sun.security.acl.PrincipalImpl;

import com.munginlabs.mgmt.console.config.root.RootContextConfig;
import com.munginlabs.mgmt.console.config.root.TestConfiguration;
import com.munginlabs.mgmt.console.config.servlet.ServletContextConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles("test")
@ContextConfiguration(classes = { TestConfiguration.class, RootContextConfig.class, ServletContextConfig.class })
public class MealRestWebServiceTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testSearchMealsByDate() throws Exception {
        mockMvc.perform(
                get("/meal").param("fromDate", "2015/01/01").param("toDate", "2015/01/02").param("pageNumber", "1")
                        .accept(MediaType.APPLICATION_JSON).principal(new PrincipalImpl("test123"))).andDo(print())
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.['meals'].[0].['description']").value("2 -  Chickpea with roasted cauliflower"));
    }

    @Test
    public void testSaveMeals() throws Exception {
        mockMvc.perform(
                post("/meal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "[{\"id\":\"1\", \"date\": \"2015/01/01\",\"time\": \"11:00\", \"calories\":\"100\", \"description\": \"test\" }]")
                        .accept(MediaType.APPLICATION_JSON).principal(new PrincipalImpl(UserServiceTest.USERNAME)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.[0].['description']").value("test"));
    }

    @Test
    public void deleteMeals() throws Exception {
        mockMvc.perform(
                delete("/meal").contentType(MediaType.APPLICATION_JSON).content("[14]")
                        .accept(MediaType.APPLICATION_JSON).principal(new PrincipalImpl(UserServiceTest.USERNAME)))
                .andDo(print()).andExpect(status().isOk());

    }

}
