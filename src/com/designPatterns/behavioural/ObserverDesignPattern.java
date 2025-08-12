/*
 * Observer Design Pattern
 *
 * The Observer Design Pattern is a behavioral design pattern where an object (Subject)
 * maintains a list of dependents (Observers) and notifies them automatically of any state changes.
 *
 * 💡 When to use:
 *   - When multiple objects need to be updated whenever one object changes.
 *   - When you want to maintain loose coupling between the subject and observers.
 *
 * 📌 Real-life examples in software:
 *   - Event listeners in GUI frameworks (Swing, JavaFX)
 *   - Publish-Subscribe messaging systems (Kafka, RabbitMQ)
 *   - Real-time dashboards
 *
 * Key Participants:
 *   | Component            | Responsibility                                                                 |
 *   |----------------------|--------------------------------------------------------------------------------|
 *   | Subject              | Maintains a list of observers and provides methods to attach/detach them.      |
 *   | Concrete Subject     | Stores state and notifies observers when it changes.                           |
 *   | Observer             | Defines the interface for receiving updates.                                   |
 *   | Concrete Observer    | Implements the Observer interface to update itself based on Subject's changes. |
 *
 * Core Idea:
 *   - The subject doesn’t know the concrete details of observers.
 *   - Observers register themselves with the subject and receive notifications automatically.
 *
 * 1. Introduction
 *   We’ll create a job notification system:
 *     - JobPost (Subject) → publishes job openings.
 *     - JobSeekers (Observers) → automatically get notified when new jobs are posted.
 *
 * 2. Concepts
 *   - Subject = JobPost (manages a list of jobseekers)
 *   - Observer = JobSeeker (gets notified of new jobs)
 *   - Notification flow: When a new job is posted, all registered jobseekers are notified.
 *
 * 3. Analogy
 *   - Think of a company’s HR department posting new jobs:
 *     - HR keeps a list of candidates who want updates.
 *     - When HR posts a new job → all candidates get an email notification automatically.
 *
 * Full Video Explanation: https://youtu.be/7B1DFe0VBxI?si=fnHyKv1Vna8X9Dfq
 */

package com.designPatterns.behavioural;

import java.util.ArrayList;
import java.util.List;

interface JobObserver {
    void update(String jobTitle);
}

interface JobSubject {
    void attach(JobObserver observer);
    void detach(JobObserver observer);
    void notifyAllObservers(String jobTitle);
}

class JobPost implements JobSubject {
    private final List<JobObserver> observers = new ArrayList<>();

    @Override
    public void attach(JobObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(JobObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String jobTitle) {
        for (JobObserver observer : observers) {
            observer.update(jobTitle);
        }
    }

    // Business logic to post a job
    public void postJob(String jobTitle) {
        System.out.println("New job posted: " + jobTitle);
        notifyAllObservers(jobTitle); // Here, Object is changing state, so notify observers
    }
}

class JobSeeker implements JobObserver {
    private final String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    @Override
    public void update(String jobTitle) {
        System.out.println("Hey " + name + ", new job available: " + jobTitle);
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        JobPost jobPost = new JobPost();

        JobObserver seeker1 = new JobSeeker("Radha");
        JobObserver seeker2 = new JobSeeker("Krishna");
        JobObserver seeker3 = new JobSeeker("Parth");

        jobPost.attach(seeker1);
        jobPost.attach(seeker2);
        jobPost.attach(seeker3);

        jobPost.postJob("Java Backend Developer");
        System.out.println("---");
        jobPost.postJob("Full Stack Developer");
    }
}
