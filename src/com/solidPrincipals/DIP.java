/*
 * Dependency Inversion Principle (DIP)
 *
 * Note: Dependency Inversion, Dependency Injection, and Inversion of Control (IoC) are different concepts.
 *
 * Dependency Inversion is a principle that states high-level modules should not depend on low-level modules,
 * but both should depend on abstractions. Abstractions should not depend on implementations; instead, implementations
 * should depend on abstractions.
 * It promotes the use of interfaces or abstract classes to
 * decouple high-level and low-level modules.
 *
 * - Dependency Injection is a design pattern that implements the Dependency Inversion Principle by injecting
 *   dependencies into a class rather than the class creating its own dependencies.
 *
 * - Inversion of Control (IoC) is a broader concept where the control of object creation and lifecycle
 *   is inverted, typically managed by a framework or container. IoC can be achieved through
 *   Dependency Injection, but it can also be implemented in other ways.
 *
 * Analogy:
 * Think of a USB Port on your laptop.
 * The USB Port (interface) stays the same.
 * You can plug in a pen drive, external HDD, or phone — all work without changing your laptop.
 * Similarly, our FileStorageService doesn’t care whether files go to AWS, GCP, or local FS.
 *
 * Concepts:
 * - High-Level Module: FileStorageService – business logic that needs to save files.
 * - Low-Level Modules: AwsStorageProvider, GcpStorageProvider, LocalStorageProvider.
 * - Abstraction: StorageProvider interface – defines the contract.
 * - Inversion: Instead of service depending directly on AWS SDK or GCP SDK, both depend on the interface.
 *
 * Class Diagram:
 *
 *           +---------------------+
 *           |  FileStorageService |
 *           +---------------------+
 *                     |
 *              depends on
 *                     v
 *           +-------------------+
 *           |  StorageProvider  | <---- Abstraction
 *           +-------------------+
 *            /          |         \
 *           /           |          \
 * +----------------+  +----------------+  +------------------+
 * | AwsStorageProv |  | GcpStorageProv |  | LocalStorageProv |
 * +----------------+  +----------------+  +------------------+
 *
 */

package com.solidPrincipals;

interface StorageProvider {
    void uploadFile(String fileName, byte[] data);
    byte[] downloadFile(String fileName);
    void deleteFile(String fileName);
}

class AwsStorageProvider implements StorageProvider {
    @Override
    public void uploadFile(String fileName, byte[] data) {
        System.out.println("Uploading " + fileName + " to AWS S3...");
        // AWS SDK logic here
    }

    @Override
    public byte[] downloadFile(String fileName) {
        System.out.println("Downloading " + fileName + " from AWS S3...");
        return new byte[0]; // mock
    }

    @Override
    public void deleteFile(String fileName) {
        System.out.println("Deleting " + fileName + " from AWS S3...");
    }
}

class GcpStorageProvider implements StorageProvider {
    @Override
    public void uploadFile(String fileName, byte[] data) {
        System.out.println("Uploading " + fileName + " to Google Cloud Storage...");
    }

    @Override
    public byte[] downloadFile(String fileName) {
        System.out.println("Downloading " + fileName + " from Google Cloud Storage...");
        return new byte[0];
    }

    @Override
    public void deleteFile(String fileName) {
        System.out.println("Deleting " + fileName + " from Google Cloud Storage...");
    }
}

class LocalStorageProvider implements StorageProvider {
    @Override
    public void uploadFile(String fileName, byte[] data) {
        System.out.println("Saving " + fileName + " to Local File System...");
    }

    @Override
    public byte[] downloadFile(String fileName) {
        System.out.println("Reading " + fileName + " from Local File System...");
        return new byte[0];
    }

    @Override
    public void deleteFile(String fileName) {
        System.out.println("Deleting " + fileName + " from Local File System...");
    }
}

class FileStorageService {
    private final StorageProvider storageProvider;

    // DIP applied: inject abstraction
    public FileStorageService(StorageProvider storageProvider) {
        this.storageProvider = storageProvider;
    }

    public void storeDocument(String fileName, byte[] data) {
        storageProvider.uploadFile(fileName, data);
    }

    public byte[] fetchDocument(String fileName) {
        return storageProvider.downloadFile(fileName);
    }

    public void removeDocument(String fileName) {
        storageProvider.deleteFile(fileName);
    }
}

public class DIP {
    public static void main(String[] args) {
        StorageProvider storage = new AwsStorageProvider();
        // Can swap with new GcpStorageProvider() or LocalStorageProvider()

        FileStorageService service = new FileStorageService(storage);

        service.storeDocument("invoice.pdf", new byte[]{1,2,3});
        service.fetchDocument("invoice.pdf");
        service.removeDocument("invoice.pdf");
    }
}
