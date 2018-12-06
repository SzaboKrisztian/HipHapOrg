package org.hiphap.Screens;

import org.hiphap.Arrangement;
import org.hiphap.Event;
import org.hiphap.EventManager;

public class CompositionPrompt extends MenuScreen {
  private Event event;

  public CompositionPrompt(Event event) {
    this.event = event;
    addMenuOption("1", "Turn the event into an arrangement");
    addMenuOption("2", "Create a new arrangement, then add the event to it");
  }

  @Override
  void showContent() {
    clearScreen();
    System.out.println("To compose this with other events, it must first be turned into " +
        "an arrangement, or a new arrangement created and then this event added to it. " +
        "Which option do you prefer?");
  }

  @Override
  Transition handleInput(String input) {
    Transition result;
    Arrangement newArrangement;
    switch (input) {
      case "1":
        newArrangement = new Arrangement(this.event);
        EventManager.getInstance().addEvent(newArrangement);
        EventManager.getInstance().deleteEvent(this.event);
        result = new Transition(Transition.Type.COMPOSITION_CHANGE, "Event successfully turned into arrangement.");
        result.setPayload(newArrangement);
        return result;
      case "2":
        newArrangement = new Arrangement(clsAndReadString("Enter a name for the new arrangement: "));
        newArrangement.addSubEvent(this.event);
        EventManager.getInstance().addEvent(newArrangement);
        EventManager.getInstance().deleteEvent(this.event);
        result = new Transition(Transition.Type.COMPOSITION_CHANGE, "Event successfully turned into arrangement.");
        result.setPayload(newArrangement);
        return result;
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }
}
