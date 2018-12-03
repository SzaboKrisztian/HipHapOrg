package org.hiphap.Screens;

import org.hiphap.Event;
import org.hiphap.EventResource;
import java.util.ArrayList;

public class ManageEventResources extends MenuScreen {
  Event currentEvent;

  public ManageEventResources(Event currentEvent) {
    this.currentEvent = currentEvent;
    addMenuOption("1", "Add new resource");
    addMenuOption("2", "Delete resource");
  }

  @Override
  void showContent() {
    clearScreen();
    ArrayList<EventResource> resources = currentEvent.getEventResources();
    for (EventResource resource: resources) {
      System.out.printf(" - %s: %.2f kr.%n", resource.getName(), resource.getCost());
    }
    System.out.printf("%n");
  }

  @Override
  Transition handleInput(String input) {
    switch (input) {
      case "1":
        String name = null;
        do {
          if (name != null && name.equals("")) {
            System.out.println("Name cannot be blank.");
          }
          name = clsAndReadString("Enter resource name: ");
        } while (name.equals(""));
        Double price = clsAndReadDouble("Enter price: ");
        if (price == null) {
          currentEvent.addEventResource(new EventResource(name));
        } else {
          currentEvent.addEventResource(new EventResource(name, price));
        }
        return new Transition(Transition.Type.SUCCESS, "Resource successfully added");
      case "2":
        EventResource selection;
        String query = clsAndReadString("Enter a query: ");
        ArrayList<EventResource> resources = currentEvent.getEventResources(query);
        if (resources.isEmpty()) {
          return new Transition(Transition.Type.INVALID, "No resource matched your query.");
        } else if (resources.size() == 1) {
          selection = resources.get(0);
        } else {
          int index = 1;
          for (EventResource resource: resources) {
            System.out.printf("[%d] %s: %.2f kr.%n", index++, resource.getName(), resource.getCost());
          }
          Integer option = readInteger("Select a resource: ");
          if (option != null && option >= 1 && option <= resources.size()) {
            selection = resources.get(option - 1);
          } else {
            return new Transition(Transition.Type.INVALID, "Invalid selection.");
          }
        }
        if (currentEvent.deleteEventResource(selection)) {
          return new Transition(Transition.Type.SUCCESS, "Resource successfully deleted.");
        } else {
          return new Transition(Transition.Type.INVALID, "Failed to delete resource.");
        }
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  private void printResources(String filter) {
    ArrayList<EventResource> resources = currentEvent.getEventResources(filter);
    int index = 1;
    for (EventResource resource: resources) {
        System.out.printf("[%d] %s - %.2f", index++, resource.getName(), resource.getCost());
    }
  }

  private void printResources() {
    printResources("");
  }
}
