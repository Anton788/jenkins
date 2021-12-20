import java.util.*;

public class OurGarage implements Garage{
    HashMap<String, Collection<Car>> brands;
    HashMap<Long, HashMap<Long, Car>> relations;
    HashMap<Long, Car> cars;
    HashMap<Long, Owner> owners;
    HashMap<Long, HashMap<Long, Owner>> car_relations;
    TreeSet<Car> velocity_cars;

    Comparator<Car> comparator = Comparator.comparing(obj -> obj.getMaxVelocity());
    public OurGarage(){
        brands = new HashMap<>();
        relations = new HashMap<>(new HashMap<>());
        cars = new HashMap<>();
        owners = new HashMap<>();
        car_relations = new HashMap<>(new HashMap<>());
        velocity_cars = new TreeSet<>(comparator);
    }
    @Override
    public Collection<Owner> allCarsUniqueOwners() {
        ArrayList<Owner> owners_with_cars = new ArrayList<>();
        Set<Long> set_relations =  relations.keySet();
        for(long ownerId:
                set_relations) {
            if (relations.get(ownerId).size() > 0){
                owners_with_cars.add(owners.get(ownerId));
            }
        }
        return owners_with_cars;
    }

    @Override
    public Collection<Car> topThreeCarsByMaxVelocity() {
        ArrayList<Car> cars = new ArrayList<>();
        Car first = velocity_cars.last();
        velocity_cars.remove(first);
        Car second = velocity_cars.last();
        velocity_cars.remove(second);
        Car third = velocity_cars.last();
        velocity_cars.add(first);
        velocity_cars.add(second);
        cars.add(first);
        cars.add(second);
        cars.add(third);
        return cars;
    }

    @Override
    public Collection<Car> allCarsOfBrand(String brand) {
        return brands.get(brand);
    }

    @Override
    public Collection<Car> carsWithPowerMoreThan(int power) {
        Collection<Car> value_car = cars.values();
        ArrayList<Car> array = new ArrayList<>();
        for (Car car:
                value_car) {
            if (car.getPower() > power){
                array.add(car);
            }
        }
        return array;
    }

    @Override
    public Collection<Car> allCarsOfOwner(Owner owner) {
        return relations.get(owner.getOwnerId()).values();
    }

    @Override
    public int meanOwnersAgeOfCarBrand(String brand) {
        Collection<Owner> value_car = owners.values();
        int count_cars = brands.get(brand).size();
        int sum_ages = 0;
        for (Owner own:
                value_car) {
            sum_ages += own.getAge();
        }
        return sum_ages / count_cars;
    }

    @Override
    public int meanCarNumberForEachOwner() {
        Collection<Car> value_car = cars.values();
        int count_owners = owners.size();
        int count_cars = cars.size();
        return count_cars / count_owners;
    }

    @Override
    public Car removeCar(int carId) {
        Car removed_car = cars.remove((long)carId);
        car_relations.remove((long)carId);
        HashMap<Long, Car> hash_car =  relations.get(removed_car.getOwnerId());
        hash_car.remove((long)carId);
        relations.put(removed_car.getOwnerId(), hash_car);
        velocity_cars.remove(removed_car);
        return removed_car;
    }

    @Override
    public void addCar(Car car, Owner owner) {
        if (relations.containsKey(owner.getOwnerId())){
            HashMap<Long, Car> hash_car = relations.get(owner.getOwnerId());
            hash_car.put(car.getCarId(), car);
            relations.put(owner.getOwnerId(), hash_car);
        } else {
            HashMap<Long, Car> hash_car = new HashMap<>();
            hash_car.put(car.getCarId(), car);
            relations.put(owner.getOwnerId(), hash_car);
        }
        if (car_relations.containsKey(car.getCarId())){
            HashMap<Long, Owner> hash_owners = car_relations.get(car.getCarId());
            hash_owners.put(owner.getOwnerId(), owner);
            car_relations.put(car.getCarId(), hash_owners);
        } else {
            HashMap<Long, Owner> hash_owners = new HashMap<>();
            hash_owners.put(owner.getOwnerId(), owner);
            car_relations.put(car.getCarId(), hash_owners);
        }
        if (brands.containsKey(car.getBrand())){
            ArrayList<Car> brand_cars = (ArrayList<Car>) brands.get(car.getBrand());
            brand_cars.add(car);
            brands.put(car.getBrand(), brand_cars);
        } else {
            ArrayList<Car> brand_cars = new ArrayList<>();
            brand_cars.add(car);
            brands.put(car.getBrand(), brand_cars);
        }
        if (!owners.containsKey(owner.getOwnerId())){
            owners.put(owner.getOwnerId(), owner);
        }
        if (!cars.containsKey(car.getCarId())){
            cars.put(car.getCarId(), car);
        }
        velocity_cars.add(car);
    }

    @Override
    public String toString() {
        return "OurGarage{" +
                ", cars=" + cars +
                ", owners=" + owners +
                '}';
    }
}
