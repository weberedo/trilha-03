package com.trilha03.core.servlets;

public @interface SlingServlet {

    String[] paths();

    String[] methods();

    boolean metatype();

}
