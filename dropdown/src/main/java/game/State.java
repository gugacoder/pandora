package game;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class State {

  private static Map<Behaviour, State> index = new HashMap<Behaviour, State>();
  
  private Object parent;
  
  private final LinkedList<State> children;
  private final LinkedList<Behaviour> behaviours;
  private final LinkedList<Object> newest;
  private final LinkedList<Object> losses;
  
  public State() {
    children = new LinkedList<State>();
    behaviours = new LinkedList<Behaviour>();
    newest = new LinkedList<Object>();
    losses = new LinkedList<Object>();
  }
  
  void setParent(Object parent) {
    this.parent = parent;
  }
  
  public State getParent() {
    if (parent instanceof State)
      return (State) parent;
    return null;
  }
  
  public Game getGame() {
    if (parent instanceof Game)
      return (Game) parent;
    if (parent instanceof State)
      return ((State) parent).getGame();
    return null;
  }
  
  public static State getState(Behaviour behaviour) {
    return index.get(behaviour);
  }
  
  public void add(State child) {
    newest.add(child);
  }

  public void add(Behaviour behaviour) {
    newest.add(behaviour);
  }
  
  public void remove(State child) {
    losses.add(child);
  }
  
  public void remove(Behaviour behaviour) {
    losses.add(behaviour);
  }

  public void prepare() {
    for (State state : children) {
      state.prepare();
    }
    while (!newest.isEmpty()) {
      Object item = newest.pop();
      if (item instanceof State) {
        State child = (State) item;
        children.add(child);
        child.setParent(this);
        child.prepare();
      } else {
        Behaviour behaviour = (Behaviour) item;
        behaviours.add(behaviour);
        index.put(behaviour, this);
        behaviour.welcome();
      }
    }
  }

  public void update(int w, int h) {
    for (State state : children) {
      state.update(w, h);
    }
    for (Behaviour behaviour : behaviours) {
      behaviour.update(w, h);
    }
  }

  public void render(Graphics2D g, int w, int h) {
    for (State state : children) {
      state.render(g, w, h);
    }
    for (Behaviour behaviour : behaviours) {
      behaviour.render(g, w, h);
    }
  }

  public void cleanup() {
    for (State state : children) {
      state.cleanup();
    }
    while (!losses.isEmpty()) {
      Object item = losses.pop();
      if (item instanceof State) {
        State child = (State) item;
        child.setParent(null);
        children.remove(child);
      } else {
        Behaviour behaviour = (Behaviour) item;
        behaviour.bye();
        behaviours.remove(behaviour);
        index.remove(behaviour);
      }
    }
  }
}
