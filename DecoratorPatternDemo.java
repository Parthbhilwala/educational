
// Component
interface Car {
  void assemble();
}

// Concrete Component
class BasicCar implements Car {
  @Override
  public void assemble() {
      System.out.println("Basic Car.");
  }
}

// Decorator
class CarDecorator implements Car {
  protected Car decoratedCar;

  public CarDecorator(Car car) {
      this.decoratedCar = car;
  }

  @Override
  public void assemble() {
      this.decoratedCar.assemble();
  }
}

// Concrete Decorator
class SunroofDecorator extends CarDecorator {
  public SunroofDecorator(Car car) {
      super(car);
  }

  @Override
  public void assemble() {
      super.assemble();
      System.out.println("Adding features of Sunroof.");
  }
}

// Demo
public class DecoratorPatternDemo {
  public static void main(String[] args) {
      Car basicCar = new BasicCar();
      Car luxuryCar = new SunroofDecorator(basicCar);
      luxuryCar.assemble();
  }
}
