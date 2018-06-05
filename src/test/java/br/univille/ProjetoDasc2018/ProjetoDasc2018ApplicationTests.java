package br.univille.ProjetoDasc2018;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.univille.ProjetoDasc2018.controller.HomeController;
import br.univille.ProjetoDasc2018.controller.MedicoController;
import br.univille.ProjetoDasc2018.controller.PacienteController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjetoDasc2018ApplicationTests {

@Autowired
private HomeController controller;
@Autowired
private PacienteController pacienteController;
@Autowired
private MedicoController medicoController;
@Autowired
private MockMvc mockMvc;

@Test
public void contextLoads() {
//Verifica a existência da instância do controlador
assertThat(controller).isNotNull();
assertThat(pacienteController).isNotNull();
assertThat(medicoController).isNotNull();
}

@Test
public void homeControllerTest() throws Exception {
//Teste do método index
this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
.andExpect(content().string(containsString("eu")));
}



@Test
public void pacienteControllerSaveTest() throws Exception {
    this.mockMvc.perform(post("/paciente")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("form", "")
            .content("id=0&nome=Zezinho&sexo=Masculino"))
            .andDo(print())
            .andExpect(status().isMovedTemporarily())
            .andExpect(view().name("redirect:/paciente"));
    
    this.mockMvc.perform(get("/paciente")).andDo(print()).andExpect(status().isOk())
    .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("Zezinho"))
    .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("Masculino")); 
}

@Test
public void medicoControllerSaveTest() throws Exception {
    this.mockMvc.perform(post("/medico")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("form", "")
            .content("id=0&nome=Walter&crm=1234"))
            .andDo(print())
            .andExpect(status().isMovedTemporarily())
            .andExpect(view().name("redirect:/medico"));
    
    this.mockMvc.perform(get("/paciente")).andDo(print()).andExpect(status().isOk())
    .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[1]/text()").string("Walter"))
    .andExpect(xpath("/html/body/div/div/table/tbody/tr/td[2]/text()").string("1234")); 
}


@Test
public void pacienteControllerTest() throws Exception {
//Teste do método index
this.mockMvc.perform(get("/paciente")).andDo(print()).andExpect(status().isOk())
.andExpect(xpath("//table").exists())
.andExpect(xpath("//td[contains(., 'Zezinho')]").exists());
}

@Test
public void medicoControllerTest() throws Exception {
	//Teste do método index
	this.mockMvc.perform(get("/medico")).andDo(print()).andExpect(status().isOk())
	.andExpect(xpath("//table").exists())
	.andExpect(xpath("//td[contains(., 'Walter')]").exists());
}


}