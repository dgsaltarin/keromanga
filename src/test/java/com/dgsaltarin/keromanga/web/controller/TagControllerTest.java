package com.dgsaltarin.keromanga.web.controller;

import com.dgsaltarin.keromanga.domain.Tag;
import com.dgsaltarin.keromanga.domain.data.TagRequest;
import com.dgsaltarin.keromanga.domain.services.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private TagController tagController;
    @MockBean
    private TagService tagService;

    private Tag tag;
    private Tag tag2;
    private String name;
    private int id;
    private String request;
    private String badRequest;
    List<Tag> mockList = new ArrayList<>();

    @BeforeAll
    public void setUp() {
        tagService = Mockito.mock(TagService.class);
        tagController = new TagController(tagService);
        tag2 = new Tag();
        name = "tag name";
        id = 1;
        tag2.setName(name);
        tag2.setId(id);
        tag = Mockito.mock(Tag.class);
        mockList.add(tag2);
        request = "{\n" +
                "  \"name\": \"Fantasia\"\n" +
                "}";
        badRequest = "{\n" +
                "  \"nameasas\": \"Fantasia\"\n" +
                "}";
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }

    @Test
    public void shouldReturnNotFoundWhenNoTagFoundById() {
        when(tagService.getTagById(1)).thenReturn(Optional.empty());
        ResponseEntity<Tag> tag = tagController.getTagById(1);
        assertEquals(HttpStatus.NOT_FOUND, tag.getStatusCode());
    }

    @Test
    public void shouldReturnOkWhenTagFoundById() {
        when(tagService.getTagById(1)).thenReturn(Optional.of(tag));
        ResponseEntity<Tag> tag = tagController.getTagById(1);
        assertEquals(HttpStatus.OK, tag.getStatusCode());
    }

    @Test
    public void shouldReturnTagListWhenTagsFound() throws Exception {
        when(tagService.getAll()).thenReturn(Optional.of(mockList));
        mockMvc.perform(get("/tag/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(name)));
    }

    @Test
    public void shouldReturnNotFoundWhenNoTagsFound() throws Exception {
        when(tagService.getAll()).thenReturn(Optional.empty());
        mockMvc.perform(get("/tag/all"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnTagWhenTagFoundById() throws Exception {
        when(tagService.getTagById(any(Integer.class))).thenReturn(Optional.of(tag2));
        mockMvc.perform(get("/tag/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.id", is(id)));
    }

    @Test
    public void shouldReturnNotFoundWhenNoTagsFoundById() throws Exception {
        when(tagService.getTagById(any(Integer.class))).thenReturn(Optional.empty());
        mockMvc.perform(get("/tag/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnTagWhenTagCreatedSuccessfully() throws Exception {
        when(tagService.add(any(TagRequest.class))).thenReturn(tag2);
        mockMvc.perform(post("/tag/add")
        .param("request", request)
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name", is(name)));
    }

    @Test
    public void shouldReturnBadRequestWhenBadRequestSentToAdd() throws Exception {
        mockMvc.perform(post("/tag/add")
                .param("request", badRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnOkWhenTagDeletedSuccessfully() throws Exception {
        when(tagService.delete(any(Integer.class))).thenReturn(true);
        mockMvc.perform(delete("/tag/delete")
                .param("id", "1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundWhenNoTagFoundToDelete() throws Exception {
        when(tagService.delete(any(Integer.class))).thenReturn(false);
        mockMvc.perform(delete("/tag/delete")
                .param("id", "1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }
}