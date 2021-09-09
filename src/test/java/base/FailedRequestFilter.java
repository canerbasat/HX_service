package base;


import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.log4j.Logger;


public class FailedRequestFilter implements Filter {
    private static org.apache.log4j.Logger log = Logger.getLogger(FailedRequestFilter.class);
    private static String logMessage = "";





    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

           Response response = ctx.next(requestSpec, responseSpec);

           if (response.statusCode() >= 400) {
               log.info(requestSpec.getMethod() + " " + requestSpec.getURI() + " => " + response.getStatusCode() + " " + response.getStatusLine());
           }

           return response;
       }

    }
