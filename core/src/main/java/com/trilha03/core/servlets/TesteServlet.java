package com.trilha03.core.servlets;

import com.trilha03.core.models.ModelTeste;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.factory.ModelFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(immediate = true, service = javax.servlet.Servlet.class, property = {
        "sling.servlet.methods=" + "POST",
        "sling.servlet.paths=" + "/bin/TesteServlet",
        "sling.servlet.extensions=" + "json"
})
public class TesteServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(TesteServlet.class);

    @Reference
    private ModelFactory modelFactory;

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        // Extrai dados diretamente dos parâmetros, evitando a leitura prematura do corpo da solicitação
        String nome = request.getParameter("nome"); 
        String idadeParam = request.getParameter("idade"); 
        String componentPath = request.getParameter("componentPath"); // Nome do parâmetro 

        // Validação do parâmetro de idade
        int idade;
        try {
            idade = Integer.parseInt(idadeParam); 
        } catch (NumberFormatException e) {
            handleBadRequest(response, "Parâmetro de idade inválido. Forneça um número inteiro válido.");
            return;
        }

        // Obtém o recurso de destino com base no caminho do componente fornecido
        Resource componentResource = request.getResourceResolver().getResource(componentPath);

        // Verifica se o recurso do componente foi encontrado
        if (componentResource == null) {
            LOG.error("Recurso do componente não encontrado para o caminho: {}", componentPath);
            handleBadRequest(response, "Recurso do componente não encontrado.");
            return;
        }

        // Adapta o recurso do componente para ModelTeste usando o ModelFactory
        ModelTeste modelTeste = modelFactory.createModel(componentResource, ModelTeste.class);

        // Atualiza as propriedades do ModelTeste
        modelTeste.setNome(nome); // Nome do método atualizado
        modelTeste.setIdade(idade); // Nome do método atualizado

        // Confirma as alterações
        try {
            modelTeste.updateProperties(); 
            LOG.info("Propriedades do node do AEM atualizadas com sucesso.");
            response.getWriter().write("Propriedades do node do AEM atualizadas com sucesso.");
        } catch (Exception e) {
            LOG.error("Erro ao atualizar as propriedades do node do AEM: {}", e);
            handleServerError(response, "Erro ao atualizar as propriedades do node do AEM.");
        }
    }

    
    private void handleBadRequest(SlingHttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(message);
    }

    private void handleServerError(SlingHttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(message);
    }
}
