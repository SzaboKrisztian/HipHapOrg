package org.hiphap.Screens;

import org.hiphap.Event;

public class ManageParticipants extends MenuScreen {
  private Event currentEvent;

  public ManageParticipants(Event event) {
    this.currentEvent = event;
    addMenuOption("1", "Add participant from persons database");
    addMenuOption("2", "Add participant from organizations database");
    addMenuOption("3", "Add new person as participant");
    addMenuOption("4", "Add new organization as participant");
    addMenuOption("5", "Delete participant");
  }


  @Override
  void showContent() {

  }

  @Override
  Transition handleInput(String input) {
    return null;
  }
}
