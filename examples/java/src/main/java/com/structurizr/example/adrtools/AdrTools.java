package com.structurizr.example.adrtools;

import com.structurizr.Workspace;
import com.structurizr.api.WorkspaceApiClient;
import com.structurizr.importer.documentation.AdrToolsDecisionImporter;
import com.structurizr.model.*;
import com.structurizr.view.*;

import java.io.File;

/**
 * An example of how to import architecture decision records from Nat Pryce's adr-tools tool.
 *
 * The live workspace is available to view at https://structurizr.com/share/39459
 */
public class AdrTools {

    private static final long WORKSPACE_ID = 39459;
    private static final String API_KEY = "key";
    private static final String API_SECRET = "secret";

    private static final String FILE_SYSTEM_TAG = "File System";

    public static void main(String[] args) throws Exception {
        Workspace workspace = new Workspace("adr-tools", "A description of the adr-tools command line utility.");
        Model model = workspace.getModel();
        model.setImpliedRelationshipsStrategy(new CreateImpliedRelationshipsUnlessAnyRelationshipExistsStrategy());

        Person user = model.addPerson("User", "Somebody on a software development team.");
        SoftwareSystem adrTools = model.addSoftwareSystem("adr-tools", "A command-line tool for working with Architecture Decision Records (ADRs).");
        adrTools.setUrl("https://github.com/npryce/adr-tools");

        Container adrShellScripts = adrTools.addContainer("adr", "A command-line tool for working with Architecture Decision Records (ADRs).", "Shell Scripts");
        adrShellScripts.setUrl("https://github.com/npryce/adr-tools/tree/master/src");
        Container fileSystem = adrTools.addContainer("File System", "Stores ADRs, templates, etc.", "File System");
        fileSystem.addTags(FILE_SYSTEM_TAG);
        user.uses(adrShellScripts, "Manages ADRs using");
        adrShellScripts.uses(fileSystem, "Reads from and writes to");

        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(adrTools, "SystemContext", "The system context diagram for adr-tools.");
        contextView.addAllElements();

        ContainerView containerView = views.createContainerView(adrTools, "Containers", "The container diagram for adr-tools.");
        containerView.addAllElements();

        File adrDirectory = new File("./src/main/java/com/structurizr/example/adrtools/decisions");
        AdrToolsDecisionImporter decisionImporter = new AdrToolsDecisionImporter();
        decisionImporter.importDocumentation(adrTools, adrDirectory);

        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.ELEMENT).shape(Shape.RoundedBox).color("#ffffff");
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#18ADAD").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).shape(Shape.Person).background("#008282").color("#ffffff");
        styles.addElementStyle(Tags.CONTAINER).background("#6DBFBF");
        styles.addElementStyle(FILE_SYSTEM_TAG).shape(Shape.Folder);

        WorkspaceApiClient structurizrClient = new WorkspaceApiClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }

}