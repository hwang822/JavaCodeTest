import java.util.List;

public class InterviewQA {
    //Q: is string mutable in java
    //A: In Java, all strings are immutable. When you are trying to modify a String , what you are really doing is creating a new one.
    //   However, when you use a StringBuilder , you are actually modifying the contents, instead of creating a new one.

    static void strTest(){
        String str = "Good";
        str = str + " Morning";

        StringBuffer str1 = new StringBuffer("Good");
        str1.append(" Morning");
    }


    //Q: The difference between StringBuilder and StringBuffer?
    //A: StringBuffer is synchronized i.e. thread safe. It means two threads can't call the methods of StringBuffer simultaneously.
    //   StringBuilder is non-synchronized i.e. not thread safe. It means two threads can call the methods of StringBuilder simultaneously.

    void stringBuildAndBuffers(int minimumCapacity) {
        StringBuffer str1 = new StringBuffer("Good");
        str1.append(" Morning");
        StringBuilder str2 = new StringBuilder("Good");
        str2.append(" Morning");
    }

    //Q: What is OOP?
    //A: Object-oriented programming System(OOPs) is a programming paradigm based on the concept of “objects” that contain data and methods

    class OOP{
        int data;
        int method(int data){
            return data;
        }
        OOP(){};
    }

    //Q: Why use inheritance?
    //A: Inheritance allows us to reuse of code, it improves reusability in your java application. Note: The biggest advantage of Inheritance is
    //   that the code that is already present in base class need not be rewritten in the child class.

    static class Teacher {
        String designation = "Teacher";
        String collegeName = "Beginnersbook";
        void does(){
            System.out.println("Teaching");
        }
    }

    public static class PhysicsTeacher extends Teacher{
        String mainSubject = "Physics";
        public static void main(String args[]){
            PhysicsTeacher obj = new PhysicsTeacher();  //Output:
            System.out.println(obj.collegeName);        //Beginnersbook
            System.out.println(obj.designation);        //Teacher
            System.out.println(obj.mainSubject);        //Physics
            obj.does();                                 //Teaching
        }
    }

    //Q: What java 8 feature you used
    //A: 1. Default and Static
    //   2. for each() method
    //   3. Java Stream API
    //   4. Collection API improvements
    //   5. Java Time API
    //   6. Concurrency API
    //   7. Improvements
    //   8. Lambda expressions   Lambda Expressions syntax is (argument) -> (body).

    //Q: What kinds of exception handling in java, how we use exception handling
    //A: Customized Exception Handling : Java exception handling is managed via five keywords: try, catch, throw, throws, and finally. Briefly, here is how they work. Program statements
    //   that you think can raise exceptions are contained within a try block. If an exception occurs within the try block, it is thrown.

    //Q: What key word this, final means
    //A: the final keyword is used in several contexts to define an entity that can only be assigned once.
    //   Once a final variable has been assigned, it always contains the same value

    //Q: What is collection
    //A: The Collection in Java is a framework that provides an architecture to store and manipulate the group of objects

    //Q: What does static mean in java
    //A:  It means that the static object belongs specifically to the class, instead of instances of that class. Variables, methods, and nested classes can be static. ... Instead,
    //    we can make the variable static and make it part of the class itself.

    //Q: Can we override static method
    //A: Static methods cannot be overridden because they are not dispatched on the object instance at runtime.
    //   The compiler decides which method gets called. Static methods can be overloaded (meaning that you can have the same method name for several methods as long as they have different parameter types)

    //Q: What query you used for Cassandra
    //A: A SELECT expression using COUNT(*) returns the number of rows that matched the query.

    //Q: What annotation you used in spring
    //A: When you use @Autowired on setter methods, Spring tries to perform it ... This annotation is used to avoid the confusion that occurs when you

    //Q: How we configure port number is spring
    //A: Spring Boot provides sensible defaults for many configuration properties.

    //Q: What is spring initializer
    //A: The Spring Initializr is ultimately a web application that can generate a Spring Boot project structure for yo

    //Q: What is get, post, put, delete mapping
    //A: The primary or most-commonly-used HTTP verbs (or methods, as they are properly called) are POST, GET, PUT, PATCH, and DELETE. These correspond to create, read, update, and delete (or CRUD) operations, respectively

    //Q: What is serialization and deserialization
    //A: Serialization is a mechanism of converting the state of an object into a byte stream. Deserialization is the reverse process where the byte stream is used to recreate the actual Java object in memory.

    //Q: What is @mock in unit testing
    //A: Mock is a method/object that simulates the behavior of a real method/object in controlled ways. Mock objects are used in unit testing.

    //Q: What is garbage collection
    //A: attempts to reclaim garbage, or memory occupied by objects that are no longer in use by the program.

    //https://www.journaldev.com/2800/java-8-date-localdate-localdatetime-instanthttps://www.journaldev.com/2800/java-8-date-localdate-localdatetime-instant
    //9. Lambda expressions: Lambda Expressions syntax is (argument) -> (body).
        Runnable r1 = () -> System.out.println("My Runnable");

    //10. Java Stream API: to iterate over a list of integers
        private static int sumStream(List<Integer> list) {
            return list.stream().filter(i -> i > 10).mapToInt(i -> i).sum();
        }

    //11. Date Time API: Java 8 Date Time API is a good addition to the core Java APIs.

    //12. Java Access Modifiers: Java provides access control through three keywords – private, protected and public.

    //13. Abstract: Abstract class in Java is similar to interface except that it can contain default method implementation. An abstract class can have an abstract method without body and it can have methods with implementation also.

    //14. Interface: Interface in java is one of the core concept. Java Interface is core part of java programming language and used a lot not only in JDK but also java design patterns. Most of the frameworks use java interface heavily.

    //15. Composition: to implement has-a relationship in classes

    //16. Inheritance in Java is the method to create a hierarchy between classes by inheriting from other classes.

    //17. inside body of class in another class

    //18. Java String: String is immutable object which means that it cannot be changed once it is created.

    //19. Annotations: Java Annotations provides information about the code

    //20. Enum: Enum was introduced in Java 1.5 as a new type whose fields consists of a fixed set of constants.

    //21. Collection: various type of collections such as List, Set, Queue, Stack, etc.

    //22. Java General: with collection classes is very easy but it provides a lot more features than just creating the type of collection.

    //23. Scanner: Scanner class in Java (java.util.Scanner) was introduced in Java 1.5 as a simple text scanner which can parse primitive types and strings using regular expressions.

    //24. Exception Handling: Exception is an error event that can happen during the execution of a program and disrupts its normal flow

    //25. Thread: Thread can be called lightweight process. Thread requires less resources to create and exists in the process, thread shares the process resources.

    //26. Java synchronized: Synchronization is the tool using which we can achieve thread-safety,

    //27. Java ThreadLocal is used to create thread local variables.

    //28. java.util.Timer is a utility class that can be used to schedule a thread to be executed at certain time in future.

    //29. Java thread pool manages the pool of worker threads.

    //30. java.util.concurrent.Callable interface in concurrency package that is similar to Runnable interface but it can return any Object and able to throw Exception.

    //31. Java Callable Future interfaces that we can use to get the concurrent processing benefits of threads as well as they are capable of returning value to the calling program.

    //32. java.util.concurrent.locks package with Lock interface and some implementation classes to improve the Object locking mechanism.

    //33. Regular Expressions: The regular expression in java defines a pattern for a String. Regular Expression can be used to search, edit or manipulate text

    //34: Java Heap space is used by java runtime to allocate memory to Objects and JRE classes.

    //35:

}
