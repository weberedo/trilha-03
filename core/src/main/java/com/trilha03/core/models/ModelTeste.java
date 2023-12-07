package com.trilha03.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })
public class ModelTeste {

    @ValueMapValue(name = "sling:resourceType", injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;

    @SlingObject
    private ResourceResolver resourceResolver;

    private String nome;

    private int idade;

    @PostConstruct
    protected void init() {
        if (currentResource != null) {
            ModifiableValueMap valueMap = currentResource.adaptTo(ModifiableValueMap.class);

            if (valueMap != null) {
                // Pegando os valores
                nome = valueMap.get("nome", String.class);
                idade = valueMap.get("idade", 0);

               

                // Frases de debug
                System.out.println("nome: " + nome);
                System.out.println("idade: " + idade);
            } else {
                System.err.println("Failed to adapt currentResource to ModifiableValueMap");
            }
        } else {
            System.err.println("Current resource is null during init");
        }
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
        updateProperties();
    }

    public void setIdade(int idade) {
        this.idade = idade;
        updateProperties();
    }

    public ResourceResolver getResourceResolver() {
        return resourceResolver;
    }

    public void updateProperties() {
        if (currentResource != null && resourceResolver != null) {
            ModifiableValueMap valueMap = currentResource.adaptTo(ModifiableValueMap.class);
    
            if (valueMap != null) {
                if (nome != null) {
                    valueMap.put("nome", nome);
                }
                valueMap.put("idade", idade);
    
                // Aplicar as alterações
                try {
                    resourceResolver.commit();
                } catch (PersistenceException e) {
                    // Exception logging
                    e.printStackTrace();
                }
            } else {
                System.err.println("Failed to adapt currentResource to ModifiableValueMap during update");
            }
        } else {
            System.err.println("Current resource or ResourceResolver is null during update");
        }
    }
    
}
