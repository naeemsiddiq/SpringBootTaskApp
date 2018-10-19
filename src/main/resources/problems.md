swagger configurations
-add swaggerConfiguration.java 
-add swagger dependencies with version 2.8.0(note 2.9.2 have some exceptions)

-swagger gives "basic-error-controller","hystrix-dashboard-controller"(if we are using hystrix) and "web-mvc-endpoint-handler-mapping" by default in the swagger dashboard, to remove these we use 
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
				
				OR 
				simply use 
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
				
				
hystrix configurations
- @EnableHystrix  and @EnableCircuitBreaker  do the same work, we can use any one of them