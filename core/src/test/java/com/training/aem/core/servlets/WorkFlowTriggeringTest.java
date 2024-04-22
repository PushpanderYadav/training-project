package com.training.aem.core.servlets;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class WorkFlowTriggeringTest {
    AemContext aemContext = new AemContext();

    @Spy
    @InjectMocks
    private WorkFlowTriggering workFlowTriggering;

    @Mock
    MockSlingHttpServletRequest request;

    @Mock
    MockSlingHttpServletResponse response;

    @Mock
    ResourceResolver resolver;

    @Mock
    RequestParameter requestParameter;
    @Mock
    WorkflowSession workflowSession;
    @Mock
    WorkflowModel workflowModel;

    @Mock
    WorkflowData workflowData;

    @Test
    void doGet() throws WorkflowException, IOException {
        String page = "/content/training-project/us/en";
        String payload="/var/workflow/models/testingworkflow";
        when(request.getResourceResolver()).thenReturn(resolver);
        when(request.getRequestParameter(anyString())).thenReturn(requestParameter);
        when(requestParameter.getString()).thenReturn(page);
        when(resolver.adaptTo(WorkflowSession.class)).thenReturn(workflowSession);
        when(workflowSession.getModel("/var/workflow/models/testingworkflow")).thenReturn(workflowModel);
        when(workflowSession.newWorkflowData(anyString(),anyString())).thenReturn(workflowData);
        when(response.getWriter()).thenReturn(mock(PrintWriter.class));
        workFlowTriggering.doGet(request,response);
        atLeast(1);

    }
    @Test
    void doGetThrowException() throws IOException {
        when(request.getResourceResolver()).thenThrow(new RuntimeException());
        workFlowTriggering.doGet(request,response);

    }

}