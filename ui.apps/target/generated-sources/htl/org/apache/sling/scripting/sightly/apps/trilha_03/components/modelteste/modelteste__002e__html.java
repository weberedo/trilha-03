/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.trilha_03.components.modelteste;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class modelteste__002e__html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_modelteste = null;
_global_modelteste = renderContext.call("use", com.trilha03.core.models.ModelTeste.class.getName(), obj());
out.write("<div>\n    <h2 class=\"cmp-modelteste__title\">Componente Model Teste</h2>\n    <div class=\"cmp-modelteste__item\">\n        <p class=\"cmp-modelteste__item-label\">Nome:</p>\n        <pre class=\"cmp-modelteste__item-output\" data-cmp-hook-modelteste=\"nome\">");
{
    Object var_0 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_modelteste, "nome"), "text");
    out.write(renderContext.getObjectModel().toString(var_0));
}
out.write("</pre>\n    </div>\n    <div class=\"cmp-modelteste__item\">\n        <p class=\"cmp-modelteste__item-label\">Idade:</p>\n        <pre class=\"cmp-modelteste__item-output\" data-cmp-hook-modelteste=\"idade\">");
{
    Object var_1 = renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_global_modelteste, "idade"), "text");
    out.write(renderContext.getObjectModel().toString(var_1));
}
out.write("</pre>\n    </div>\n</div>\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

