/*
 * What is the Prototype Design Pattern?
 * - Creates new objects by cloning existing instances (called prototypes) rather than creating new ones from scratch (using the new keyword).
 * - Used when you want to avoid multiple object creations of the same instance;
 *   instead, you copy the object to a new object and then modify as needed.
 *
 * How do we implement this?
 * - The object being copied should provide a copying feature by implementing the Cloneable interface.
 * - Override the clone() method to customize the cloning process.
 *
 * When to use it?
 * - When object creation is costly or time-consuming (e.g., database calls, network I/O, or complex computations).
 * - When you need many similar objects.
 *
 * Real-World Analogy:
 * 🧠 Think of a Resume Template in an enterprise system:
 *   - You fill in a standard resume template, change the name and details, and save it as a new file.
 *   - You clone a template — not create it from scratch every time.
 *
 * Example:
 * - Efficiently create customized copies of pre-defined documents (like HR or IT templates) using deep cloning,
 *   so changes to one copy don't affect the original or other copies.
 */

package com.designPatterns.creational;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// implementing an enterprise-ready Document system where each department has its own document template.

@Getter
@Setter
@ToString
class Metadata implements Cloneable {
    private List<String> tags;
    private String version;

    public Metadata(List<String> tags, String version) {
        // Deep copy on construction
        this.tags = new ArrayList<>(tags);
        this.version = version;
    }

    public Metadata clone() throws CloneNotSupportedException {
        Metadata cloned = (Metadata) super.clone();
        // Deep copy the mutable list
        cloned.tags = new ArrayList<>(this.tags);
        return cloned;
    }
}

//This allows generic cloning and promotes type safety.
interface Prototype<T> extends Cloneable {
    T clone();
}

//Document represents a reusable enterprise template (like HR forms, contracts, policies)
@Data
@ToString
class Document implements Prototype<Document> {
    private String title;
    private String content;
    private String author;
    private String department;
    private Metadata metadata;  // <-- mutable nested object field, which will need to deeply copied.

    public Document(String title, String content, String author, String department, Metadata metadata) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.department = department;
        this.metadata = metadata;
    }

    @Override
    public Document clone() {
        try {
            Document cloned = (Document) super.clone();
            // DEEP CLONE of mutable nested field
            cloned.metadata = this.metadata.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Document cloning failed", e);
        }
    }
}

// DocumentRegistry is a centralized cache used to clone these templates using the Prototype Design Pattern.
class DocumentRegistry {
    private static final Map<String, Document> prototypes = new HashMap<>();

    static {
        Metadata hrMetadata = new Metadata(List.of("policy", "hr"), "v1.0");
        Metadata itMetadata = new Metadata(List.of("it", "setup"), "v2.0");

        prototypes.put("HR", new Document("HR Policy", "Leave rules and benefits", "HR Dept", "HR", hrMetadata));
        prototypes.put("IT", new Document("IT SOP", "Network setup guide", "IT Dept", "IT", itMetadata));
    }

    public static Document getClonedDocument(String type) {
        Document prototype = prototypes.get(type);
        return prototype != null ? prototype.clone() : null;
    }
}

public class PrototypeDesignPattern {
    public static void main(String[] args) {
        Document hrDoc1 = DocumentRegistry.getClonedDocument("HR");
        hrDoc1.setAuthor("KrishnaKumar");
        hrDoc1.setTitle("Updated HR Policy");

        // Change the metadata tags (MUTABLE)
        hrDoc1.getMetadata().getTags().add("2025-update");

        Document hrDoc2 = DocumentRegistry.getClonedDocument("HR");

        System.out.println("🔹 Cloned HR Doc 1:");
        System.out.println(hrDoc1);

        System.out.println("🔹 Cloned HR Doc 2:");
        System.out.println(hrDoc2); // Should NOT contain "2025-update"
    }
}
