Spring Boot: @Bean, @Component, Proxies, and Configuration — A Complete Reference

1. What a Proxy Is and Why Spring Uses Them

A proxy is an object that stands in front of another object and intercepts method calls. It can add behavior before or after the real method, cache results, enforce singleton behavior, apply security, logging, or transactions, and control object creation. Spring uses proxies to add framework features without requiring changes to user code.

Spring uses two types of proxies:

JDK Dynamic Proxy: Used when a class implements an interface.

CGLIB Proxy: Used when Spring subclasses a class at runtime.

Proxies allow Spring to intercept method calls and apply additional behavior such as dependency injection, lifecycle management, and singleton enforcement.

2. How @Bean Interacts with @Configuration

When @Bean methods are placed inside a class annotated with @Configuration, Spring wraps that class in a CGLIB proxy. This proxy intercepts calls to @Bean methods and ensures:

Singleton behavior is enforced.

Calls to one @Bean method from another return the same instance.

@Bean methods behave like managed factory methods.

Without @Configuration, Spring does not proxy the class. Calls to @Bean methods behave like normal Java method calls, and singleton behavior is not enforced.

@Configuration activates Spring’s proxying so that @Bean methods behave like managed, cached factories.

3. When to Use @Bean vs. @Component

Use @Component when:

You wrote the class.

It is part of your application logic.

You want Spring to auto-detect it.

It does not need special construction logic.

Examples include service classes, repository classes, and controllers.

Use @Bean when:

You need to configure a third-party class.

You need custom construction logic.

You need to pass parameters into the constructor.

You want explicit control over how the object is created.

You want to expose a bean from a library.

Examples include configuring ObjectMapper, RestTemplate, DataSource, or ExecutorService.

Rule of thumb: If you write the class, use @Component. If you configure the class, use @Bean.

4. Full Example Project Using Both @Bean and @Component

Project Structure

src/main/java/com/example/demo
│
├── DemoApplication.java
│
├── config
│   └── AppConfig.java
│
├── service
│   ├── GreetingService.java
│   └── GreetingServiceImpl.java
│
└── controller
    └── GreetingController.java

@Component Example

GreetingService.java:

public interface GreetingService {
    String greet(String name);
}

GreetingServiceImpl.java:

@Component
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}

@Bean Example

AppConfig.java:

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper customObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}

Controller Using Both

GreetingController.java:

@RestController
public class GreetingController {

    private final GreetingService greetingService;
    private final ObjectMapper objectMapper;

    public GreetingController(GreetingService greetingService,
                              ObjectMapper objectMapper) {
        this.greetingService = greetingService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) throws Exception {
        String message = greetingService.greet(name);
        return objectMapper.writeValueAsString(message);
    }
}

5. Summary Table

Concept

Purpose

Proxy

Intercepts method calls to add behavior

CGLIB Proxy

Used for @Configuration classes

@Configuration

Enables proxying of @Bean methods

@Bean

Explicit bean creation for third-party or custom-configured objects

@Component

Auto-detected bean for user-defined classes

Rule of Thumb

Write the class → @Component; configure the class → @Bean
