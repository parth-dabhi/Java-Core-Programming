/*
 * Composite Design Pattern Overview
 *
 * Purpose:
 *   The Composite pattern lets you treat individual objects and compositions (groups of objects) uniformly.
 *   It is part of the Structural Design Patterns.
 *
 * ✅ Real-World Use Case:
 *   Let’s take a company organization hierarchy.
 *   An Employee can be:
 *     - an Individual Contributor (Leaf) — like a Developer or QA.
 *     - or a Manager (Composite) — who has subordinates (other employees).
 *   We want to be able to treat both types uniformly.
 *
 * Structure:
 *
 *           Employee (Component)
 *           /                \
 *   Developer, QA (Leaf)   Manager (Composite)
 *
 * 💡 When to Use Composite Pattern in Enterprise Apps
 *
 * | Use Case                                  | Example                                                 |
 * | ------------------------------------------| --------------------------------------------------------|
 * | Tree-like structures                      | File system, organization hierarchy, product categories |
 * | Uniform treatment of groups & individuals | Task assignment system (task vs. project group)         |
 * | UI component libraries                    | Button, Panel, Layout, etc. (Swing, JavaFX)             |
 *
* 🧠 Simple Example (Real Life):
* Think of a Company Org Chart:
*
* CEO
* ├── Manager A
* │    ├── Developer 1
* │    └── Developer 2
* └── Manager B
*      ├── Developer 3
*      └── Intern
*
* Now, imagine you want to:
* ✅ Give a bonus to every employee under CEO
* ✅ Print the total number of subordinates
* You want to treat both “Developer” and “Manager” the same way (even though one has subordinates, and one doesn't).
*
* That’s where Composite Pattern helps:
* You can create a common interface for Employee, and whether it’s a Developer or a Manager,
* you just call the same method — the logic underneath handles the difference!
*
* 🤔 What Problem Does It Solve?
* Without the composite pattern, you’d need to write separate code for leaf nodes (e.g., Developer) and composite nodes (e.g., Manager with team).
* With Composite, you treat them the same, making your code cleaner, scalable, and extensible.
*/

package com.designPatterns.structural;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum EmployeeRole {
    DEVELOPER,
    QA,
    INTERN,
    MANAGER,
    CEO;
}

interface EmployeeComponent {
    String getName();
    EmployeeRole getRole();
    void displayDetails(String indent);
}

final class IndividualContributorLeaf implements EmployeeComponent {
    private final String name;
    private final EmployeeRole role;

    public IndividualContributorLeaf(String name, EmployeeRole role) {
        if (role == EmployeeRole.MANAGER || role == EmployeeRole.CEO) {
            throw new IllegalArgumentException("IndividualContributor can't have role " + role);
        }
        this.name = name;
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EmployeeRole getRole() {
        return role;
    }

    @Override
    public void displayDetails(String indent) {
        System.out.println(indent + "- " + role + ": " + name);
    }
}

class ManagerComposite implements EmployeeComponent {
    private final String name;
    private final EmployeeRole role;
    private final List<EmployeeComponent> subordinates;

    public ManagerComposite(String name, EmployeeRole role) {
        if (role != EmployeeRole.MANAGER && role != EmployeeRole.CEO) {
            throw new IllegalArgumentException("ManagerComposite must have role MANAGER or CEO");
        }
        this.role = role;
        this.name = name;
        this.subordinates = new ArrayList<>();
    }

    public void addSubordinate(EmployeeComponent employee) {
        subordinates.add(employee);
    }

    public void removeSubordinate(EmployeeComponent employee) {
        subordinates.remove(employee);
    }

    public List<EmployeeComponent> getSubordinates() {
        return Collections.unmodifiableList(subordinates);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EmployeeRole getRole() {
        return role;
    }

    @Override
    public void displayDetails(String indent) {
        System.out.println(indent + "🧑‍💼 " + role + ": " + name);
        for (EmployeeComponent e : subordinates) {
            e.displayDetails(indent + "    ");
        }
    }
}

public class CompositeDesignPattern {
    public static void main(String[] args) {
        // Leaf employees
        EmployeeComponent dev1 = new IndividualContributorLeaf("Radha Sharma", EmployeeRole.DEVELOPER);
        EmployeeComponent qa1 = new IndividualContributorLeaf("Krishna Das", EmployeeRole.QA);
        EmployeeComponent dev2 = new IndividualContributorLeaf("Meera Joshi", EmployeeRole.DEVELOPER);
        EmployeeComponent intern = new IndividualContributorLeaf("Ravi Kumar", EmployeeRole.INTERN);

        // Manager 1
        ManagerComposite teamLead = new ManagerComposite("Sundar Raj", EmployeeRole.MANAGER);
        teamLead.addSubordinate(dev1);
        teamLead.addSubordinate(qa1);

        // Manager 2
        ManagerComposite engineeringManager = new ManagerComposite("Lakshmi Rao", EmployeeRole.MANAGER);
        engineeringManager.addSubordinate(intern);
        engineeringManager.addSubordinate(dev2);

        // CEO
        ManagerComposite ceo = new ManagerComposite("Vikram Singh", EmployeeRole.CEO);
        ceo.addSubordinate(engineeringManager);
        ceo.addSubordinate(teamLead);

        // Display full structure
        ceo.displayDetails("");
        System.out.println("\nTotal Employees under CEO: " + ceo.getSubordinates().stream()
                .mapToInt(sub ->
                        sub instanceof ManagerComposite ? ((ManagerComposite) sub).getSubordinates().size() + 1 : 1)
                .sum());
    }
}
