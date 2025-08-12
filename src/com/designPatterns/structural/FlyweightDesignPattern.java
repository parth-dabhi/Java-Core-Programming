/*
 * Definition:
 * The Flyweight Pattern is a structural design pattern that reduces memory usage
 * by sharing immutable, reusable objects instead of creating duplicates for each use.
 *
 * It’s mainly used when:
 *   - You have a large number of similar objects.
 *   - Many of their attributes are shared/intrinsic.
 *   - Only a small part of their state is unique/extrinsic.
 *
 * Key Concepts:
 *   - Intrinsic State → Shared, does not change between objects (stored inside the flyweight).
 *   - Extrinsic State → Unique per use, passed in from outside.
 *
 * When to Use:
 *   - Caching immutable objects.
 *   - Large-scale object creation that wastes memory.
 *   - Representing elements like characters in a document, map tiles, or chess pieces.
 *
 * Real-World Enterprise Example:
 *   Scenario:
 *     We are building a map rendering system for a large-scale logistics platform.
 *     We need to render millions of vehicles on the map, but:
 *       - Vehicle icon, type, and color are the same for vehicles of the same type → intrinsic.
 *       - Location (lat/lon) changes frequently → extrinsic.
 *     We use Flyweight to avoid creating duplicate icons.
 */

package com.designPatterns.structural;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

enum BallColor {
    RED, BLUE, GREEN, YELLOW
}

//Flyweight Interface
interface Ball {
    void draw(int x, int y, int radius); // extrinsic state
}

//Concrete Flyweight
class ConcreteBall implements Ball {
    private final BallColor color; // intrinsic
    private final String imagePath; // intrinsic

    public ConcreteBall(BallColor color, String imagePath) {
        this.color = color;
        this.imagePath = imagePath;
    }

    @Override
    public void draw(int x, int y, int radius) {
        System.out.printf(
                "Drawing %s ball at (%d, %d) with radius %d using image %s%n",
                color, x, y, radius, imagePath
        );
    }
}

//Flyweight Factory with Caching

class BallFactory {
    private static final Map<BallColor, Ball> CACHE = new ConcurrentHashMap<>();

    public static Ball getBall(BallColor color) {
        return CACHE.computeIfAbsent(color, c -> {
            String path = loadImageFromDisk(c);
            return new ConcreteBall(c, path);
        });
    }

    private static String loadImageFromDisk(BallColor color) {
        System.out.println("Loading image for " + color + " ball from disk...");
        return "/assets/balls/" + color.name().toLowerCase() + ".png";
    }
}

// FlyweightBallGame
class FlyweightBallGame {
    public static void main(String[] args) {
        drawBall(BallColor.RED, 10, 20, 5);
        drawBall(BallColor.RED, 15, 25, 10);
        drawBall(BallColor.BLUE, 30, 40, 8);
        drawBall(BallColor.BLUE, 35, 45, 12);
    }

    private static void drawBall(BallColor color, int x, int y, int radius) {
        Ball ball = BallFactory.getBall(color); // intrinsic reused
        ball.draw(x, y, radius); // extrinsic applied
    }
}

public class FlyweightDesignPattern {
}
