package com.training.aem.core.servlets;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Objects;

@Component(service = { Servlet.class },
        property ={ "sling.servlet.paths=/bin/workflow",
                "sling.servlet.method=GET",
                "service.description=Workflow Triggering"})
public class WorkFlowTriggering extends SlingSafeMethodsServlet {
    private static final Logger LOGGER= LoggerFactory.getLogger(WorkFlowTriggering.class);
    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws IOException {
        String status="Workflow Start";
        try(ResourceResolver resolver=request.getResourceResolver()){
            String payload= Objects.requireNonNull(request.getRequestParameter("page")).getString();
            if(StringUtils.isNotEmpty(payload))
            {
                WorkflowSession workflowSession=resolver.adaptTo(WorkflowSession.class);
                if(workflowSession!=null) {
                            WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/testingworkflow");
                            WorkflowData workflowData =workflowSession.newWorkflowData("JCR_PATH", payload);
                            workflowSession.startWorkflow(workflowModel,workflowData);
                        }
                        response.setContentType("application/json");
                        response.getWriter().write(status);
                }
            } catch (Exception e) {
            LOGGER.info("error",e);
        }

    }


    }

