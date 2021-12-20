import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class OurGarageTest {

    @AfterEach
    void tearDown() {
    }

    @Test
    void allCarsUniqueOwners() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        garage.addCar(car, own);
        Collection<Owner> owners = garage.allCarsUniqueOwners();
        StringBuilder s = new StringBuilder();
        for (Owner owner :
                owners) {
            s.append(owner.getName());
        }
        assertEquals("Denis", s.toString());
    }

    @Test
    void topThreeCarsByMaxVelocity() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        Car car2 = new Car(3, "Nisan", "x5", 50, 10, 3);
        Owner own2 = new Owner(3, "Dyn", "Pupy", 38);
        Car car1 = new Car(2, "Nisan", "x5", 70, 10, 2);
        Owner own1 = new Owner(2, "Den", "Pup", 38);
        garage.addCar(car, own);
        garage.addCar(car1, own1);
        garage.addCar(car2, own2);
        Collection<Car> models = garage.topThreeCarsByMaxVelocity();
        StringBuilder s = new StringBuilder();
        for (Car iter_car:
                models) {
            s.append(iter_car.getMaxVelocity());
            s.append(" ");
        }
        //System.out.println(s.toString());
        assertEquals("70 60 50 ", s.toString());
    }

    @Test
    void allCarsOfBrand() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        garage.addCar(car, own);
        Collection<Car> models = garage.allCarsOfBrand("Nisan");
        Collection<Car> models_viet = garage.allCarsOfBrand("Viet");
        assertNull(models_viet);
        StringBuilder s = new StringBuilder();
        for (Car iter_car:
                models) {
            s.append(iter_car.getBrand());
        }
        assertEquals("Nisan", s.toString());
    }

    @Test
    void carsWithPowerMoreThan() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        Car car2 = new Car(3, "Nisan", "x5", 50, 10, 3);
        Owner own2 = new Owner(3, "Dyn", "Pupy", 38);
        Car car1 = new Car(2, "Nisan", "x5", 70, 10, 2);
        Owner own1 = new Owner(2, "Den", "Pup", 38);
        garage.addCar(car, own);
        garage.addCar(car1, own1);
        garage.addCar(car2, own2);
        Collection<Car> cars = garage.carsWithPowerMoreThan(20);
        StringBuilder s = new StringBuilder();
        for (Car iter_car:
                cars) {
            s.append(iter_car.getCarId());
        }
        assertEquals("1", s.toString());
    }

    @Test
    void allCarsOfOwner() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        garage.addCar(car, own);
        Collection<Car> cars = garage.allCarsOfOwner(own);
        StringBuilder s = new StringBuilder();
        for (Car iter_car:
                cars) {
            s.append(iter_car.getBrand());
        }
        assertEquals("Nisan", s.toString());

    }

    @Test
    void meanOwnersAgeOfCarBrand() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        garage.addCar(car, own);
        int age = garage.meanOwnersAgeOfCarBrand("Nisan");
        assertEquals(own.getAge(), age);
    }

    @Test
    void meanCarNumberForEachOwner() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        garage.addCar(car, own);
        int number = garage.meanCarNumberForEachOwner();
        assertEquals(1, number);
    }

    @Test
    void removeCar() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        garage.addCar(car, own);
        assertEquals(1 , garage.cars.size());
        assertEquals(1, garage.owners.size());
        garage.removeCar((int)car.getCarId());
        assertEquals(0, garage.cars.size());
    }

    @Test
    void addCar() {
        OurGarage garage = new OurGarage();
        Car car = new Car(1, "Nisan", "x5", 60, 180, 1);
        Owner own = new Owner(1, "Denis", "Pupkin", 36);
        garage.addCar(car, own);
        assertEquals(1 , garage.cars.size());
        assertEquals(1, garage.owners.size());
    }
}