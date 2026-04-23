// Engine Interface 
interface Engine {
    void increase();
    void decrease();
    void setSpeed(int speed);
}

// Gasoline Engine
class GasolineEngine implements Engine {
    private int speed = 0;

    public void increase() {
        speed++;
    }

    public void decrease() {
        if (speed > 0) speed--;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

// Electronic Engine
class ElectronicEngine implements Engine {
    private int speed = 0;

    public void increase() {
        speed++;
    }

    public void decrease() {
        if (speed > 0) speed--;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

// ===== Hybrid Engine =====
class MixedHybridEngine implements Engine {

    private GasolineEngine gasEngine = new GasolineEngine();
    private ElectronicEngine electricEngine = new ElectronicEngine();
    private int speed = 0;

    public void increase() {
        speed++;
        applyLogic();
    }

    public void decrease() {
        if (speed > 0) speed--;
        applyLogic();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        applyLogic();
    }

    private void applyLogic() {
        if (speed < 50) {
            electricEngine.setSpeed(speed);
            gasEngine.setSpeed(0);
            System.out.println("Using Electric Engine");
        } else {
            gasEngine.setSpeed(speed);
            electricEngine.setSpeed(0);
            System.out.println("Using Gas Engine");
        }
    }
}

// ===== Car Class =====
class Car {
    private Engine engine;
    private int speed = 0;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        speed = 0;
        engine.setSpeed(speed);
        System.out.println("Car started");
    }

    public void stop() {
        speed = 0;
        engine.setSpeed(speed);
        System.out.println("Car stopped");
    }

    public void accelerate() {
        if (speed < 200) {
            speed += 20;
            engine.setSpeed(speed);
            System.out.println("Speed: " + speed);
        }
    }

    public void brake() {
        if (speed > 0) {
            speed -= 20;
            if (speed < 0) speed = 0;
            engine.setSpeed(speed);
            System.out.println("Speed: " + speed);
        }
    }
}

// Factory 
class CarFactory {

    public static Car createCar(String type) {
        Engine engine;

        switch (type.toLowerCase()) {
            case "gas":
                engine = new GasolineEngine();
                break;
            case "electric":
                engine = new ElectronicEngine();
                break;
            case "hybrid":
                engine = new MixedHybridEngine();
                break;
            default:
                throw new IllegalArgumentException("Invalid engine type");
        }

        return new Car(engine);
    }

    public static void replaceEngine(Car car, String type) {
        Engine engine;

        switch (type.toLowerCase()) {
            case "gas":
                engine = new GasolineEngine();
                break;
            case "electric":
                engine = new ElectronicEngine();
                break;
            case "hybrid":
                engine = new MixedHybridEngine();
                break;
            default:
                throw new IllegalArgumentException("Invalid engine type");
        }

        car.setEngine(engine);
    }
}

// Main
public class Main {
    public static void main(String[] args) {

        Car car = CarFactory.createCar("hybrid");

        car.start();
        car.accelerate(); // 20
        car.accelerate(); // 40
        car.accelerate(); // 60
        car.brake();      // 40
        car.stop();

        System.out.println("----- Change Engine -----");

        CarFactory.replaceEngine(car, "gas");

        car.start();
        car.accelerate();
        car.accelerate();
    }
}