/*
 * Factory Design Pattern - Properties
 *
 * - Creational design pattern.
 * - Used when you have multiple subclasses of a superclass and need to return a specific class instance based on input.
 * - Provides abstraction between implementation & client classes.
 * - Removes direct instantiation of client classes from client code, promoting loose coupling and easier maintenance.
 * - Superclass can be an interface, abstract class, or basic class.
 * - Factory class has a static method which returns the instance of a subclass based on input.
 *
 * Why Use It?
 * - To remove tight coupling between client and class creation.
 * - When object creation logic is complex or needs to be centralized.
 * - When you want to return subclass objects based on some input.
 */

package com.designPatterns.creational;

interface Shape {
    String draw();
}

class Rectangle implements Shape {
    @Override
    public String draw() {
        return "Draw Rectangle";
    }
}

class Circle implements Shape {
    @Override
    public String draw() {
        return "Draw Circle";
    }
}

class Triangle implements Shape {
    @Override
    public String draw() {
        return "Draw Triangle";
    }
}

// Enum to Represent Types (Enterprise-safe)
enum ShapeType {
    CIRCLE,
    RECTANGLE,
    TRIANGLE
}

interface ShapeFactory {
    Shape createShape(ShapeType shapeType);
}

class ShapeFactoryImpl implements ShapeFactory {
    public Shape createShape(ShapeType shapeType) {
        return switch (shapeType) {
            case CIRCLE -> new Circle(); // OLD switch : case CIRCLE: return new Circle();
            case TRIANGLE -> new Triangle();
            case RECTANGLE -> new Rectangle();
        };
    }
}

// Client Class
// typically refers to a class that interacts with and utilizes the services or functionalities provided by another class or set of classes.
// It acts as a consumer of those services.
public class FactoryDesignPattern {

    public static void main(String[] args) {
        ShapeFactoryImpl shapeFactory = new ShapeFactoryImpl();

        Shape shape1 = shapeFactory.createShape(ShapeType.CIRCLE);
        shape1.draw();

        Shape shape2 = shapeFactory.createShape(ShapeType.RECTANGLE);
        shape2.draw();
    }
}
