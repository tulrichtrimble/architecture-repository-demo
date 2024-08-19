package com.structurizr.example;

import com.structurizr.Workspace;
import com.structurizr.api.WorkspaceApiClient;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.*;

/**
 * An example of how to use filtered views to show "before" and "after" views of a software system.
 *
 * You can see the live diagrams at https://structurizr.com/public/19911
 */
public class FilteredViews {

    private static final long WORKSPACE_ID = 19911;
    private static final String API_KEY = "key";
    private static final String API_SECRET = "secret";

    private static final String CURRENT_STATE = "Current State";
    private static final String FUTURE_STATE = "Future State";

    public static void main(String[] args) throws Exception {
        Workspace workspace = new Workspace("Filtered Views", "An example of using filtered views.");
        Model model = workspace.getModel();

        Person user = model.addPerson("User", "A description of the user.");
        SoftwareSystem softwareSystemA = model.addSoftwareSystem("Software System A", "A description of software system A.");
        SoftwareSystem softwareSystemB = model.addSoftwareSystem("Software System B", "A description of software system B.");
        softwareSystemB.addTags(FUTURE_STATE);

        user.uses(softwareSystemA, "Uses for tasks 1 and 2").addTags(CURRENT_STATE);
        user.uses(softwareSystemA, "Uses for task 1").addTags(FUTURE_STATE);
        user.uses(softwareSystemB, "Uses for task 2").addTags(FUTURE_STATE);

        ViewSet views = workspace.getViews();
        SystemLandscapeView systemLandscapeView = views.createSystemLandscapeView("SystemLandscape", "An example System Landscape diagram.");
        systemLandscapeView.addAllElements();

        views.createFilteredView(systemLandscapeView, "CurrentState", "The current system landscape.", FilterMode.Exclude, FUTURE_STATE);
        views.createFilteredView(systemLandscapeView, "FutureState", "The future state system landscape after Software System B is live.", FilterMode.Exclude, CURRENT_STATE);

        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.ELEMENT).color("#ffffff");
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#91a437").shape(Shape.RoundedBox);
        styles.addElementStyle(Tags.PERSON).background("#6a7b15").shape(Shape.Person);

        WorkspaceApiClient structurizrClient = new WorkspaceApiClient(API_KEY, API_SECRET);
        structurizrClient.putWorkspace(WORKSPACE_ID, workspace);
    }

}