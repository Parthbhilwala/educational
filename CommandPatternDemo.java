// Command Interface
interface Command {
  void execute();
}

// Receiver
class Light {
  public void turnOn() {
      System.out.println("Light is ON");
  }
  public void turnOff() {
      System.out.println("Light is OFF");
  }
}

// Concrete Command
class LightOnCommand implements Command {
  private Light light;

  public LightOnCommand(Light light) {
      this.light = light;
  }

  @Override
  public void execute() {
      light.turnOn();
  }
}

// Invoker
class SmartHome {
  private Command command;

  public void setCommand(Command command) {
      this.command = command;
  }

  public void pressButton() {
      command.execute();
  }
}

// Demo
public class CommandPatternDemo {
  public static void main(String[] args) {
      SmartHome smartHome = new SmartHome();
      Light light = new Light();
      Command lightOn = new LightOnCommand(light);

      smartHome.setCommand(lightOn);
      smartHome.pressButton();
  }
}
